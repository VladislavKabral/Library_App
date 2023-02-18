package by.kabral.springcourse.libraryapp.LibraryApp.repositories;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
