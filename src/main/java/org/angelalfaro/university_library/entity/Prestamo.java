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

    public Prestamo(Long id, Usuario usuario, Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucionEsperada, LocalDate fechaDevolucionReal, EstadoPrestamo estado) {
        this.id = id;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
    }

    public static PrestamoBuilder builder() {
        return new PrestamoBuilder();
    }

    public static class PrestamoBuilder {
        private Long id;
        private Usuario usuario;
        private Libro libro;
        private LocalDate fechaPrestamo;
        private LocalDate fechaDevolucionEsperada;
        private LocalDate fechaDevolucionReal;
        private EstadoPrestamo estado = EstadoPrestamo.ACTIVO;

        PrestamoBuilder() {
        }

        public PrestamoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PrestamoBuilder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public PrestamoBuilder libro(Libro libro) {
            this.libro = libro;
            return this;
        }

        public PrestamoBuilder fechaPrestamo(LocalDate fechaPrestamo) {
            this.fechaPrestamo = fechaPrestamo;
            return this;
        }

        public PrestamoBuilder fechaDevolucionEsperada(LocalDate fechaDevolucionEsperada) {
            this.fechaDevolucionEsperada = fechaDevolucionEsperada;
            return this;
        }

        public PrestamoBuilder fechaDevolucionReal(LocalDate fechaDevolucionReal) {
            this.fechaDevolucionReal = fechaDevolucionReal;
            return this;
        }

        public PrestamoBuilder estado(EstadoPrestamo estado) {
            this.estado = estado;
            return this;
        }

        public Prestamo build() {
            return new Prestamo(id, usuario, libro, fechaPrestamo, fechaDevolucionEsperada, fechaDevolucionReal, estado);
        }

        @Override
        public String toString() {
            return "Prestamo.PrestamoBuilder(id=" + id + ", usuario=" + usuario + ", libro=" + libro
                    + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucionEsperada=" + fechaDevolucionEsperada
                    + ", fechaDevolucionReal=" + fechaDevolucionReal + ", estado=" + estado + ")";
        }
    }
}
