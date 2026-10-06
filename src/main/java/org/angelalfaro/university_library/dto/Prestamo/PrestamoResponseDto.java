package org.angelalfaro.university_library.dto.Prestamo;

import lombok.Builder;
import lombok.Data;
import org.angelalfaro.university_library.model.EstadoPrestamo;

import java.time.LocalDate;

@Data
@Builder
public class PrestamoResponseDto {
    private Long id;
    private Long usuarioId;
    private String usuarioNombre;
    private Long libroId;
    private String libroTitulo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;
    private EstadoPrestamo estado;
}