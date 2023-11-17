package entity;
import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory{
    @Override
    public User create(String name, String password, LocalDateTime ltd, SearchHistory searchHistory, Watchlist watchlist) {
        return new CommonUser(name, password, ltd, searchHistory, watchlist);
    }
}
