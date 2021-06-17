package net.psv73.ringier.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class LoginAgg {

    private LocalDate date;

    private int order;

    private String country;

    private int logins;
}
