package dental.office.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	
	@EqualsAndHashCode.Exclude
	private String patientFirstName;
	
	@EqualsAndHashCode.Exclude
	private String patientLastName;
	
	@EqualsAndHashCode.Exclude
	private String patientPhone;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "dental_office_id", nullable = false)
	private DentalOffice dentalOffice;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "patient_staff",
			joinColumns = @JoinColumn(name = "patient_id"),
			inverseJoinColumns = @JoinColumn(name = "staff_id")
			)
	private Set<Staff> staffs = new HashSet<>();

}


/*
 * "Owned" class of @MTO
 * specify join column
 * 
 * "Owning" class of @MTM and @JT
 * specify cascade strategy
 * specify join table
 */