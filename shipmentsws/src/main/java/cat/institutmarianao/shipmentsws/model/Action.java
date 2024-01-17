package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/*JPA*/
@Entity
@Table(name = "actions")
// Decirle a JPA qué campo mirar para el mapping
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Values for type - MUST be constants */
    public static final String RECEPTION = "RECEPTION";
    public static final String ASSIGNMENT = "ASSIGNMENT";
    public static final String DELIVERY = "DELIVERY";

    public enum Type {
        RECEPTION, ASSIGNMENT, DELIVERY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false)
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, insertable=false, updatable=false)
    protected Type type;

    @ManyToOne
    protected User performer;

    @Column(nullable = false, unique = true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "shipment_id", referencedColumnName = "id", nullable = false)
    protected Shipment shipment;

}
