package by.kabral.springcourse.libraryapp.LibraryApp.utils;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;

@Component
public class BooksValidator implements Validator {

    private static final int MIN_YEAR = 1800;

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if ((book.getYear() < MIN_YEAR) || (book.getYear() > Calendar.getInstance().get(Calendar.YEAR))) {
            errors.rejectValue("year", "", "Указано некоррекное значение годы выпуска книги");
        }
    }
}
