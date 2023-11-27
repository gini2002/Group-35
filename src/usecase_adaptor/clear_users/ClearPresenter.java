package usecase_adaptor.clear_users;


import usecase_adaptor.ViewManagerModel;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearOutputData;


public class ClearPresenter implements ClearOutputBoundary {
    private final ClearViewModel clearViewModel;
    private ViewManagerModel viewManagerModel;

    public ClearPresenter(ClearViewModel clearViewModel,
                          ViewManagerModel viewManagerModel) {
        this.clearViewModel = clearViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void clearSuccessView(ClearOutputData response){
        ClearState clearState = clearViewModel.getState();
        clearState.setclearUser(response.getclearconfirmation());
        clearState.setClearedUsers(response.getClearedUsers());
        clearViewModel.setClearUser(response.getClearedUsers());
        clearViewModel.setState(clearState);
        clearViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(clearViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void clearFailView(String error) {
        ClearState clearState = clearViewModel.getState();
        clearState.setclearUserError(error);
        clearViewModel.firePropertyChanged();

    }
}
