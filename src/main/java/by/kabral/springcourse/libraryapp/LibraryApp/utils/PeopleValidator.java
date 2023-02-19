package by.kabral.springcourse.libraryapp.LibraryApp.utils;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;

@Component
public class PeopleValidator implements Validator {

    private static final int MIN_YEAR_OF_BIRTHDAY = 1900;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if ((person.getYearOfBirthday() < MIN_YEAR_OF_BIRTHDAY) || (person.getYearOfBirthday() > Calendar.getInstance().get(Calendar.YEAR))) {
            errors.rejectValue("yearOfBirthday", "", "Указано некоррекное значение года рождения");
        }
    }
}
