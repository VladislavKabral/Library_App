package by.kabral.springcourse.libraryapp.LibraryApp.utils;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PeopleValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
