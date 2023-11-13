package interface_adapter.clear_users;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ClearViewModel extends ViewModel {
    private ArrayList<String> clearedusers;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ClearState state = new ClearState();

    public ClearViewModel(){
        super("clear");
    }
    public void setState(ClearState clearState) {
        this.state = clearState;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public ClearState getState() {
        return state;
    }


    public String getClearedUsers() {
        String output = new String();
        for (String name : clearedusers){
            output += name + "\n";
        }
        return output;
    }

    public void setClearUser(ArrayList<String> clearedUsers) {
        this.clearedusers = clearedUsers;
    }
}
