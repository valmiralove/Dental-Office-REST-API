package dental.office.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class DentalOffice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dentalOfficeId;
	
	private String dentalOfficeName;
	private String dentalOfficeAddress;
	private String dentalOfficePhone;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "dentalOffice")
	private Set<Patient> patients = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "dentalOffice")
	private Set<Staff> staffs = new HashSet<>();
}

/*
 * "Owning class of @OTM"
 * specify cascade strategy 
 * define orphan removal behavior
 */