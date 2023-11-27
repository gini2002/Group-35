package use_case.clear_users;


public interface ClearOutputBoundary {
    void clearSuccessView(ClearOutputData response);
    void clearFailView(String error);
}