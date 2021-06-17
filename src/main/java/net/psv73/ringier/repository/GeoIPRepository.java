package net.psv73.ringier.repository;

import net.psv73.ringier.model.GeoIP;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoIPRepository extends CrudRepository<GeoIP, Long> {

    List<GeoIP> findAll();

    @Query("SELECT g FROM GeoIP g WHERE g.startIP < ?1 AND g.endIP > ?1")
    List<GeoIP> findFirstByIp(Long ip);
}

