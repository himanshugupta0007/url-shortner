/**
 *
 */
package com.mysocialpal.urlshortner.repository;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mysocialpal.urlshortner.entity.URLShortner;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Repository class for {@link URLShortner}
 *
 * @author Himanshu.Gupta
 * @version 1.0.0
 */
@Repository
public interface URLRepository extends JpaRepository<URLShortner, Long> {
    /**
     * Query to fetch URL by Aliasm
     */
    String FETCH_URL_BY_ALIAS =
            "select * from url_shortner_data usd where url_alias = :alias " +
                    "and is_active = true and is_deleted = false;";

    /**
     * Method fetches URL shortned by alias
     *
     * @param alias Alias of URL
     * @return URLShortner
     */
    @Query(nativeQuery = true, value = FETCH_URL_BY_ALIAS)
    URLShortner findURLByAlias(@Param("alias") String alias);

}
