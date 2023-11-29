package usecase_adaptor.MainMenu;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainMenuViewModel extends ViewModel {

    public final String TITLE_LABEL = "Main Menu";

    public final String SHARE_WATCHLIST_BUTTON_LABEL = "Share Watchlist to other users";

    public final String Get_WATCHLIST_BUTTON_LABEL = "get Watchlist";

    private MainMenuState state = new MainMenuState();

    private String loggedInUser;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public MainMenuViewModel() {
        super("Main Menu");
    }

    public void setState(MainMenuState state) {
        this.state = state;
    }

    public void setLoggedInUser(String userName) {
        this.loggedInUser = userName;
    }

    public MainMenuState getState() {
        return state;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("MainMenuState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
