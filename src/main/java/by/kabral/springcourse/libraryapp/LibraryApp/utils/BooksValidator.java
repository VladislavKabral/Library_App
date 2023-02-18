package by.kabral.springcourse.libraryapp.LibraryApp.utils;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Book;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BooksValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
