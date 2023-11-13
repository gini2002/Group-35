package interface_adapter.SearchByName;

public class SearchByNameViewModel {
    private SearchByNameState state = new SearchByNameState();

    private String name = "SearchByName";

    public SearchByNameViewModel() {
    }

    public void setState(SearchByNameState state) {
        this.state = state;
    }

    public SearchByNameState getState() {
        return state;
    }
}
