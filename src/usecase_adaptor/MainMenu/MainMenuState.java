package usecase_adaptor.MainMenu;

public class MainMenuState {

    private String userName = "";

    /**
     * create a new main menu state.
     */
    public MainMenuState() {}

    /**
     * make a copy of existed main menu state.
     * @param copy a main menu state that want to be copied.
     */
    public MainMenuState(MainMenuState copy) {
        this.userName = copy.userName;
    }

    /**
     * save the logged-in user's username in state.
     * @param userName a string of logged-in user's userName.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * get the current logged-in user's username from state.
     * @return a string of logged-in user's username.
     */
    public String getUserName() {
        return userName;
    }
}
