package org.angelalfaro.university_library.dto.Libro;

import lombok.Data;

@Data
public class LibroResponseDto {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private Integer stockTotal;
    private Integer stockDisponible;

    public LibroResponseDto() {
    }

    public LibroResponseDto(Long id, String isbn, String titulo, String autor, String categoria, Integer stockTotal, Integer stockDisponible) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.stockTotal = stockTotal;
        this.stockDisponible = stockDisponible;
    }

    public static LibroResponseDtoBuilder builder() {
        return new LibroResponseDtoBuilder();
    }

    public static class LibroResponseDtoBuilder {
        private Long id;
        private String isbn;
        private String titulo;
        private String autor;
        private String categoria;
        private Integer stockTotal;
        private Integer stockDisponible;

        LibroResponseDtoBuilder() {
        }

        public LibroResponseDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LibroResponseDtoBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public LibroResponseDtoBuilder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public LibroResponseDtoBuilder autor(String autor) {
            this.autor = autor;
            return this;
        }

        public LibroResponseDtoBuilder categoria(String categoria) {
            this.categoria = categoria;
            return this;
        }

        public LibroResponseDtoBuilder stockTotal(Integer stockTotal) {
            this.stockTotal = stockTotal;
            return this;
        }

        public LibroResponseDtoBuilder stockDisponible(Integer stockDisponible) {
            this.stockDisponible = stockDisponible;
            return this;
        }

        public LibroResponseDto build() {
            return new LibroResponseDto(id, isbn, titulo, autor, categoria, stockTotal, stockDisponible);
        }

        @Override
        public String toString() {
            return "LibroResponseDto.LibroResponseDtoBuilder(id=" + id + ", isbn=" + isbn + ", titulo=" + titulo
                    + ", autor=" + autor + ", categoria=" + categoria + ", stockTotal=" + stockTotal
                    + ", stockDisponible=" + stockDisponible + ")";
        }
    }
}