package org.angelalfaro.university_library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.angelalfaro.university_library.dto.Libro.LibroRequestDto;
import org.angelalfaro.university_library.dto.Libro.LibroResponseDto;
import org.angelalfaro.university_library.service.Libro.LibroServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroServiceImpl libroService;

    @GetMapping
    public ResponseEntity<Page<LibroResponseDto>> getAllLibros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String categoria,
            Pageable pageable
    ) {
        return ResponseEntity.ok(libroService.findAll(titulo, categoria, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponseDto> getLibroById(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LibroResponseDto> createLibro(@Valid @RequestBody LibroRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroResponseDto> updateLibro(@PathVariable Long id, @Valid @RequestBody LibroRequestDto request) {
        return ResponseEntity.ok(libroService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
