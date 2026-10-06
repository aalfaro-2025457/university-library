package org.angelalfaro.university_library.dto.Libro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibroResponseDto {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private Integer stockTotal;
    private Integer stockDisponible;
}