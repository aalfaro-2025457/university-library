package org.angelalfaro.university_library.service.Prestamo;

import org.angelalfaro.university_library.dto.Prestamo.PrestamoRequestDto;
import org.angelalfaro.university_library.dto.Prestamo.PrestamoResponseDto;

import java.util.List;

public interface IPrestamoService {
    PrestamoResponseDto registrarPrestamo(PrestamoRequestDto request);
    PrestamoResponseDto registrarDevolucion(Long prestamoId);
    List<PrestamoResponseDto> obtenerPrestamosPorUsuario(Long usuarioId);
    List<PrestamoResponseDto> obtenerPrestamosAtrasados();
}