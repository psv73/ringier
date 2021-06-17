package net.psv73.ringier.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Data
@Access(AccessType.FIELD)
public class GeoIP {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Long startIP;

    private Long endIP;

    private String shortName;

    private String fullName;

}
