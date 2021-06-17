package net.psv73.ringier.repository;

import net.psv73.ringier.model.Login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {

    List<Login> findAll();

    @Query(value = "SELECT DATE(ts, 'unixepoch') as date, country, count(ip) as logins FROM Login " +
            "WHERE ts >= ?1 AND ts <= ?2 GROUP by date, country ORDER BY date, logins DESC LIMIT ?3", nativeQuery = true)
    List<Tuple> getTopCountry(long dateStart, long dateEnd, int top);
}