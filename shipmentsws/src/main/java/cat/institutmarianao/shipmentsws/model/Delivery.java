package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)

@DiscriminatorValue(Action.DELIVERY)
public class Delivery extends Action implements Serializable {

	private static final long serialVersionUID = 1L;
}
