package org.angelalfaro.university_library.service.Libro;


import org.angelalfaro.university_library.dto.Libro.LibroRequestDto;
import org.angelalfaro.university_library.dto.Libro.LibroResponseDto;
import org.angelalfaro.university_library.entity.Libro;
import org.angelalfaro.university_library.exception.ResourceNotFoundException;
import org.angelalfaro.university_library.repository.LibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServiceImpl implements ILibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LibroResponseDto> findAll(String titulo, String categoria, Pageable pageable) {
        // El patrón LIKE se construye aquí para no depender de CONCAT() en el JPQL:
        // Hibernate 7 lo traduce a '||' de PostgreSQL y, combinado con lower(),
        // causa el error "function lower(bytea) does not exist".
        String tituloPattern = (titulo == null || titulo.isBlank()) ? null : "%" + titulo.trim() + "%";
        return libroRepository.findByFilters(tituloPattern, categoria, pageable)
                .map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public LibroResponseDto findById(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
        return mapToDto(libro);
    }

    @Override
    @Transactional
    public LibroResponseDto create(LibroRequestDto request) {
        Libro libro = Libro.builder()
                .isbn(request.getIsbn())
                .titulo(request.getTitulo())
                .autor(request.getAutor())
                .categoria(request.getCategoria())
                .stockTotal(request.getStockTotal())
                .stockDisponible(request.getStockTotal())
                .build();

        Libro saved = libroRepository.save(libro);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public LibroResponseDto update(Long id, LibroRequestDto request) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));

        libro.setIsbn(request.getIsbn());
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setCategoria(request.getCategoria());

        int diff = request.getStockTotal() - libro.getStockTotal();
        libro.setStockTotal(request.getStockTotal());
        libro.setStockDisponible(Math.max(0, libro.getStockDisponible() + diff));

        Libro updated = libroRepository.save(libro);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with ID: " + id);
        }
        libroRepository.deleteById(id);
    }

    private LibroResponseDto mapToDto(Libro libro) {
        return LibroResponseDto.builder()
                .id(libro.getId())
                .isbn(libro.getIsbn())
                .titulo(libro.getTitulo())
                .autor(libro.getAutor())
                .categoria(libro.getCategoria())
                .stockTotal(libro.getStockTotal())
                .stockDisponible(libro.getStockDisponible())
                .build();
    }
}