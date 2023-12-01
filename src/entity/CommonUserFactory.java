package entity;
import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory{

    /**
     *
     * @param name name of user.
     * @param password password of user.
     * @param ltd time when user is created.
     * @param searchHistory user's search history.
     * @param watchlist user's watchlist.
     * @return the user.
     */
    @Override
    public User create(String name, String password, LocalDateTime ltd, SearchHistory searchHistory, Watchlist watchlist) {
        return new CommonUser(name, password, ltd, searchHistory, watchlist);
    }

    /**
     *
     * @param name user's name.
     * @param password user's password.
     * @param ltd user's creation time.
     * @return the user.
     */
    @Override
    public User create(String name, String password, LocalDateTime ltd) {
        return new CommonUser(name, password, ltd, new SearchHistory(), new Watchlist());
    }
}
