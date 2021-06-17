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
public class Login {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(updatable = false)
    private long ts;

    private String ip;

    private String country;

}
