package com.example.book_api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class BookScraper {

    public Book scrapeBook(String bookUrl) throws IOException {

        Document document = Jsoup.connect(bookUrl)
                .timeout(10000)
                .get();

        String title = document.select("h1").text();

        String price = document
                .select("div.product_main p.price_color")
                .text();

        String availability = document
                .select("div.product_main p.instock.availability")
                .text();

        String ratingClass = document
                .select("div.product_main p.star-rating")
                .attr("class");

        int rating = convertRating(ratingClass);

        Book book = new Book();

        book.setTitle(title);
        book.setPrice(price);
        book.setAvailability(availability);
        book.setRating(rating);

        return book;
    }

    private int convertRating(String ratingClass) {

        if (ratingClass.contains("One")) {
            return 1;
        } else if (ratingClass.contains("Two")) {
            return 2;
        } else if (ratingClass.contains("Three")) {
            return 3;
        } else if (ratingClass.contains("Four")) {
            return 4;
        } else if (ratingClass.contains("Five")) {
            return 5;
        }

        return 0;
    }
}