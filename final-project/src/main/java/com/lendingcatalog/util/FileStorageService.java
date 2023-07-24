package com.lendingcatalog.util;

import com.lendingcatalog.util.exception.FileStorageException;

<<<<<<< HEAD
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
=======
import java.io.*;
>>>>>>> 61ee5a133fc213b9007434c585a5413996a73386
import java.util.List;
import java.util.Scanner;

public class FileStorageService {

    // Requirement: File I/O
    public static void writeContentsToFile(String contents, String filename, boolean appendFile) throws FileStorageException {
<<<<<<< HEAD
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename, appendFile))) {
            writer.println(contents);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("File not found.");
=======
        try (
             FileOutputStream fileOutputStream2 = new FileOutputStream(filename, appendFile);
             PrintWriter writer2 = new PrintWriter(fileOutputStream2);
        ) {
            writer2.println(contents);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("File " + filename + " was not found.");
        } catch (IOException e) {
            throw new FileStorageException("Unable to close file output stream");
>>>>>>> 61ee5a133fc213b9007434c585a5413996a73386
        }
    }

    public static List<String> readContentsOfFile(String filename) throws FileStorageException {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            List<String> fileContentsOfFile = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                fileContentsOfFile.add(currentLine);
            }
            return fileContentsOfFile;

        } catch (FileNotFoundException e) {
            throw new FileStorageException("File not found.");
        }
    }
}

