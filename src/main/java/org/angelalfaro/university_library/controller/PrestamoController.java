package org.angelalfaro.university_library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.angelalfaro.university_library.dto.Prestamo.PrestamoRequestDto;
import org.angelalfaro.university_library.dto.Prestamo.PrestamoResponseDto;
import org.angelalfaro.university_library.entity.Usuario;
import org.angelalfaro.university_library.service.Prestamo.PrestamoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prestamos")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoServiceImpl prestamoService;

    @PostMapping
    public ResponseEntity<PrestamoResponseDto> registrarPrestamo(@Valid @RequestBody PrestamoRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.registrarPrestamo(request));
    }

    @PatchMapping("/{id}/devolucion")
    public ResponseEntity<PrestamoResponseDto> registrarDevolucion(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.registrarDevolucion(id));
    }

    @GetMapping("/mis-prestamos")
    public ResponseEntity<List<PrestamoResponseDto>> obtenerMisPrestamos(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(prestamoService.obtenerPrestamosPorUsuario(usuario.getId()));
    }

    @GetMapping("/atrasados")
    public ResponseEntity<List<PrestamoResponseDto>> obtenerPrestamosAtrasados() {
        return ResponseEntity.ok(prestamoService.obtenerPrestamosAtrasados());
    }
}
