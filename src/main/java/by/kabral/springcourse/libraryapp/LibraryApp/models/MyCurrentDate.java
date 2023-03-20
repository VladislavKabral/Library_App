package by.kabral.springcourse.libraryapp.LibraryApp.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "My_Current_Date")
public class MyCurrentDate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "my_current_date_value")
    @Temporal(TemporalType.DATE)
    private Date currentDate;

    public MyCurrentDate() {
    }

    public MyCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public MyCurrentDate(int id, Date currentDate) {
        this.id = id;
        this.currentDate = currentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
