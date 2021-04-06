package guru.springframework.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UnitOfMeasure {

	@Id
	@GeneratedValue
	private Long id;
	
	private String uom;
	
}
