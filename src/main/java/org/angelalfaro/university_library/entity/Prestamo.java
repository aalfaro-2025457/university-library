package org.angelalfaro.university_library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.angelalfaro.university_library.model.EstadoPrestamo;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos", indexes = {
        @Index(name = "idx_prestamo_usuario", columnList = "usuario_id"),
        @Index(name = "idx_prestamo_estado", columnList = "estado")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    @Column(nullable = false)
    private LocalDate fechaDevolucionEsperada;

    private LocalDate fechaDevolucionReal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPrestamo estado = EstadoPrestamo.ACTIVO;
}
