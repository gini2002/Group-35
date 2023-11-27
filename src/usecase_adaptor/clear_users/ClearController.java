package usecase_adaptor.clear_users;

import usecase_adaptor.clear_users.ClearInputBoundary;
import usecase_adaptor.clear_users.ClearInputData;

public class ClearController {
    final ClearInputBoundary clearUseCaseInteractor;
    public ClearController(ClearInputBoundary clearUseCaseInteractor) {
        this.clearUseCaseInteractor = clearUseCaseInteractor;
    }
    public void execute(boolean clearinputdata) {
        ClearInputData clearInputData = new ClearInputData(clearinputdata);
        clearUseCaseInteractor.execute(clearInputData);
    }
}
