package use_case.clear_users;
import java.util.ArrayList;

public class ClearOutputData {
    private final boolean useCaseFailed;
    private final boolean clearconfirmation;

    private final ArrayList<String> clearedUsers;

    public ClearOutputData(boolean clearconfirmation, ArrayList<String> clearedUsers) {
        this.useCaseFailed = false;
        this.clearconfirmation = clearconfirmation;
        this.clearedUsers = clearedUsers;
    }

    public boolean getclearconfirmation() {
        return clearconfirmation;
    }

    public ArrayList<String> getClearedUsers() {
        return clearedUsers;
    }

}