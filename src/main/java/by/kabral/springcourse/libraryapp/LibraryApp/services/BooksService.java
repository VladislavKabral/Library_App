package by.kabral.springcourse.libraryapp.LibraryApp.services;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Book;
import by.kabral.springcourse.libraryapp.LibraryApp.models.Person;
import by.kabral.springcourse.libraryapp.LibraryApp.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    private final CurrentDateService currentDateService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService, CurrentDateService currentDateService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
        this.currentDateService = currentDateService;
    }

    public List<Book> findAll() {
        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> getPageElements(int index, int elementsCount) {
        return booksRepository.findAll(PageRequest.of(index, elementsCount, Sort.by("year"))).getContent();
    }

    public Book findById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void giveBook(int bookId, int personId) {
        Book currentBook = findById(bookId);
        Person owner = peopleService.findById(personId);
        currentBook.setId(bookId);
        currentBook.setOwner(owner);
        currentBook.setDateOfGiving(currentDateService.getCurrentDate().getDate());
        booksRepository.save(currentBook);
    }

    @Transactional
    public void releaseBook(int id) {
        Book book = findById(id);
        book.setId(id);
        book.setOwner(null);
        book.setDateOfGiving(null);
        booksRepository.save(book);
    }
}