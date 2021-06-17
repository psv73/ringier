package net.psv73.ringier.service;

import lombok.NoArgsConstructor;
import net.psv73.ringier.model.GeoIP;
import net.psv73.ringier.model.Login;
import net.psv73.ringier.model.LoginAgg;
import net.psv73.ringier.repository.GeoIPRepository;
import net.psv73.ringier.repository.LoginRepository;
import net.psv73.ringier.utils.GenerateIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private GeoIPRepository geoIPRepository;

    public Login saveLogs(Login login) {

        List<GeoIP> geoIP = geoIPRepository.findFirstByIp(GenerateIP.ipToLong(login.getIp()));
        login.setCountry(geoIP.size() > 0 ? geoIP.get(0).getShortName() : "-");

        loginRepository.save(login);

        return login;
    }

    public List<Login> getAll() {
        return loginRepository.findAll();
    }

    public ArrayList<Login> createList(String startIP, String endIP, int count) {

        ArrayList<Login> listIP = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            String newIP = GenerateIP.createIP(startIP, endIP);

            Login login = new Login();
            login.setTs(Instant.now().getEpochSecond());
            login.setIp(newIP);

            listIP.add(this.saveLogs(login));
        }

        return listIP;
    }

    public List<LoginAgg> getCountry(LocalDate date, int top) {

        List<LoginAgg> logins = new ArrayList<>();


        LocalDateTime ldtStart = LocalDateTime.of(date,  LocalTime.of(0,0,0));
        LocalDateTime ldtEnd = LocalDateTime.of(date, LocalTime.of(23,59, 59));
        long dateStart = ldtStart.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
        long dateEnd = ldtEnd.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();

        List<Tuple> list = loginRepository.getTopCountry(dateStart, dateEnd, top);

        int i = 0;

        for (Tuple element : list) {

            LoginAgg loginAgg = new LoginAgg();
            loginAgg.setOrder(++i);
            loginAgg.setDate(LocalDate.parse((CharSequence) element.get(0)));
            loginAgg.setCountry((String) element.get(1));
            loginAgg.setLogins((Integer) element.get(2));

            logins.add(loginAgg);
        }

        return logins;
    }

}
