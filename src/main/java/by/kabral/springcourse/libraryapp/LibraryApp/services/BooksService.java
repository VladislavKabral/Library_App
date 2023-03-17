package by.kabral.springcourse.libraryapp.LibraryApp.services;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Book;
import by.kabral.springcourse.libraryapp.LibraryApp.models.Person;
import by.kabral.springcourse.libraryapp.LibraryApp.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
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
        booksRepository.save(currentBook);
    }
}