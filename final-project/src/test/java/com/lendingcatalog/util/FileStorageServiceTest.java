package com.lendingcatalog.util;

import com.lendingcatalog.util.exception.FileStorageException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FileStorageServiceTest {
    public static final String TEST_DIRECTORY_PATH = "src/test/resources/temporary-test-files/";
    public static final String TEST_FILENAME = TEST_DIRECTORY_PATH + "testData.txt";

    @Before
    public void setup(){
        File dir = new File(TEST_DIRECTORY_PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }

//        File file = new File(TEST_FILENAME);
//        file.delete();
    }

    @After
    public void afterTest(){
        File file = new File(TEST_FILENAME);
       file.delete();
    }

    @Test(expected = FileStorageException.class)
    public void writeContentsToFile_throwExceptionWhenFileNotFound() throws FileStorageException {
            FileStorageService.writeContentsToFile("abc", "does_Not_exist_drive//:/src/test/resources/DOES_NOT_EXIST.txt", true);
    }

    @Test
    public void writeContentsToFile_savesDataToFile() throws FileStorageException, FileNotFoundException {
        FileStorageService.writeContentsToFile("abcdef", TEST_FILENAME, false);
        try (Scanner scanner = new Scanner(new File(TEST_FILENAME))) {
            assertEquals("abcdef", scanner.nextLine());
        };
    }

    @Test
    public void writeContentsToFile_appendsDataToFile() throws FileStorageException, FileNotFoundException {
        FileStorageService.writeContentsToFile("abcdef", TEST_FILENAME, false);
        FileStorageService.writeContentsToFile("ghijkl", TEST_FILENAME, true);

        Scanner scanner = new Scanner(new File(TEST_FILENAME));
        assertEquals("abcdef", scanner.nextLine());
        assertEquals("ghijkl", scanner.nextLine());
    }
}