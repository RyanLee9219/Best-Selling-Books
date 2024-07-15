package system;

import system.book.Book;
import system.book.BookList;
import system.exception.BookException;
import system.exception.UserException;
import system.user.User;
import system.user.UserList;

public class SystemManager {
    private User currentUser;
    private UserList userList;
    private BookList bookList;

    public SystemManager() {
        userList = new UserList();
        bookList = new BookList();
    }

    public void createUser(String... params) throws UserException {
        User user = User.createUser(params);
        userList.addUser(user);
    }

    public void showBookList() {
        System.out.println(bookList);
    }

    public void searchInBookList(String keyword) throws BookException {
        System.out.println(bookList.searchInBookList(keyword));
    }

    public void login(String email, String password) throws UserException {
        currentUser = userList.findUser(email, password);
    }

    public void exit() {
        currentUser = null;
    }

    public void addBookInMyList(int bookIndex) throws UserException, BookException {
        if (currentUser != null) {
            Book book = bookList.findBookByIndex(bookIndex);
            currentUser.addToBookList(book);
        } else {
            throw new UserException("No user is logged in");
        }
    }

    public void showMyBookList() throws UserException {
        if (currentUser != null) {
            currentUser.displayBookList();
        } else {
            throw new UserException("No user is logged in");
        }
    }

    public void readBook(int bookIndex) throws UserException {
        if (currentUser != null) {
            Book book = currentUser.findBookByIndex(bookIndex);
            // Implement the read functionality if applicable
        } else {
            throw new UserException("No user is logged in");
        }
    }

    public void downloadBook(int bookIndex) throws UserException {
        if (currentUser != null) {
            Book book = currentUser.findBookByIndex(bookIndex);
            // Implement the download functionality if applicable
        } else {
            throw new UserException("No user is logged in");
        }
    }

    public void logoff() {
        currentUser = null;
    }
}

dfaffa