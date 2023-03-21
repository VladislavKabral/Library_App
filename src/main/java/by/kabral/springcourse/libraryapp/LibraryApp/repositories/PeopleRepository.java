package by.kabral.springcourse.libraryapp.LibraryApp.repositories;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
