package use_case.clear_users;


public class ClearInputData {
    final private boolean clearInputData;
    public ClearInputData(boolean clearInputData){
        this.clearInputData = clearInputData;
    }
    boolean getdeleteconfirmation() {
        return clearInputData;
    }
}
