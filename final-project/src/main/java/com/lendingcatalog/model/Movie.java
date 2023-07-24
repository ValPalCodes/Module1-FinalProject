package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;
import com.sun.jdi.LocalVariable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Movie implements CatalogItem {
    private String id;
    private String name;
    private String director;
    private LocalDate releaseDate;


    public Movie(String name, String director, String releaseDate) {
        this.name = name;
        this.director = director;
        this.releaseDate = LocalDate.parse(releaseDate);
    }

    @Override
    public String toString() {
        return "Movie: " + name + ", " + director + ", " + releaseDate + " , " + id;
    }

    @Override
    public boolean matchesName(String searchStr) {
        if(searchStr == null && name == null) {
            return true;
        }
        if( searchStr == null || name == null){
            return true;
        }
        return name.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return director.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return searchYear == releaseDate.getYear();
    }

    @Override
    public void registerItem() throws FileStorageException {
        id = UUID.randomUUID().toString();
        FileStorageService.writeContentsToFile(
            "Movie created: " + LocalDate.now() + " , " + LocalTime.now() + System.lineSeparator() + this.toString(),
            LOG_LOCATION + "movie-" + LocalDate.now() + ".log",
            true
        );
    }
}
