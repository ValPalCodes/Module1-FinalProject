package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Book implements CatalogItem{
    private String id;
    private String title;
    private String author;
    private LocalDate publishDate;


    public Book(String title, String author, String publishDate) {
        this.title = title;
        this.author = author;
        this.publishDate = LocalDate.parse(publishDate);
    }



    @Override
    public String toString() {
        return "Book: " + title + ", " + author + ", " + publishDate + " , " + id;
    }

    @Override
    public boolean matchesName(String searchStr) {
        if(searchStr == null && title == null) {
            return true;
        }
        if( searchStr == null || title == null){
            return true;
        }
        return title.equalsIgnoreCase(searchStr);

    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return author.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return searchYear == publishDate.getYear();
    }

    @Override
    public void registerItem() throws FileStorageException {
        id = UUID.randomUUID().toString();
        FileStorageService.writeContentsToFile(
            "Book created: " + LocalDate.now() + " , " + LocalTime.now() + System.lineSeparator() + this.toString(),
            LOG_LOCATION + "book-" + LocalDate.now() + ".log",
            true
        );
    }
}
