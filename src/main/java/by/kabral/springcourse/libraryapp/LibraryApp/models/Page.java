package by.kabral.springcourse.libraryapp.LibraryApp.models;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private final List<Integer> pageIndexes = new ArrayList<>();

    public Page(int pageCount) {
        for (int i = 0; i < pageCount; i++) {
            pageIndexes.add(i);
        }
    }

    public List<Integer> getPageIndexes() {
        return pageIndexes;
    }
}
