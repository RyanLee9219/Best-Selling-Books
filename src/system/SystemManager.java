package system;

import system.book.Book;
import system.book.BookList;
import system.exception.BookException;
import system.exception.UserException;
import system.user.User;
import system.user.UserList;
import system.user.UserPlan;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SystemManager {
    private static final int OPTION1_CRT = 1;
    private static final int OPTION2_SHOW = 2;
    private static final int OPTION3_SRC = 3;
    private static final int OPTION4_CRT_USER = 4;
    private static final int OPTION5_SHOW_USER = 5;
    private static final int OPTION6_SAVE_USER_LIST = 6;
    private static final int OPTION7_LOAD_USER_LIST = 7;
    private static final int OPTION8_LOGIN = 8;
    private static final int OPTION9_EXT = 9;

    private static final int OPTION10_SRC = 10;
    private static final int OPTION11_ADD_BOOK_IN_MY_LIST = 11;
    private static final int OPTION12_SHOW_MY_BOOKLIST = 12;
    private static final int OPTION13_READBOOK = 13;
    private static final int OPTION14_DOWNLOADBOOK = 14;
    private static final int OPTION15_CHANGE_PASSWD = 15;
    private static final int OPTION16_LOGOFF = 16;

    private User currentUser;
    private UserList userList;
    private BookList bookList;

    private Scanner input;

    public SystemManager() {
        userList = new UserList();
        bookList = new BookList();
        input = new Scanner(System.in);
    }

    public void createUser() {
        try {
            System.out.print("- Email: ");
            String email = input.nextLine();

            System.out.print("- Password: ");
            String password = input.nextLine();

            System.out.print("- Plan type: [1]: trial, [2]: standard, [3]: vip: ");
            int planTypeIndex = input.nextInt();
            input.nextLine(); // Consume newline left-over
            UserPlan plan = createPlan(planTypeIndex);

            System.out.print("- Activation: [1]: activated, [2]: desactivated: ");
            int isActiveIndex = input.nextInt();
            input.nextLine(); // Consume newline left-over
            boolean isActive = isActiveIndex == 1;

            User user = new User(email, password, plan, isActive);
            userList.addUser(user);
            System.out.println("User successfully created!");
        } catch (UserException e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }

    private UserPlan createPlan(int planTypeIndex) throws UserException {
        switch (planTypeIndex) {
            case 1:
                return new UserPlan(UserPlan.PlanType.TRIAL, true); // Adjust UserPlan constructor as needed
            case 2:
                return new UserPlan(UserPlan.PlanType.STANDARD, true); // Adjust UserPlan constructor as needed
            case 3:
                return new UserPlan(UserPlan.PlanType.VIP, true); // Adjust UserPlan constructor as needed
            default:
                throw new UserException("Invalid plan type index.");
        }
    }



    public void showBookList() {
        bookList.printList();
    }

    public void searchInBookList() {
        System.out.print("Enter search keyword: ");
        String keyword = input.nextLine().trim();
        bookList.searchInBookList(keyword);
    }

    public void login(String email, String password) throws UserException {
        currentUser = userList.findUser(email, password);
        if (currentUser != null) {
            System.out.println("Login successful.");
        } else {
            throw new UserException("Login failed. Invalid email or password.");
        }
    }

    public void exit() {
        System.out.println("================================");
        System.out.println("|| [Application ended] ||");
        System.out.println("================================");

    }

    public void addBookInMyList() throws UserException, BookException {
        System.out.print("Enter index of the book to add: ");
        int bookIndex = input.nextInt();
        input.nextLine(); // Consume newline left-over

        if (currentUser != null) {
            Book book = bookList.findBookByIndex(bookIndex);
            currentUser.addToBookList(book);
            System.out.println("Book added to your list.");
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

    public void readBook() throws UserException, BookException {
        System.out.print("Enter index of the book to read: ");
        int bookIndex = input.nextInt();
        input.nextLine(); // Consume newline left-over

        if (currentUser != null) {
            Book book = currentUser.findBookByIndex(bookIndex);
            if (book != null) {
                System.out.println("Reading book: " + book.getName());
            } else {
                throw new BookException("Book not found in your list.");
            }
        } else {
            throw new UserException("No user is logged in");
        }
    }

    public void downloadBook() throws UserException, BookException {
        System.out.print("Enter index of the book to download: ");
        int bookIndex = input.nextInt();
        input.nextLine(); // Consume newline left-over

        if (currentUser != null) {
            Book book = currentUser.findBookByIndex(bookIndex);
            if (book != null) {
                System.out.println("Downloading book: " + book.getName());
            } else {
                throw new BookException("Book not found in your list.");
            }
        } else {
            throw new UserException("No user is logged in");
        }
    }

    public void logoff() {
        currentUser = null;
    }

    public void createBookList() throws BookException, IOException {
        System.out.print("Enter the name of the file to create book list: ");
        String fileName = input.nextLine().trim();
        File file = new File(fileName);

        if (!file.exists()) {
            throw new BookException("File '" + fileName + "' not found in the current directory.");
        }

        bookList.loadBookList(fileName);
    }

    public void saveUserList(String csvFile) throws UserException {
        userList.saveUserList(csvFile);
    }

    public void loadUserList(String csvFile) throws UserException, IOException {
        userList.loadUserList(csvFile);
    }

//    public void showUserList() {
//        userList.showUserList();
//    }

    public void loginUser() throws UserException {
        System.out.print("Enter email: ");
        String email = input.nextLine().trim();
        System.out.print("Enter password: ");
        String password = input.nextLine().trim();
        login(email, password);

    }

    public void changePassword() throws UserException {
        if (currentUser != null) {
            System.out.print("Enter new password: ");
            String newPassword = input.nextLine().trim();
            currentUser.setPassword(newPassword);
            System.out.println("Password changed successfully.");
        } else {
            throw new UserException("No user is logged in");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static void showAdminMenu() {
        System.out.println("=======================");
        System.out.println("|| Menu - Mini-System: OOP/A2 ||");
        System.out.println("=======================");
        System.out.println("1. Create Book List");
        System.out.println("2. Show Book List");
        System.out.println("3. Search in Book List");
        System.out.println("4. Create User");
        System.out.println("5. Show User List");
        System.out.println("6. Save User List");
        System.out.println("7. Load User List");
        System.out.println("8. Login User");
        System.out.println("9. Exit");
        System.out.println("=======================");
        System.out.print("Choose an option: ");
    }

    public static void showUserMenu() {
        System.out.println("=======================");
        System.out.println("|| User Menu ||");
        System.out.println("=======================");
        System.out.println("10. Search in Book List");
        System.out.println("11. Add Book in My List");
        System.out.println("12. Show My Book List");
        System.out.println("13. Read Book");
        System.out.println("14. Download Book");
        System.out.println("15. Change Password");
        System.out.println("16. Logoff");
        System.out.println("=======================");
        System.out.print("Choose an option: ");
    }
    public void runMenu() {
        boolean exit = false;

        while (!exit) {
            if (currentUser == null) {
                showAdminMenu();
                try {
                    int option = input.nextInt();
                    input.nextLine(); // Consume newline left-over

                    switch (option) {
                        case OPTION1_CRT:
                            createBookList();
                            break;
                        case OPTION2_SHOW:
                            showBookList();
                            break;
                        case OPTION3_SRC:
                            searchInBookList();
                            break;
                        case OPTION4_CRT_USER:
                            createUser();
                            break;
                        // case OPTION5_SHOW_USER:
                        //     showUserList();
                        //     break;
                        case OPTION6_SAVE_USER_LIST:
                            System.out.print("Enter the file name to save user list: ");
                            String saveFileName = input.nextLine().trim();
                            saveUserList(saveFileName);
                            break;
                        case OPTION7_LOAD_USER_LIST:
                            System.out.print("Enter the file name to load user list: ");
                            String loadFileName = input.nextLine().trim();
                            loadUserList(loadFileName);
                            break;
                        case OPTION8_LOGIN:
                            loginUser();
                            break;
                        case OPTION9_EXT:
                            exit = true;
                            exit();
                            break;
                        default:
                            System.out.println("Invalid option. Please choose a valid option.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a number.");
                    input.nextLine(); // Clear the invalid input
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                showUserMenu();
                try {
                    int option = input.nextInt();
                    input.nextLine();

                    switch (option) {
                        case OPTION10_SRC:
                            searchInBookList();
                            break;
                        case OPTION11_ADD_BOOK_IN_MY_LIST:
                            addBookInMyList();
                            break;
                        case OPTION12_SHOW_MY_BOOKLIST:
                            showMyBookList();
                            break;
                        case OPTION13_READBOOK:
                            readBook();
                            break;
                        case OPTION14_DOWNLOADBOOK:
                            downloadBook();
                            break;
                        case OPTION15_CHANGE_PASSWD:
                            changePassword();
                            break;
                        case OPTION16_LOGOFF:
                            logoff();
                            break;
                        default:
                            System.out.println("Invalid option. Please choose a valid option.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a number.");
                    input.nextLine(); // Clear the invalid input
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }


    public static void main(String[] args) {
        SystemManager systemManager = new SystemManager();

        systemManager.runMenu();
    }
}
