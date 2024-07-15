package system.book;

import system.exception.BookException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.io.*;
import java.util.ArrayList;


public class BookList{
    private final ArrayList<Book> bestsellers = new ArrayList<>();;
    private final int NUMCOLS = 6;
    private String[] title;
    private final String[] DEFAULT_TITLE = {"Index", "Name", "Author", "Original Language", "First Published", "Million Sales", "Genre"};

    public BookList() {
    }

    /**
     * @param line
     * @return
     */

    public String[] lineReader(String line) {
        String[] str = new String[NUMCOLS];
        int index = 0;
        final char chComma = ',';
        final char chQuotes = '"';
        int start = 0;
        int end = line.indexOf(chComma);
        String value;
        while (start < end) {
            if (line.charAt(start) == chQuotes) {
                start++;
                end = line.indexOf(chQuotes, start + 1);
            }
            value = line.substring(start, end);
            value = value.trim();
            str[index++] = value;
            if (line.charAt(end) == chQuotes)
                start = end + 2;
            else
                start = end + 1;
            end = line.indexOf(chComma, start + 1);
        }
        if (start < line.length()) {
            value = line.substring(start);
            str[index++] = value;
        }
        return str;
    }

    /**
     * @param csvFile
     * @throws BookException
     * @throws IOException
     */
    public void createList(String csvFile) throws BookException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            int lineNumber = 0;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] data = lineReader(line);

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

    /**
     * @param pos
     * @param b
     */
    public void edit(int pos, Book b) {
        bestsellers.set(pos, b);
    }


    /**
     * @param index
     * @return
     */

    public Book findBookByIndex(int index) {
        if (index >= 0 && bestsellers.size() > index) return bestsellers.get(index);
        return null;
    }

    /**
     * @return
     */
    public int getSize() {
        return bestsellers.size();
    }

    /**
     * @param b
     */
    public void add(Book b) {
        try {
            int fp = b.getFirstPublished();
            float ms = b.getMilionsales();
            if (fp < 0 || fp > 2024) {
                throw new BookException("The year must be positive between 0 and 2024");
            }
            if (ms < 0) {
                throw new BookException("The number of sales should be positive.");
            }

            bestsellers.add(b);
            System.out.println("Book added successfully.");

        } catch (BookException e) {
        }
    }

    /**
     * @param pos
     */
    public void delete(int pos) {
        findBookByIndex(pos);
        Book remove = bestsellers.remove(pos);
    }

    /**
     * @param data
     */
    public void search(String data) {
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
}