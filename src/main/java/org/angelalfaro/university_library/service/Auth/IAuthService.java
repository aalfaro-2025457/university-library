package org.angelalfaro.university_library.service.Auth;

import org.angelalfaro.university_library.dto.Auth.AuthResponse;
import org.angelalfaro.university_library.dto.Auth.LoginRequest;
import org.angelalfaro.university_library.dto.Auth.RegisterRequest;

public interface IAuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
