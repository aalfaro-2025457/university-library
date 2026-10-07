package org.angelalfaro.university_library.repository;

import org.angelalfaro.university_library.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE " +
            "LOWER(l.titulo) LIKE LOWER(COALESCE(:titulo, l.titulo)) AND " +
            "LOWER(l.categoria) = LOWER(COALESCE(:categoria, l.categoria))")
    Page<Libro> findByFilters(@Param("titulo") String titulo,
                              @Param("categoria") String categoria,
                              Pageable pageable);
}
