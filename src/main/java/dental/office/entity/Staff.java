package dental.office.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long staffId;
	
	@EqualsAndHashCode.Exclude
	private String staffTitle;
	
	@EqualsAndHashCode.Exclude
	private String staffName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "dental_office_id")
	private DentalOffice dentalOffice;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "staffs", cascade = CascadeType.PERSIST)
	private Set<Patient> patients = new HashSet<>();

}



/*
 * "Owned" class of @MTO
 * specify join column
 * 
 * "Owned" class of @MTM
 * declares mappedBy attribute (name of Java field in owning class)
*/