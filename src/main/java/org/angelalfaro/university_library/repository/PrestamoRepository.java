package org.angelalfaro.university_library.repository;

import org.angelalfaro.university_library.entity.Prestamo;
import org.angelalfaro.university_library.model.EstadoPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    long countByUsuarioIdAndEstado(Long usuarioId, EstadoPrestamo estado);

    List<Prestamo> findByUsuarioId(Long usuarioId);

    @Query("SELECT p FROM Prestamo p WHERE p.estado = :estado AND p.fechaDevolucionEsperada < :currentDate")
    List<Prestamo> findOverdueLoans(@Param("estado") EstadoPrestamo estado, @Param("currentDate") LocalDate currentDate);
}