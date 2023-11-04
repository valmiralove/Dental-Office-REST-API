package dental.office.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dental.office.controller.model.DentalOfficeData;
import dental.office.controller.model.DentalOfficeData.PatientData;
import dental.office.controller.model.DentalOfficeData.StaffData;
import dental.office.service.DentalOfficeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dental_office")
@Slf4j
public class DentalOfficeController {
	
	@Autowired
	private DentalOfficeService dentalOfficeService;

	
	/*
	 * CRUD operations for Dental Office
	 */
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public DentalOfficeData createDentalOffice (@RequestBody DentalOfficeData dentalOfficeData) {
		log.info("Creating Dental Office {}", dentalOfficeData);
		return dentalOfficeService.saveDentalOffice(dentalOfficeData);
	}
	
	@PutMapping("/{dentalOfficeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public DentalOfficeData updateDentalOffice (@PathVariable Long dentalOfficeId, @RequestBody DentalOfficeData dentalOfficeData) {
		dentalOfficeData.setDentalOfficeId(dentalOfficeId);
		log.info("Updating Dental Office {}", dentalOfficeData);
		return dentalOfficeService.saveDentalOffice(dentalOfficeData);
	}
	
	@GetMapping("/{dentalOfficeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public DentalOfficeData retrieveDentalOfficeById(@PathVariable Long dentalOfficeId) {
		log.info("Dental Office with ID {}", dentalOfficeId);
		return dentalOfficeService.retrieveDentalOfficeById(dentalOfficeId);
	}
	
	@GetMapping
	public List<DentalOfficeData> retrieveAllDentalOffices(){
		log.info("Retrieving all Dental Offices");
		return dentalOfficeService.retrieveAllDentalOffices();
	}
	
	@DeleteMapping("/{dentalOfficeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Map<String, String> deleteDentalOffice(@PathVariable Long dentalOfficeId) {
		log.info("Deleting Dental Office with ID {}", dentalOfficeId);
		dentalOfficeService.deleteDentalOffice(dentalOfficeId);
		
		return Map.of("message", "Dental Office with ID " + dentalOfficeId + " was deleted successfully.");
	}
	
	/*
	 * CRUD operations for Patient
	 */
	
	@PostMapping("/{dentalOfficeId}/patient")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PatientData createPatient(@PathVariable Long dentalOfficeId, @RequestBody PatientData patientData) {
		log.info("Creating Patient {}", patientData);
		return dentalOfficeService.savePatient(dentalOfficeId, patientData);
	}
	
	@PutMapping("/{dentalOfficeId}/patient/{patientId}")
	@ResponseStatus(code = HttpStatus.OK)
	public PatientData updatePatient (@PathVariable Long dentalOfficeId, @PathVariable Long patientId, @RequestBody PatientData patientData) {
		patientData.setPatientId(patientId);
		log.info("Updating Patient {}", patientData);
		return dentalOfficeService.savePatient(dentalOfficeId, patientData);
	}
	
	@GetMapping("/{dentalOfficeId}/patient/{patientId}")
	@ResponseStatus(code = HttpStatus.OK)
	public PatientData retrievePatientById(@PathVariable Long dentalOfficeId, @PathVariable Long patientId) {
		log.info("Patient with ID {}", patientId);
		return dentalOfficeService.retrievePatientById(dentalOfficeId, patientId);
	}
	
	@DeleteMapping("/{dentalOfficeId}/patient/{patientId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Map<String, String> deletePatient(@PathVariable Long dentalOfficeId, @PathVariable Long patientId){
		log.info("Deleting Patient with ID {}", patientId);
		dentalOfficeService.deletePatient(dentalOfficeId, patientId);
		
		return Map.of("message", "Patient with ID " + patientId + " was deleted successfully.");
	}
	
	/*
	 * CRUD operations for Staff
	 */
	
	@PostMapping("/{dentalOfficeId}/staff")
	@ResponseStatus(code = HttpStatus.CREATED)
	public StaffData createStaff(@PathVariable Long dentalOfficeId, @RequestBody StaffData staffData) {
		log.info("Creating Staff {}", staffData);
		return dentalOfficeService.saveStaff(dentalOfficeId, staffData);
	}
	
	@PutMapping("/{dentalOfficeId}/staff/{staffId}")
	@ResponseStatus(code = HttpStatus.OK)
	public StaffData updateStaff(@PathVariable Long dentalOfficeId, @PathVariable Long staffId, @RequestBody StaffData staffData) {
		staffData.setStaffId(staffId);
		log.info("Updating Staff {}", staffData);
		return dentalOfficeService.saveStaff(dentalOfficeId, staffData);
	}
	
	@GetMapping("/{dentalOfficeId}/staff/{staffId}")
	@ResponseStatus(code = HttpStatus.OK)
	public StaffData retrieveStaffById (@PathVariable Long dentalOfficeId, @PathVariable Long staffId) {
		log.info("Staff with ID {}", staffId);
		return dentalOfficeService.retrieveStaffById(dentalOfficeId, staffId);
	}
	
	@DeleteMapping("/{dentalOfficeId}/staff/{staffId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Map<String, String> deleteStaff(@PathVariable Long dentalOfficeId, @PathVariable Long staffId){
		log.info("Deleting Staff with ID " + staffId + " was deleted successfully");
		dentalOfficeService.deleteStaff(dentalOfficeId, staffId);
		
		return Map.of("message", "Staff with ID " + staffId + " was deleted successfully.");
	}
	
	
	

}
