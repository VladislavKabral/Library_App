package by.kabral.springcourse.libraryapp.LibraryApp.controllers;

import by.kabral.springcourse.libraryapp.LibraryApp.models.Book;
import by.kabral.springcourse.libraryapp.LibraryApp.models.Page;
import by.kabral.springcourse.libraryapp.LibraryApp.models.Person;
import by.kabral.springcourse.libraryapp.LibraryApp.services.BooksService;
import by.kabral.springcourse.libraryapp.LibraryApp.services.CurrentDateService;
import by.kabral.springcourse.libraryapp.LibraryApp.services.PeopleService;
import by.kabral.springcourse.libraryapp.LibraryApp.utils.BooksValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final BooksValidator booksValidator;

    private final PeopleService peopleService;

    private final CurrentDateService currentDateService;

    private final static int ELEMENTS_ON_PAGE_COUNT = 5;

    @Autowired
    public BooksController(BooksService booksService, BooksValidator booksValidator, PeopleService peopleService, CurrentDateService currentDateService) {
        this.booksService = booksService;
        this.booksValidator = booksValidator;
        this.peopleService = peopleService;
        this.currentDateService = currentDateService;
    }

    @GetMapping
    public String booksHome(@RequestParam(defaultValue = "0") int pageIndex, Model model) {
        int listSize = booksService.findAll().size();
        int pageCount = (int) Math.ceil(listSize / (double) ELEMENTS_ON_PAGE_COUNT);
        Page page = new Page(pageCount);
        model.addAttribute("pageIndexes", page.getPageIndexes());
        model.addAttribute("books", booksService.getPageElements(pageIndex, ELEMENTS_ON_PAGE_COUNT));
        return "books/books";
    }

    @GetMapping("/{id}")
    public String bookHome(@PathVariable("id") int id, Model model, @ModelAttribute("selectedPerson") Person selectedPerson) {
        Book book = booksService.findById(id);
        model.addAttribute("book", book);

        if (book.isEmptyPerson()) {
            model.addAttribute("people", peopleService.findAll());
        } else {
            model.addAttribute("owner", book.getOwner());
        }

        return "books/book";
    }

    @GetMapping("/new")
    public String newBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "books/addBook";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        booksValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/addBook";
        }

        booksService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        booksValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/editBook";
        }

        booksService.update(id, book);

        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteBookPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/deleteBook";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/give")
    public String giveBook(@ModelAttribute("selectedPerson") Person selectedPerson, @PathVariable("id") int id) {
        booksService.giveBook(id, selectedPerson.getId());
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        booksService.releaseBook(id);
        return "redirect:/books";
    }

    @PostMapping("skip")
    public String skipTime() {
        currentDateService.updateCurrentDate();
        return "redirect:/books";
    }
}
