package system.user;

import system.book.Book;
import system.exception.UserException;
import java.util.List;
import java.util.ArrayList;


public class User {
    private String email;
    private String password;
    private List<Book> bookList = new ArrayList<>();
    private UserPlan plan;

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

    public User(String email, String password, UserPlan plan) {
        this.email = email;
        this.password = password;
        this.plan = plan;
    }

    public static User createUser(String... params) throws UserException {
        if (params.length != 4) throw new UserException("Invalid number of parameters");
        String email = params[0];
        String password = params[1];
        UserPlan plan;
        try {
            plan = UserPlan.createPlan(params[2], params[3]);
        } catch (Exception e) {
            throw new UserException("Invalid plan parameters");
        }
        return new User(email, password, plan);
    }

    public void addToBookList(Book book) throws UserException {
        if (!plan.isActive()) throw new UserException("User is not active");
        bookList.add(book);
    }

    public void displayBookList() {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public Book findBookByIndex(int index) throws UserException {
        if (index < 0 || index >= bookList.size()) throw new UserException("Index out of bounds");
        return bookList.get(index);
    }

    public void loadBookList() throws UserException {
        // Implementation here
    }

    public void saveBookList() throws UserException {
        // Implementation here
    }
}
