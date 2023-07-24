package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Tool implements CatalogItem {
    private String id;
    private final String type;
    private final String manufacturer;
    private final int count;


    public Tool(String type, String manufacturer, String count) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.count = Integer.parseInt(count);
    }

    @Override
    public String toString() {
        return "Tool: " + type + ", " + manufacturer + ", " + count + " , " + id;
    }

    @Override
    public boolean matchesName(String searchStr) {
        if(searchStr == null && type == null) {
            return true;
        }
        if( searchStr == null || type == null){
            return true;
        }
        return type.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return manufacturer.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return false;
    }

    @Override
    public void registerItem() throws FileStorageException {
        id = UUID.randomUUID().toString();
        FileStorageService.writeContentsToFile(
            "Tool created: " + LocalDate.now() + " , " + LocalTime.now() + System.lineSeparator() + this.toString(),
            LOG_LOCATION + "tool-" + LocalDate.now() + ".log",
            true
        );
    }
}
