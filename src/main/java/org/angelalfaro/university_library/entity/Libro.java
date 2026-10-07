package org.angelalfaro.university_library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libros", indexes = {
        @Index(name = "idx_libro_isbn", columnList = "isbn"),
        @Index(name = "idx_libro_categoria", columnList = "categoria")
})
@Getter
@Setter
@NoArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String isbn;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String autor;

    @Column(nullable = false, length = 100)
    private String categoria;

    @Column(nullable = false)
    private Integer stockTotal;

    @Column(nullable = false)
    private Integer stockDisponible;

    public Libro(Long id, String isbn, String titulo, String autor, String categoria, Integer stockTotal, Integer stockDisponible) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.stockTotal = stockTotal;
        this.stockDisponible = stockDisponible;
    }

    public static LibroBuilder builder() {
        return new LibroBuilder();
    }

    public static class LibroBuilder {
        private Long id;
        private String isbn;
        private String titulo;
        private String autor;
        private String categoria;
        private Integer stockTotal;
        private Integer stockDisponible;

        LibroBuilder() {
        }

        public LibroBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LibroBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public LibroBuilder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public LibroBuilder autor(String autor) {
            this.autor = autor;
            return this;
        }

        public LibroBuilder categoria(String categoria) {
            this.categoria = categoria;
            return this;
        }

        public LibroBuilder stockTotal(Integer stockTotal) {
            this.stockTotal = stockTotal;
            return this;
        }

        public LibroBuilder stockDisponible(Integer stockDisponible) {
            this.stockDisponible = stockDisponible;
            return this;
        }

        public Libro build() {
            return new Libro(id, isbn, titulo, autor, categoria, stockTotal, stockDisponible);
        }

        @Override
        public String toString() {
            return "Libro.LibroBuilder(id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor
                    + ", categoria=" + categoria + ", stockTotal=" + stockTotal + ", stockDisponible=" + stockDisponible + ")";
        }
    }
}
