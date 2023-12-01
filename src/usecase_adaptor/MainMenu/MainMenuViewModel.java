package usecase_adaptor.MainMenu;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainMenuViewModel extends ViewModel {

    public final String SHARE_WATCHLIST_BUTTON_LABEL = "Share Watchlist to other users";

    public final String Get_WATCHLIST_BUTTON_LABEL = "get Watchlist";

    private MainMenuState state = new MainMenuState();

    private String loggedInUser;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * initiate a main menu view model.
     */
    public MainMenuViewModel() {
        super("Main_menu");
    }

    /**
     * save state into view model.
     * @param state a main menu state that is signed to this view model.
     */
    public void setState(MainMenuState state) {
        this.state = state;
    }

    /**
     * save logged-in user information.
     * @param userName a string of logged-in user's useName.
     */
    public void setLoggedInUser(String userName) {
        this.loggedInUser = userName;
    }

    /**
     * get the state saved in this view model.
     * @return the main menu state.
     */
    public MainMenuState getState() {
        return state;
    }

    /**
     * get logged-on user's name.
     * @return string of logged-in user's userName.
     */
    public String getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * remind listeners that there is something changed.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("MainMenuState", null, this.state);
    }

    /**
     * add a class to listen to this view model.
     * @param listener property change listener that may listen to this view model.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
