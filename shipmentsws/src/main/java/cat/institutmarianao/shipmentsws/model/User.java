package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "users")
//TODO decirla a JPA qué campo mirar para el mapping
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for role - MUST be constants (can not be enums) */
	public static final String RECEPTIONIST = "RECEPTIONIST";
	public static final String LOGISTICS_MANAGER = "LOGISTICS_MANAGER";
	public static final String COURIER = "COURIER";

	public enum Role {
		RECEPTIONIST, LOGISTICS_MANAGER, COURIER
	}
	
	public static final int MIN_USERNAME = 2;
	public static final int MAX_USERNAME = 25;
	public static final int MIN_PASSWORD = 10;
	public static final int MIN_FULL_NAME = 3;
	public static final int MAX_FULL_NAME = 100;
	public static final int MAX_EXTENSION = 9999;

	/* Lombok */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(nullable = false, unique = true)
	protected String username;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, insertable=false, updatable=false)
	protected Role role;
	
	@Column(nullable = false)
	protected String password;
	
	@Column(name="full_name", nullable = false)
	protected String fullName;

	protected Integer extension;
}
