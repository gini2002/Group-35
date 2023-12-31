package entity;

import java.time.LocalDateTime;

public interface UserFactory {
    User create(String name, String password, LocalDateTime ldt, SearchHistory searchHistory, Watchlist watchlist);
    User create(String name, String password, LocalDateTime ldt);

}
