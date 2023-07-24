package com.lendingcatalog.model;

import com.lendingcatalog.util.exception.FileStorageException;

public interface CatalogItem {
    boolean matchesName(String searchStr);

    boolean matchesCreator(String searchStr);

    boolean matchesYear(int searchYear);

    void registerItem() throws FileStorageException;

    String LOG_LOCATION = "C:\\Users\\Student\\workspace\\valeria-podoynitsyna-student-code\\java\\module-1\\week-9\\final-project\\src\\main\\resources\\logs\\";
}

