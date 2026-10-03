package com.example.book_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final BookScraper bookScraper = new BookScraper();
    private final BookCatalogScraper catalogScraper = new BookCatalogScraper();

    @GetMapping("/hello")
    public String hello() {
        return "Book API is working!";
    }

    @GetMapping("/api/books")
    public List<Book> getBooks(
            @RequestParam(defaultValue = "1") int page) throws IOException {

        List<String> bookUrls = catalogScraper.getBookUrls();

        int start = (page - 1) * 20;
        int end = Math.min(start + 20, bookUrls.size());

        if (start < 0 || start >= bookUrls.size()) {
            throw new IllegalArgumentException("Invalid page number");
        }

        List<Book> books = new ArrayList<>();

        for (int i = start; i < end; i++) {
            books.add(bookScraper.scrapeBook(bookUrls.get(i)));
        }

        return books;
    }

    @GetMapping("/api/books/{id}")
    public Book getBook(@PathVariable int id) throws IOException {

        List<String> bookUrls = catalogScraper.getBookUrls();

        if (id < 1 || id > bookUrls.size()) {
            throw new IllegalArgumentException("Book not found");
        }

        return bookScraper.scrapeBook(bookUrls.get(id - 1));
    }

    @GetMapping("/api/search")
    public List<Book> searchBooks(
            @RequestParam String title) throws IOException {

        List<String> matchingUrls =
                catalogScraper.searchBookUrls(title);

        List<Book> results = new ArrayList<>();

        for (String url : matchingUrls) {
            results.add(bookScraper.scrapeBook(url));
        }

        return results;
    }
}