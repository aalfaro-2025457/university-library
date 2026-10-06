package org.angelalfaro.university_library.service.Libro;

import org.angelalfaro.university_library.dto.Libro.LibroRequestDto;
import org.angelalfaro.university_library.dto.Libro.LibroResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILibroService {
    Page<LibroResponseDto> findAll(String titulo, String categoria, Pageable pageable);
    LibroResponseDto findById(Long id);
    LibroResponseDto create(LibroRequestDto request);
    LibroResponseDto update(Long id, LibroRequestDto request);
    void delete(Long id);
}