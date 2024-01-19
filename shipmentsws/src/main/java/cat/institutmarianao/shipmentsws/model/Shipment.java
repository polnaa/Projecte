package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Table(name = "shipments")
/* Lombok */

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Shipment implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_DESCRIPTION = 500;

	public enum Category {
		PARTICULAR, COMPANY, GOVERNMENT
	}

	public static final String PENDING = "PENDING";
	public static final String IN_PROCESS = "IN_PROCESS";
	public static final String DELIVERED = "DELIVERED";

	public enum Status {
		PENDING, IN_PROCESS, DELIVERED
	}

	/* Lombok */
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false, name = "category")
	@Enumerated(EnumType.STRING)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
	private Address sender;

	@ManyToOne
	@JoinColumn(name = "recipient_id", referencedColumnName = "id", nullable = false)
	private Address recipient;

	private Float weight;
	private Float height;
	private Float width;
	private Float length;

	private Boolean express;
	private Boolean fragile;

	private String note;

	@OneToMany(mappedBy = "shipment")
	@Column(nullable = false)
	@OrderBy("date DESC")
	private List<Action> tracking;

	/* Hibernate */
	@Formula("(SELECT CASE a.type WHEN '" + Action.RECEPTION + "' THEN '" + PENDING + "' " + " WHEN '"
			+ Action.ASSIGNMENT + "' THEN '" + IN_PROCESS + "' " + " WHEN '" + Action.DELIVERY + "' THEN '" + DELIVERED
			+ "' ELSE NULL END FROM actions a "
			+ " WHERE a.date=( SELECT MAX(last_action.date) FROM actions last_action "
			+ " WHERE last_action.shipment_id=a.shipment_id AND last_action.shipment_id=id ))")
	// Lombok
	@Setter(AccessLevel.NONE)
	@Transient
	private Status status;

}
