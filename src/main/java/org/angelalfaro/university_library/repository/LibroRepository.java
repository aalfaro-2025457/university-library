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
            "(:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) AND " +
            "(:categoria IS NULL OR LOWER(l.categoria) = LOWER(:categoria))")
    Page<Libro> findByFilters(@Param("titulo") String titulo,
                              @Param("categoria") String categoria,
                              Pageable pageable);
}
