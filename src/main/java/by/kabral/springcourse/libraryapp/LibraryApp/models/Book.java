package by.kabral.springcourse.libraryapp.LibraryApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Поле названия книги не должно быть пустым")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "В поле названия книги должны быть только буквы")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Поле автора книги не должно быть пустым")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "В поле автора книги должны быть только буквы")
    private String author;

    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getFullName() {
        return name + ", " + author + ", " + year;
    }
}
