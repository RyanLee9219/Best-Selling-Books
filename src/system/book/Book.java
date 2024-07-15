package system.book;

import system.exception.BookException;
import system.user.User;
import system.util.SystemUtil;

public class Book implements BookDownloadable, BookReadable {
    private int index;
    private String name;
    private String author;
    private String originalLanguage;
    private String genre;
    private int firstPublished;
    private float millionSales;

    public Book(int index, String name, String author, String originalLanguage, String genre, int firstPublished, float millionSales) {
        this.index = index;
        this.name = name;
        this.author = author;
        this.originalLanguage = originalLanguage;
        this.genre = genre;
        this.firstPublished = firstPublished;
        this.millionSales = millionSales;
    }

    public static Book createBook(String... params) throws BookException {
        if (params.length != 7) throw new BookException("Invalid number of parameters");

        try {
            int index = Integer.parseInt(params[0]);
            String name = params[1];
            String author = params[2];
            String originalLanguage = params[3];
            String genre = params[4];
            int firstPublished = Integer.parseInt(params[5]);
            float millionSales = Float.parseFloat(params[6]);

            // Additional validation
            if (!SystemUtil.isValid(name) || !SystemUtil.isValid(author) || !SystemUtil.isValid(originalLanguage) ||
                    !SystemUtil.isValid(genre)) {
                throw new BookException("Invalid parameters");
            }

            return new Book(index, name, author, originalLanguage, genre, firstPublished, millionSales);
        } catch (NumberFormatException e) {
            throw new BookException("Invalid number format");
        }
    }

    @Override
    public String toString() {
        return "Index: " + index + ", Name: " + name + ", Author: " + author + ", Original Language: " + originalLanguage +
                ", Genre: " + genre + ", First Published: " + firstPublished + ", Million Sales: " + millionSales;
    }

    @Override
    public boolean download(User user) {
        // Implement download functionality
        return false;
    }

    @Override
    public boolean read(User user) {
        // Implement read functionality
        return false;
    }

    public int getIndex() {
        return 0;
    }
}
