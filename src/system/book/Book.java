package system.book;

import system.exception.BookException;
import system.user.User;
import system.util.SystemUtil;

public class Book implements BookDownloadable,BookReadable{
    private int index;
    private String name;
    private String author;
    private String orignialLanguage;
    private int firstPublished;
    private float milionsales;
    private String genre;
    private String[] title;


    public Book(int index, String name, String author, String orignialLanguage, int firstPublished, float milionsales, String genre) {
        super();
        this.index = index;
        this.name = name;
        this.author = author;
        this.orignialLanguage = orignialLanguage;
        this.firstPublished = firstPublished;
        this.milionsales = milionsales;
        this.genre = genre;

    }


    public void setIndex(int index) {
        this.index = index;
    }


    public int getIndex() {
        return index;
    }


    public void setTitle(String[] title){
        this.title = title;
    }


    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }


    public String getOrignialLanguage() {
        return orignialLanguage;
    }

    public int getFirstPublished() {
        return firstPublished;
    }

    public float getMilionsales() {
        return milionsales;
    }


    public String getGenre() {
        return genre;
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String[] getTitle() {
        return title;
    }
    //done
    @Override
    public String toString() {
        return "Book [" + getIndex() + "],{" +
                "Book='" + getName() + '\'' +
                ", Author(s)='" + getAuthor() + '\'' +
                ", Original language='" + getOrignialLanguage() + '\'' +
                ", First published=" + getFirstPublished() +
                ", Approximate sales in millions=" + getMilionsales() +
                ", Genre='" + getGenre() + '\'' +
                '}';
    }

    @Override
    public boolean download(User user) {
        return false;
    }

    @Override
    public boolean read(User user) {
        return true;
    }

    public static Book createBook(String index, String name, String author, String originalLanguage, String firstPublished, String millionsales, String genre) {
        if (index == null || name == null || author == null || originalLanguage == null || genre == null ||
            index.isEmpty() || name.isEmpty() || author.isEmpty() || originalLanguage.isEmpty() || genre.isEmpty()) {
            return null;
        }

        try {
            int indexValue = Integer.parseInt(index);
            int firstPublishedValue = Integer.parseInt(firstPublished);
            float millionsalesValue = Float.parseFloat(millionsales);
            return new Book(indexValue, name, author, originalLanguage, firstPublishedValue, millionsalesValue, genre);
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
