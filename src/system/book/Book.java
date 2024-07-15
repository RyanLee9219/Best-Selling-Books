package system.book;

import system.exception.BookException;
import system.util.SystemUtil;

public class Book {
    private int index;
    private String name;
    private String author;
    private String originalLanguage;
    private String genre;
    private int firstPublished;
    private float milionSales;

    public Book(int index, String name, String author, String originalLanguage, String genre, int firstPublished, float milionSales) {
        this.index = index;
        this.name = name;
        this.author = author;
        this.originalLanguage = originalLanguage;
        this.genre = genre;
        this.firstPublished = firstPublished;
        this.milionSales = milionSales;
    }

    public static Book createBook(String... params) throws BookException {
        if (params.length != 7) throw new BookException("Invalid number of parameters");
        if (!SystemUtil.isValid(params[0]) || !SystemUtil.isValid(params[1]) || !SystemUtil.isValid(params[2]) ||
                !SystemUtil.isValid(params[3]) || !SystemUtil.isValid(params[4]) || !SystemUtil.isValid(params[5]) ||
                !SystemUtil.isValid(params[6])) {
            throw new BookException("Invalid parameters");
        }

        int index;
        int firstPublished;
        float milionSales;
        try {
            index = Integer.parseInt(params[0]);
            firstPublished = Integer.parseInt(params[5]);
            milionSales = Float.parseFloat(params[6]);
        } catch (NumberFormatException e) {
            throw new BookException("Invalid number format");
        }
        return new Book(index, params[1], params[2], params[3], params[4], firstPublished, milionSales);
    }
}

    @Override
    public String toString() {
        return "Index: " + index + ", Name: " + name + ", Author: " + author + ", Original Language: " + originalLanguage +
                ", Genre: " + genre + ", First Published: " + firstPublished + ", Million Sales: " + milionSales;
    }
}
