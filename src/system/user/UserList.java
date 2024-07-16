package system.user;

import system.exception.UserException;

import java.io.*;
import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList = new ArrayList<>();

    public void addUser(User user) throws UserException {
        userList.add(user);
    }

    public void loadUserList(String csvFile) throws UserException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String email = data[0];
                String password = data[1];
                String planType = data[2];
                String activation = data[3];

                try {
                    UserPlan plan = UserPlan.createPlan(planType, activation);
                    boolean isActive = Boolean.parseBoolean(activation);
                    User user = new User(email, password, plan, isActive);
                    userList.add(user);
                } catch (Exception e) {
                    throw new UserException("Error creating user from CSV: " + e.getMessage());
                }
            }
            System.out.println("User list loaded successfully!");
        } catch (FileNotFoundException e) {
            throw new UserException("File not found: " + csvFile);
        }
    }

    public void saveUserList(String csvFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            for (User user : userList) {
                writer.write(String.format("%s,%s,%s,%s",
                        user.getEmail(),
                        user.getPassword(),
                        user.getPlan().getType().toString().toLowerCase(),
                        user.getPlan().isActive() ? "true" : "false"));
                writer.newLine();
            }
            System.out.println("User list saved successfully to file: " + csvFile);
        } catch (IOException e) {
            throw new RuntimeException(new UserException("Error saving user list to file: " + csvFile));
        }
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
