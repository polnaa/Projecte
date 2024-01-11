package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "addresses")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected Long id;

    private String name;

    private String street;
    private String number;
    private String floor;
    private String door;

    private String city;
    private String province;
    
    @Column(name = "postal_code")
    private String postalCode;

    private String country;
}
