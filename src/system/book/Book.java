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

    public String[] getTitle() {
        return title;
    }

    /**
     * @return
     */
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
        return false;
    }

    public static Book createBook(String... params)  {

        if (params.length != 7) {
            try {
                throw new BookException("Invalid number of parameters to create book list");
            } catch (BookException e) {
                throw new RuntimeException(e);
            }
        }
        for (String param : params) {
            if (!SystemUtil.isValid(param)) {
                try {
                    throw new BookException("Invalid parameter value.");
                } catch (BookException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        int index = Integer.parseInt(params[0]);
        String name = params[1];
        String author = params[2];
        String originalLanguage = params[3];
        int firstPublished = Integer.parseInt(params[4]);
        float millionSales = Float.parseFloat(params[5]);
        String genre = params[6];

        return new Book(index, name, author, originalLanguage, firstPublished, millionSales,genre);
    }

}
