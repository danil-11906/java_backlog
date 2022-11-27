package interfaces;

import javax.servlet.http.Cookie;

public interface CookiesRepository<T> {
    void save(Cookie userCookie, Long id);
}
