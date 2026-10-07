package org.angelalfaro.university_library.dto.Auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private String email;
    private String rol;

    public AuthResponse(String token, String tokenType, String email, String rol) {
        this.token = token;
        this.tokenType = tokenType;
        this.email = email;
        this.rol = rol;
    }

    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    public static class AuthResponseBuilder {
        private String token;
        private String tokenType;
        private String email;
        private String rol;

        AuthResponseBuilder() {
        }

        public AuthResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponseBuilder tokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public AuthResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AuthResponseBuilder rol(String rol) {
            this.rol = rol;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(token, tokenType, email, rol);
        }

        @Override
        public String toString() {
            return "AuthResponse.AuthResponseBuilder(token=" + token + ", tokenType=" + tokenType + ", email=" + email
                    + ", rol=" + rol + ")";
        }
    }
}