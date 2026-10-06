package org.angelalfaro.university_library.service.Auth;


import lombok.RequiredArgsConstructor;
import org.angelalfaro.university_library.dto.Auth.AuthResponse;
import org.angelalfaro.university_library.dto.Auth.LoginRequest;
import org.angelalfaro.university_library.dto.Auth.RegisterRequest;
import org.angelalfaro.university_library.entity.Usuario;
import org.angelalfaro.university_library.exception.BusinessRuleException;
import org.angelalfaro.university_library.exception.ResourceNotFoundException;
import org.angelalfaro.university_library.model.Rol;
import org.angelalfaro.university_library.repository.UsuarioRepository;
import org.angelalfaro.university_library.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new BusinessRuleException("Email is already registered");
        }

        Usuario user = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.LECTOR)
                .build();

        usuarioRepository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .rol(user.getRol().name())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .rol(user.getRol().name())
                .build();
    }
}