package usecase_adaptor.MainMenu;

public class MainMenuState {

    private String userName = "";

    public MainMenuState() {}

    public MainMenuState(MainMenuState copy) {
        this.userName = copy.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
