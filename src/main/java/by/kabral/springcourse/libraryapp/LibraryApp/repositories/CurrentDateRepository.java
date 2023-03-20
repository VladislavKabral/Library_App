package by.kabral.springcourse.libraryapp.LibraryApp.repositories;

import by.kabral.springcourse.libraryapp.LibraryApp.models.MyCurrentDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDateRepository extends JpaRepository<MyCurrentDate, Integer> {
}
