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
    private int fiveStarReview;
    private String[] title;

    /**
     * @param index
     * @param name
     * @param author
     * @param orignialLanguage
     * @param firstPublished
     * @param milionsales
     * @param genre
     */
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

    /**
     * @param index
     */

    public void setIndex(int index) {
        this.index = index;
    }

    /**

     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param title
     */
    public void setTitle(String[] title){
        this.title = title;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return
     */
    public String getOrignialLanguage() {
        return orignialLanguage;
    }

    /**
     * @return
     */
    public int getFirstPublished() {
        return firstPublished;
    }

    /**
     * @return
     */
    public float getMilionsales() {
        return milionsales;
    }


    /**
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }


    /**
     * @return
     */
    @Override
    public String toString() {
        return "Book [" + index + "],{" +
                "Book='" + name + '\'' +
                ", Author(s)='" + author + '\'' +
                ", Original language='" + orignialLanguage + '\'' +
                ", First published=" + firstPublished +
                ", Approximate sales in millions=" + milionsales +
                ", Genre='" + genre + '\'' +
                '}';
    }

    @Override
    public boolean download(User user) {
        return false;
    }

    @Override
    public boolean read(User user) {
        return false;
    }
}