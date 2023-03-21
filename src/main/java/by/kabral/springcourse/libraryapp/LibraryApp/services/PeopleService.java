package by.kabral.springcourse.libraryapp.LibraryApp.services;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Book;
import by.kabral.springcourse.libraryapp.LibraryApp.models.Person;
import by.kabral.springcourse.libraryapp.LibraryApp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    private final CurrentDateService currentDateService;

    private final static int HOURS_IN_DAY = 24;

    private final static int MINUTES_IN_HOUR = 60;

    private final static int SECONDS_IN_MINUTE = 60;

    private final static int MILLISECONDS_IN_SECOND = 1000;

    private final static int MAX_COUNT_ALLOWED_DAYS = 10;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, CurrentDateService currentDateService) {
        this.peopleRepository = peopleRepository;
        this.currentDateService = currentDateService;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        Date currDate = currentDateService.getCurrentDate().getDate();
        Optional<Person> person = peopleRepository.findById(id);

        for (Book book: person.get().getBooks()) {
            long milliseconds = currDate.getTime() - book.getDateOfGiving().getTime();
            int days = (int) (milliseconds / (HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND));
            book.setExpired(days > MAX_COUNT_ALLOWED_DAYS);
        }

        return person.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
