package use_case.clear_users;

import java.util.ArrayList;

public class ClearInteractor implements ClearInputBoundary{
    final ClearUserDataAccessInterface userDataAccessObject;
    final ClearOutputBoundary clearPresenter;

    public ClearInteractor(ClearUserDataAccessInterface userDataAccessObject,
                           ClearOutputBoundary clearPresenter){
        this.userDataAccessObject = userDataAccessObject;
        this.clearPresenter = clearPresenter;
    }

    public void execute(ClearInputData clearinputdata) {
        ArrayList<String> clearedUsers = userDataAccessObject.getAllUsers();
        ClearOutputData clearOutputData = new ClearOutputData(clearinputdata.getdeleteconfirmation(), clearedUsers);
        if (clearedUsers.isEmpty()) {
            clearPresenter.clearFailView("No Users / Users already be cleared");
        }
        else {
            clearPresenter.clearSuccessView(clearOutputData);
            userDataAccessObject.clearAllUsers();
        }
    }
}

