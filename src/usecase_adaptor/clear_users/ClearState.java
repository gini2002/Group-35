package usecase_adaptor.clear_users;

import java.util.ArrayList;

public class ClearState {
    private ArrayList<String> clearedUsers;

    private boolean clearuser;
    private String clearUserError;

    public ClearState(ClearState copy) {
        clearuser = copy.clearuser;
        clearUserError = copy.clearUserError;
        clearedUsers = copy.clearedUsers;
    }
    public ClearState() {}

    public String getclearUserError() {
        return clearUserError;
    }

    public String setclearUserError(String error) {
        return this.clearUserError = error;
    }

    public boolean getclearUser() {
        return clearuser;
    }

    public boolean setclearUser(boolean clearuser) {
        return this.clearuser = clearuser;
    }

    public ArrayList<String> getClearedUsers(){
        return clearedUsers;
    }

    public ArrayList<String> setClearedUsers(ArrayList<String> clearedUsers){
        return this.clearedUsers = clearedUsers;
    }
}