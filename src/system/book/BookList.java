package system.book;

import system.exception.BookException;
import system.util.SystemUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.io.*;


public class BookList{
    private final ArrayList<Book> bestsellers = new ArrayList<>();;
    private final String[] DEFAULT_TITLE = {"Index", "Name", "Author", "Original Language", "First Published", "Million Sales", "Genre"};

    public BookList() {
    }

    /**
     * @param line
     * @return
     */



    /**
     * @param csvFile
     * @throws BookException
     * @throws IOException
     */
    public void loadBookList(String csvFile) throws BookException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            int lineNumber = 0;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = SystemUtil.lineReader(line);

                try {
                    int index = bestsellers.size();
                    String name = data[0];
                    String author = data[1];
                    String originalLanguage = data[2];
                    int firstPublished = Integer.parseInt(data[3]);
                    float millionsales = Float.parseFloat(data[4]);
                    String genre = data[5];

                    Book book = new Book(index, name, author, originalLanguage, firstPublished, millionsales, genre);
                    bestsellers.add(book);
                } catch (NumberFormatException e) {
                    throw new BookException("Skipping invalid record (number format error): " + line);
                }
            }
            System.out.println("Book list created successfully!");
        }
    }
    /**
     * @param csvFile
     * @throws BookException
     * @throws IOException
     */

    public void saveList(String csvFile) throws BookException, IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(String.join(",", DEFAULT_TITLE));
            writer.newLine();

            for (Book bestseller : bestsellers) {
                writer.write(bestseller.toString());
                writer.newLine();
            }

        } catch (FileNotFoundException fe) {
            throw new BookException("File not found");
        }
    }

    public void printList() {
        for (Book bestseller : bestsellers) {
            if (bestseller != null) {
                System.out.println(bestseller);
            }
        }
    }



    public void searchInBookList(String data) {
        if (data == null || data.isEmpty()) {
            System.err.println("Error: Search data cannot be empty ");
            System.out.println();
            return;
        }
        boolean found = false;
        for (Book bestseller : bestsellers) {
            if (bestseller != null) {
                if (bestseller.getName().toLowerCase().contains(data.toLowerCase())) {
                    System.out.println(bestseller);
                    found = true;
                }
                if (bestseller.getAuthor().toLowerCase().contains(data.toLowerCase())) {
                    System.out.println(bestseller);
                    found = true;
                }
                if (bestseller.getOrignialLanguage().toLowerCase().contains(data.toLowerCase())) {
                    System.out.println(bestseller);
                    found = true;
                }
                if (String.valueOf(bestseller.getFirstPublished()).toLowerCase().contains(data.toLowerCase())) {
                    System.out.println(bestseller);
                    found = true;
                }
                if (String.valueOf(bestseller.getMilionsales()).toLowerCase().contains(data.toLowerCase())) {
                    System.out.println(bestseller);
                    found = true;
                }
                if (bestseller.getGenre() != null && bestseller.getGenre().toLowerCase().contains(data.toLowerCase())) {
                    System.out.println(bestseller);
                    found = true;
                }
            }
        }

        if (!found) {
            System.err.print("No matching book found ");
            System.out.println();

        }
    }
    public Book findBookByIndex(int index) {
        if (index >= 0 && bestsellers.size() > index) return bestsellers.get(index);
        return null;
    }


}