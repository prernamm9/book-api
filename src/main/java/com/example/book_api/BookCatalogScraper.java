package com.example.book_api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookCatalogScraper {

    private static final String BASE_URL =
            "https://books.toscrape.com/catalogue/";

    private List<String> cachedBookUrls = null;

    public Document getCatalogPage(int pageNumber) throws IOException {

        String url = BASE_URL + "page-" + pageNumber + ".html";

        return Jsoup.connect(url)
                .timeout(10000)
                .get();
    }

    public List<String> getBookUrls() throws IOException {

        if (cachedBookUrls != null) {
            return cachedBookUrls;
        }

        List<String> bookUrls = new ArrayList<>();

        for (int page = 1; page <= 50; page++) {

            Document document = getCatalogPage(page);

            for (Element link :
                    document.select("article.product_pod h3 a")) {

                bookUrls.add(link.absUrl("href"));
            }
        }

        cachedBookUrls = bookUrls;

        return cachedBookUrls;
    }

    public List<String> searchBookUrls(String title) throws IOException {

        List<String> matchingUrls = new ArrayList<>();

        String searchText = title.toLowerCase();

        for (int page = 1; page <= 50; page++) {

            Document document = getCatalogPage(page);

            for (Element link :
                    document.select("article.product_pod h3 a")) {

                String bookTitle = link.attr("title");

                if (bookTitle.toLowerCase().contains(searchText)) {
                    matchingUrls.add(link.absUrl("href"));
                }
            }
        }

        return matchingUrls;
    }
}