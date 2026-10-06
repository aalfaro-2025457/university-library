package org.angelalfaro.university_library.dto.Libro;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LibroRequestDto {
    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotBlank(message = "Title is required")
    private String titulo;

    @NotBlank(message = "Author is required")
    private String autor;

    @NotBlank(message = "Category is required")
    private String categoria;

    @NotNull(message = "Total stock is required")
    @Min(value = 1, message = "Stock must be at least 1")
    private Integer stockTotal;
}
