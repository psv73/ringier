package net.psv73.ringier.controller;

import net.psv73.ringier.model.Login;
import net.psv73.ringier.model.LoginAgg;
import net.psv73.ringier.repository.GeoIPRepository;
import net.psv73.ringier.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LoginLogsAggService {

    @Autowired
    private LoginService loginService;

    @Autowired
    private GeoIPRepository geoIPRepository;

    public LoginLogsAggService() {}

    @PostMapping("/stat")
    public List<LoginAgg> getCountry(@RequestBody Map<String, Object> req) {

        LocalDate date = LocalDate.parse(req.get("date").toString());
        int top = Integer.parseInt(req.get("top").toString());

        System.out.println("ok");
        return loginService.getCountry(date, top);
    }

    @PostMapping("/addip")
    public ArrayList<Login> addNewLogins(@RequestBody Map<String, String> param) {

        return loginService.createList(param.get("startIP"), param.get("endIP"),
                Integer.parseInt(param.get("count")));
    }

    @PostMapping("/login")
    public Login login(@RequestBody Login login) {

        loginService.saveLogs(login);

        return login;
    }
}
