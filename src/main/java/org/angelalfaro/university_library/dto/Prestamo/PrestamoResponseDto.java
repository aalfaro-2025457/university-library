package org.angelalfaro.university_library.dto.Prestamo;

import lombok.Data;
import org.angelalfaro.university_library.model.EstadoPrestamo;

import java.time.LocalDate;

@Data
public class PrestamoResponseDto {
    private Long id;
    private Long usuarioId;
    private String usuarioNombre;
    private Long libroId;
    private String libroTitulo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;
    private EstadoPrestamo estado;

    public PrestamoResponseDto() {
    }

    public PrestamoResponseDto(Long id, Long usuarioId, String usuarioNombre, Long libroId, String libroTitulo,
                               LocalDate fechaPrestamo, LocalDate fechaDevolucionEsperada, LocalDate fechaDevolucionReal,
                               EstadoPrestamo estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.libroId = libroId;
        this.libroTitulo = libroTitulo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
    }

    public static PrestamoResponseDtoBuilder builder() {
        return new PrestamoResponseDtoBuilder();
    }

    public static class PrestamoResponseDtoBuilder {
        private Long id;
        private Long usuarioId;
        private String usuarioNombre;
        private Long libroId;
        private String libroTitulo;
        private LocalDate fechaPrestamo;
        private LocalDate fechaDevolucionEsperada;
        private LocalDate fechaDevolucionReal;
        private EstadoPrestamo estado;

        PrestamoResponseDtoBuilder() {
        }

        public PrestamoResponseDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PrestamoResponseDtoBuilder usuarioId(Long usuarioId) {
            this.usuarioId = usuarioId;
            return this;
        }

        public PrestamoResponseDtoBuilder usuarioNombre(String usuarioNombre) {
            this.usuarioNombre = usuarioNombre;
            return this;
        }

        public PrestamoResponseDtoBuilder libroId(Long libroId) {
            this.libroId = libroId;
            return this;
        }

        public PrestamoResponseDtoBuilder libroTitulo(String libroTitulo) {
            this.libroTitulo = libroTitulo;
            return this;
        }

        public PrestamoResponseDtoBuilder fechaPrestamo(LocalDate fechaPrestamo) {
            this.fechaPrestamo = fechaPrestamo;
            return this;
        }

        public PrestamoResponseDtoBuilder fechaDevolucionEsperada(LocalDate fechaDevolucionEsperada) {
            this.fechaDevolucionEsperada = fechaDevolucionEsperada;
            return this;
        }

        public PrestamoResponseDtoBuilder fechaDevolucionReal(LocalDate fechaDevolucionReal) {
            this.fechaDevolucionReal = fechaDevolucionReal;
            return this;
        }

        public PrestamoResponseDtoBuilder estado(EstadoPrestamo estado) {
            this.estado = estado;
            return this;
        }

        public PrestamoResponseDto build() {
            return new PrestamoResponseDto(id, usuarioId, usuarioNombre, libroId, libroTitulo, fechaPrestamo,
                    fechaDevolucionEsperada, fechaDevolucionReal, estado);
        }

        @Override
        public String toString() {
            return "PrestamoResponseDto.PrestamoResponseDtoBuilder(id=" + id + ", usuarioId=" + usuarioId
                    + ", usuarioNombre=" + usuarioNombre + ", libroId=" + libroId + ", libroTitulo=" + libroTitulo
                    + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucionEsperada=" + fechaDevolucionEsperada
                    + ", fechaDevolucionReal=" + fechaDevolucionReal + ", estado=" + estado + ")";
        }
    }
}