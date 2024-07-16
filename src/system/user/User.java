package system.user;

import system.book.Book;
import system.exception.UserException;
import system.util.SystemUtil;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class User {
    private String email;
    private String password;
    private List<Book> bookList;
    private UserPlan plan;

    public User(String email, String password, UserPlan plan) {
        this.email = email;
        this.password = password;
        this.bookList = new ArrayList<>();
        this.plan = plan;
    }

    public User(String email, String password, UserPlan plan, boolean isActive) {
        this.email = email;
        this.password = password;
        this.bookList = new ArrayList<>();
        this.plan = plan;
        this.plan.setActive(isActive);
    }

    public UserPlan getPlan() {
        return plan;
    }

    public void setPlan(UserPlan plan) {
        this.plan = plan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User createUser(String... params) {
        if (params.length != 4) {
            throw new RuntimeException(new UserException("Invalid number of parameters"));
        }

        String email = params[0];
        String password = params[1];
        String planType = params[2];
        String activation = params[3];

        if (!SystemUtil.isValid(email) || !SystemUtil.isValid(password)) {
            throw new RuntimeException(new UserException("Invalid email or password"));
        }

        try {
            UserPlan plan = UserPlan.createPlan(planType, activation);
            boolean isActive = Boolean.parseBoolean(activation);
            return new User(email, password, plan, isActive);
        } catch (Exception e) {
            throw new RuntimeException(new UserException("Invalid plan parameters"));
        }
    }

    public void addToBookList(Book book) {
        if (!plan.isActive()) {
            throw new RuntimeException(new UserException("User is not active"));
        }
        bookList.add(book);
    }

    public void displayBookList() {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public Book findBookByIndex(int index) throws UserException {
        if (index < 0 || index >= bookList.size()) {
            throw new UserException("Index out of bounds");
        }
        return bookList.get(index);
    }

    public void loadBookList(String filePath) throws UserException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int index = Integer.parseInt(data[0]);
                String name = data[1];
                String author = data[2];
                String originalLanguage = data[3];
                int firstPublished = Integer.parseInt(data[4]);
                float millionsales = Float.parseFloat(data[5]);
                String genre = data[6];

                Book book = new Book(index, name, author, originalLanguage, firstPublished, millionsales, genre);
                bookList.add(book);
            }
            System.out.println("Book list loaded successfully!");
        } catch (FileNotFoundException e) {
            throw new UserException("File not found: " + filePath);
        } catch (NumberFormatException e) {
            throw new UserException("Error parsing numeric data in file: " + filePath);
        }
    }

    public void saveBookList(String filePath) throws UserException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : bookList) {
                writer.write(String.format("%s,%s,%s,%s,%d,%.2f,%s",
                        book.getIndex(),
                        book.getName(),
                        book.getAuthor(),
                        book.getOrignialLanguage(),
                        book.getFirstPublished(),
                        book.getMilionsales(),
                        book.getGenre()));
                writer.newLine();
            }
            System.out.println("Book list saved successfully to file: " + filePath);
        } catch (IOException e) {
            throw new UserException("Error saving book list to file: " + filePath);
        }
    }

    @Override
    public String toString() {
        return "Email: " + email + ", Plan: " + plan.getType().toString().toLowerCase() + ", IsActive: " + plan.isActive();
    }


    public int getBookListSize() {
        return bookList.size();
    }
}
