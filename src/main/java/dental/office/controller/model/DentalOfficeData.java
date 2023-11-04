package dental.office.controller.model;

import java.util.HashSet;
import java.util.Set;

import dental.office.entity.DentalOffice;
import dental.office.entity.Patient;
import dental.office.entity.Staff;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DentalOfficeData Class
 */

@Data
@NoArgsConstructor
public class DentalOfficeData {
	
	private Long dentalOfficeId;
	private String dentalOfficeName;
	private String dentalOfficeAddress;
	private String dentalOfficePhone;
	private Set<PatientData> patients = new HashSet<>();
	private Set<StaffData> staffs = new HashSet<>();
	
	public DentalOfficeData (DentalOffice dentalOffice) {
		this.dentalOfficeId = dentalOffice.getDentalOfficeId();
		this.dentalOfficeName = dentalOffice.getDentalOfficeName();
		this.dentalOfficeAddress = dentalOffice.getDentalOfficeAddress();
		this.dentalOfficePhone = dentalOffice.getDentalOfficePhone();
		
		for (Patient patient : dentalOffice.getPatients()) {
			this.patients.add(new PatientData(patient));
		}
		
		for(Staff staff : dentalOffice.getStaffs()) {
			this.staffs.add(new StaffData(staff));
		}
	}
	
	public DentalOfficeData (Long dentalOfficeId, String dentalOfficeName, String dentalOfficeAddress, String dentalOfficePhone) {
		this.dentalOfficeId = dentalOfficeId;
		this.dentalOfficeName = dentalOfficeName;
		this.dentalOfficeAddress = dentalOfficeAddress;
		this.dentalOfficePhone = dentalOfficePhone;
	}
	
	public DentalOffice toDentalOffice() {
		DentalOffice dentalOffice = new DentalOffice();
		
		dentalOffice.setDentalOfficeId(dentalOfficeId);
		dentalOffice.setDentalOfficeName(dentalOfficeName);
		dentalOffice.setDentalOfficeAddress(dentalOfficeAddress);
		dentalOffice.setDentalOfficePhone(dentalOfficePhone);
		
		for (PatientData patientData : patients) {
			dentalOffice.getPatients().add(patientData.toPatient());
		}
		
		for (StaffData staffData : staffs) {
			dentalOffice.getStaffs().add(staffData.toStaff());
		}
		
		return dentalOffice;
	}
	
	/*
	 * PatientData class
	 */

	@Data
	@NoArgsConstructor
	public static class PatientData {
		private Long patientId;
		private String patientFirstName;
		private String patientLastName;
		private String patientPhone;
		private Set<StaffData> staffs = new HashSet<>();
		
		public PatientData(Patient patient) {
			this.patientId = patient.getPatientId();
			this.patientFirstName = patient.getPatientFirstName();
			this.patientLastName = patient.getPatientLastName();
			this.patientPhone = patient.getPatientPhone();
			
			for (Staff staff : patient.getStaffs()) {
				this.staffs.add(new StaffData(staff));
			}
		}
		
		public Patient toPatient() {
			Patient patient = new Patient();
			
			patient.setPatientId(patientId);
			patient.setPatientFirstName(patientFirstName);
			patient.setPatientLastName(patientLastName);
			patient.setPatientPhone(patientPhone);
			
			for(StaffData staffData : staffs) {
				patient.getStaffs().add(staffData.toStaff());
			}
			
			return patient;
		}
		
		}
	
	/*
	 * StaffData class
	 */
	
	@Data
	@NoArgsConstructor
	public static class StaffData {
		private Long staffId;
		private String staffTitle;
		private String staffName;
		private Set<PatientData> patients = new HashSet<>();
		
		public StaffData(Staff staff) {
			this.staffId = staff.getStaffId();
			this.staffTitle = staff.getStaffTitle();
			this.staffName = staff.getStaffName();
			
//			for (Patient patient : staff.getPatients()) {
//				this.patients.add(new PatientData(patient));
//			}
		}
		
		public Staff toStaff() {
			Staff staff = new Staff();
			
			staff.setStaffId(staffId);
			staff.setStaffTitle(staffTitle);
			
			for (PatientData patientData : patients) {
				staff.getPatients().add(patientData.toPatient());
			}
			
			return staff;
		}
	}
	
}
