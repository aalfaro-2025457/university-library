package org.angelalfaro.university_library.service.Prestamo;


import org.angelalfaro.university_library.dto.Prestamo.PrestamoRequestDto;
import org.angelalfaro.university_library.dto.Prestamo.PrestamoResponseDto;
import org.angelalfaro.university_library.entity.Libro;
import org.angelalfaro.university_library.entity.Prestamo;
import org.angelalfaro.university_library.entity.Usuario;
import org.angelalfaro.university_library.exception.BusinessRuleException;
import org.angelalfaro.university_library.exception.ResourceNotFoundException;
import org.angelalfaro.university_library.model.EstadoPrestamo;
import org.angelalfaro.university_library.model.EstadoUsuario;
import org.angelalfaro.university_library.repository.LibroRepository;
import org.angelalfaro.university_library.repository.PrestamoRepository;
import org.angelalfaro.university_library.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, LibroRepository libroRepository, UsuarioRepository usuarioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public PrestamoResponseDto registrarPrestamo(PrestamoRequestDto request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (usuario.getEstado() == EstadoUsuario.SANCIONADO) {
            throw new BusinessRuleException("User is sanctioned and cannot borrow books.");
        }

        Libro libro = libroRepository.findById(request.getLibroId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (libro.getStockDisponible() <= 0) {
            throw new BusinessRuleException("Book is out of stock.");
        }

        long activeLoansCount = prestamoRepository.countByUsuarioIdAndEstado(usuario.getId(), EstadoPrestamo.ACTIVO);
        if (activeLoansCount >= 3) {
            throw new BusinessRuleException("User has reached the maximum limit of 3 active loans.");
        }

        libro.setStockDisponible(libro.getStockDisponible() - 1);
        libroRepository.save(libro);

        LocalDate today = LocalDate.now();
        Prestamo prestamo = Prestamo.builder()
                .usuario(usuario)
                .libro(libro)
                .fechaPrestamo(today)
                .fechaDevolucionEsperada(today.plusDays(14))
                .estado(EstadoPrestamo.ACTIVO)
                .build();

        Prestamo saved = prestamoRepository.save(prestamo);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public PrestamoResponseDto registrarDevolucion(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            throw new BusinessRuleException("Loan has already been returned.");
        }

        LocalDate today = LocalDate.now();
        prestamo.setFechaDevolucionReal(today);

        if (today.isAfter(prestamo.getFechaDevolucionEsperada())) {
            prestamo.setEstado(EstadoPrestamo.ATRASADO);
            prestamo.getUsuario().setEstado(EstadoUsuario.SANCIONADO);
            usuarioRepository.save(prestamo.getUsuario());
        } else {
            prestamo.setEstado(EstadoPrestamo.DEVUELTO);
        }

        Libro libro = prestamo.getLibro();
        libro.setStockDisponible(libro.getStockDisponible() + 1);
        libroRepository.save(libro);

        Prestamo updated = prestamoRepository.save(prestamo);
        return mapToDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoResponseDto> obtenerPrestamosPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoResponseDto> obtenerPrestamosAtrasados() {
        return prestamoRepository.findOverdueLoans(EstadoPrestamo.ACTIVO, LocalDate.now()).stream()
                .map(p -> {
                    p.setEstado(EstadoPrestamo.ATRASADO);
                    return mapToDto(p);
                })
                .collect(Collectors.toList());
    }

    private PrestamoResponseDto mapToDto(Prestamo prestamo) {
        return PrestamoResponseDto.builder()
                .id(prestamo.getId())
                .usuarioId(prestamo.getUsuario().getId())
                .usuarioNombre(prestamo.getUsuario().getNombre())
                .libroId(prestamo.getLibro().getId())
                .libroTitulo(prestamo.getLibro().getTitulo())
                .fechaPrestamo(prestamo.getFechaPrestamo())
                .fechaDevolucionEsperada(prestamo.getFechaDevolucionEsperada())
                .fechaDevolucionReal(prestamo.getFechaDevolucionReal())
                .estado(prestamo.getEstado())
                .build();
    }
}