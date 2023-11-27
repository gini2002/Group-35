package use_case.clear_users;


import java.util.ArrayList;

public interface ClearUserDataAccessInterface {
    ArrayList<String> getAllUsers();

    void clearAllUsers();
}
