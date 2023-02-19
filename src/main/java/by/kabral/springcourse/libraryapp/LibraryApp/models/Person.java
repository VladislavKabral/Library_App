package by.kabral.springcourse.libraryapp.LibraryApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name")
    @NotEmpty(message = "Поле фамилии не должно быть пустым")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "В поле фамилии должны быть только буквы")
    private String lastName;

    @Column(name = "first_name")
    @NotEmpty(message = "Поле имени не должно быть пустым")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "В поле имени должны быть только буквы")
    private String firstName;

    @Column(name = "middle_name")
    @NotEmpty(message = "Поле отчества не должно быть пустым")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "В поле отчества должны быть только буквы")
    private String middleName;

    @Column(name = "year_of_birthday")
    private int yearOfBirthday;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public String getCustomerName() {
        return lastName + " " + firstName + " " + middleName + " " + yearOfBirthday;
    }
}
