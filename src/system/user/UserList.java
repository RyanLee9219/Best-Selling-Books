package system.user;

import system.exception.UserException;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList = new ArrayList<>();

    public void addUser(User user) throws UserException {
        userList.add(user);
    }

    public void loadUserList(String csvFile) throws UserException {
        // Implementation here
    }

    public void saveUserList(String csvFile) throws UserException {
        // Implementation here
    }


    public User findUser(String email, String password) throws UserException {
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserException("User not found or invalid credentials");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }
}
