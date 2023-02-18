package by.kabral.springcourse.libraryapp.LibraryApp.controllers;

import by.kabral.springcourse.libraryapp.LibraryApp.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public String booksHome(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String bookHome(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/book";
    }
}
