package org.angelalfaro.university_library.dto.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.angelalfaro.university_library.model.Rol;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    private String nombre;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    /**
     * Rol opcional del usuario. Valores válidos: ADMIN, BIBLIOTECARIO o LECTOR.
     * Si no se envía o es null, se asigna LECTOR por defecto.
     */
    private Rol role;
}