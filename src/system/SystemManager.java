package system;

import system.book.Book;
import system.book.BookList;
import system.exception.BookException;
import system.exception.UserException;
import system.user.User;
import system.user.UserList;

import java.util.Scanner;

public class SystemManager {
    private final int OPTION1_CRT = 1;
    private final int OPTION2_LST = 2;
    private final int OPTION3_ADD = 3;
    private final int OPTION4_EDT = 4;
    private final int OPTION5_DEL = 5;
    private final int OPTION6_SAV = 6;
    private final int OPTION7_SRC = 7;
    private final int OPTION8_EXT = 8;

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
        if (keyword.trim().isEmpty()) {
            System.err.println("Search string cannot be empty.");
            return;
        }
        System.out.println("Results from search ...");
        bookList.search(keyword);
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

    public void createBookList() {
        // Implementation for creating a book list
    }

    public void saveUserList() {
        // Implementation for saving the user list to a file
    }

    public void loadUserList() {
        // Implementation for loading the user list from a file
    }

    public void showUserList() {
        // Implementation for showing the user list
    }

    public void loginUser(String email, String password) throws UserException {
        login(email, password);
    }

    public void changePassword(User user, String newPassword) throws UserException {
        if (user != null) {
            user.setPassword(newPassword);
        } else {
            throw new UserException("No user is logged in");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        SystemManager manager = new SystemManager();
        Scanner scanner = new Scanner(System.in);

        try {
            manager.loadUserList(); // Load user data from file (initial setup)

            while (true) {
                if (manager.getCurrentUser() == null) {
                    // Admin Menu (Main Menu)
                    System.out.println("=== Admin Menu ===");
                    System.out.println("1. Create Book List");
                    System.out.println("2. Show Book List");
                    System.out.println("3. Search in Book List");
                    System.out.println("4. Create User");
                    System.out.println("5. Show User List");
                    System.out.println("6. Save User List");
                    System.out.println("7. Load User List");
                    System.out.println("8. Login User");
                    System.out.println("9. Exit");

                    System.out.print("Enter option: ");
                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (option) {
                        case 1:
                            manager.createBookList();
                            break;
                        case 2:
                            manager.showBookList();
                            break;
                        case 3:
                            System.out.print("Enter keyword to search: ");
                            String keyword = scanner.nextLine();
                            manager.searchInBookList(keyword);
                            break;
                        case 4:
                            System.out.print("Enter email: ");
                            String email = scanner.nextLine();
                            System.out.print("Enter password: ");
                            String password = scanner.nextLine();
                            System.out.print("Enter role: ");
                            String role = scanner.nextLine();
                            manager.createUser(email, password, role);
                            break;
                        case 5:
                            manager.showUserList();
                            break;
                        case 6:
                            manager.saveUserList();
                            System.out.println("User list saved successfully.");
                            break;
                        case 7:
                            manager.loadUserList();
                            System.out.println("User list loaded successfully.");
                            break;
                        case 8:
                            System.out.print("Enter email: ");
                            String loginEmail = scanner.nextLine();
                            System.out.print("Enter password: ");
                            String loginPassword = scanner.nextLine();
                            manager.loginUser(loginEmail, loginPassword);
                            if (manager.getCurrentUser() != null) {
                                System.out.println("Login successful as " + manager.getCurrentUser().getEmail());
                            }
                            break;
                        case 9:
                            System.out.println("Exiting...");
                            manager.exit();
                            return;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } else {
                    // User Menu (Logged in user)
                    System.out.println("=== User Menu ===");
                    System.out.println("10. Search in Book List");
                    System.out.println("11. Add Book in My List");
                    System.out.println("12. Show My Book List");
                    System.out.println("13. Read Book");
                    System.out.println("14. Download Book");
                    System.out.println("15. Change Password");
                    System.out.println("16. Logoff");

                    System.out.print("Enter option: ");
                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (option) {
                        case 10:
                            System.out.print("Enter keyword to search: ");
                            String keyword = scanner.nextLine();
                            manager.searchInBookList(keyword);
                            break;
                        case 11:
                            manager.showBookList();
                            System.out.print("Enter index of book to add: ");
                            int bookIndex = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            try {
                                manager.addBookInMyList(bookIndex);
                                System.out.println("Book added successfully.");
                            } catch (UserException | BookException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        case 12:
                            try {
                                manager.showMyBookList();
                            } catch (UserException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        case 13:
                            try {
                                manager.showMyBookList();
                                System.out.print("Enter index of book to read: ");
                                int readIndex = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                manager.readBook(readIndex);
                                System.out.println("Reading book...");
                            } catch (UserException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        case 14:
                            try {
                                manager.showMyBookList();
                                System.out.print("Enter index of book to download: ");
                                int downloadIndex = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                manager.downloadBook(downloadIndex);
                                System.out.println("Downloading book...");
                            } catch (UserException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        case 15:
                            System.out.print("Enter new password: ");
                            String newPassword = scanner.nextLine();
                            try {
                                manager.changePassword(manager.getCurrentUser(), newPassword);
                                System.out.println("Password changed successfully.");
                            } catch (UserException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        case 16:
                            System.out.println("Logging off...");
                            manager.logoff();
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            }
        } catch (UserException | BookException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
