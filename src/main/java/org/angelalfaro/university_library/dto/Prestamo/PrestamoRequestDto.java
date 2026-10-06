package org.angelalfaro.university_library.dto.Prestamo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrestamoRequestDto {
    @NotNull(message = "User ID is required")
    private Long usuarioId;

    @NotNull(message = "Book ID is required")
    private Long libroId;
}
