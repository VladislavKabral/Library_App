package by.kabral.springcourse.libraryapp.LibraryApp.services;

import by.kabral.springcourse.libraryapp.LibraryApp.models.MyCurrentDate;
import by.kabral.springcourse.libraryapp.LibraryApp.repositories.CurrentDateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CurrentDateService {

    private final CurrentDateRepository currentDateRepository;

    private final static int DATE_ID = 1;

    public CurrentDateService(CurrentDateRepository currentDateRepository) {
        this.currentDateRepository = currentDateRepository;
    }

    public MyCurrentDate getCurrentDate() {
        Optional<MyCurrentDate> currentDate = currentDateRepository.findById(DATE_ID);

        return currentDate.orElse(null);
    }

    @Transactional
    public void setCurrentDate(MyCurrentDate currentDate) {
        currentDate.setId(DATE_ID);
        currentDateRepository.save(currentDate);
    }

    @Transactional
    public void updateCurrentDate() {
        MyCurrentDate currentDate = getCurrentDate();
        Calendar calendar = Calendar.getInstance();
        Date prevDate = currentDate.getDate();
        calendar.setTime(prevDate);
        calendar.add(Calendar.DATE, 10);
        Date currDate = calendar.getTime();
        currentDate.setCurrentDate(currDate);
    }
}
