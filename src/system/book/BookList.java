package system.book;

import system.exception.BookException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> bestsellers = new ArrayList<>();

    public void loadBookList(String csvFile) throws BookException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Book book = Book.createBook(data);
                bestsellers.add(book);
            }
        } catch (IOException e) {
            throw new BookException("File not found or invalid file format");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Book book : bestsellers) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }

    public Book findBookByIndex(int index) throws BookException {
        for (Book book : bestsellers) {
            if (book.getIndex() == index) {
                return book;
            }
        }
        throw new BookException("Book not found");
    }

    public ArrayList<Book> searchInBookList(String keyword) throws BookException {
        if (keyword == null || keyword.isEmpty()) throw new BookException("Search keyword is empty");
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : bestsellers) {
            if (book.toString().contains(keyword)) {
                result.add(book);
            }
        }
        return result;
    }
}

