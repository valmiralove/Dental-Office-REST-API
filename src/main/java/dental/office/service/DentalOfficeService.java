package dental.office.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dental.office.controller.model.DentalOfficeData;
import dental.office.controller.model.DentalOfficeData.PatientData;
import dental.office.controller.model.DentalOfficeData.StaffData;
import dental.office.dao.DentalOfficeDao;
import dental.office.dao.PatientDao;
import dental.office.dao.StaffDao;
import dental.office.entity.DentalOffice;
import dental.office.entity.Patient;
import dental.office.entity.Staff;

@Service
public class DentalOfficeService {

	@Autowired
	private DentalOfficeDao dentalOfficeDao;

	@Autowired
	private PatientDao patientDao;
	
	@Autowired
	private StaffDao staffDao;

	@Transactional(readOnly = false)
	public DentalOfficeData saveDentalOffice(DentalOfficeData dentalOfficeData) {
		DentalOffice dentalOffice = dentalOfficeData.toDentalOffice();
		DentalOffice dbDentalOffice = dentalOfficeDao.save(dentalOffice);

		return new DentalOfficeData(dbDentalOffice);

	}

	@Transactional(readOnly = true)
	public DentalOfficeData retrieveDentalOfficeById(Long dentalOfficeId) {
		DentalOffice dentalOffice = findDentalOfficeById(dentalOfficeId);
		return new DentalOfficeData(dentalOffice);
	}

	private DentalOffice findDentalOfficeById(Long dentalOfficeId) {
		return dentalOfficeDao.findById(dentalOfficeId).orElseThrow(
				() -> new NoSuchElementException("Dental Office with ID " + dentalOfficeId + " was not found."));
	}

	@Transactional(readOnly = false)
	public void deleteDentalOffice(Long dentalOfficeId) {
		DentalOffice dentalOffice = findDentalOfficeById(dentalOfficeId);
		dentalOfficeDao.delete(dentalOffice);
	}

	@Transactional(readOnly = true)
	public List<DentalOfficeData> retrieveAllDentalOffices() {
		List<DentalOffice> dentalOfficeEntities = dentalOfficeDao.findAll();
		List<DentalOfficeData> dentalOfficeDtos = new LinkedList<>();

		for (DentalOffice dentalOffice : dentalOfficeEntities) {
			DentalOfficeData dentalofficeData = new DentalOfficeData(dentalOffice);
			dentalOfficeDtos.add(dentalofficeData);
		}

		return dentalOfficeDtos;
	}

	@Transactional(readOnly = false)
	public PatientData savePatient(Long dentalOfficeId, PatientData patientData) {
		DentalOffice dentalOffice = findDentalOfficeById(dentalOfficeId);
		Long patientId = patientData.getPatientId();
		Patient patient = findOrCreatePatient(dentalOfficeId, patientId);
		copyPatientFields(patientData, patient);
		patient.setDentalOffice(dentalOffice);
		dentalOffice.getPatients().add(patient);
		Patient dbPatient = patientDao.save(patient);
		

		return new PatientData(dbPatient);
	}

	private void copyPatientFields(PatientData patientData, Patient patient) {
		patient.setPatientId(patientData.getPatientId());
		patient.setPatientFirstName(patientData.getPatientFirstName());
		patient.setPatientLastName(patientData.getPatientLastName());
		patient.setPatientPhone(patientData.getPatientPhone());
	}

	private Patient findOrCreatePatient(Long dentalOfficeId, Long patientId) {
		if(Objects.isNull(patientId)) {
			return new Patient();
		} 
		return findPatientById(dentalOfficeId, patientId);
	}

	public PatientData retrievePatientById(Long dentalOfficeId, Long patientId) {
		return new PatientData(findPatientById(dentalOfficeId, patientId));
	}

	private Patient findPatientById(Long dentalOfficeId, Long patientId) {
		Patient patient = patientDao.findById(patientId).orElseThrow(() -> new NoSuchElementException("Patient with ID " + patientId + " was not found."));
		if(patient.getDentalOffice().getDentalOfficeId() != dentalOfficeId) {
			throw new IllegalArgumentException("Patient with Id " + patientId + " is not part of Dental Office with Id " + dentalOfficeId);
		} 
		
		return patient;
	}

	public void deletePatient(Long dentalOfficeId, Long patientId) {
		Patient patient = findPatientById(dentalOfficeId, patientId);
		patientDao.delete(patient);
	}

	public StaffData saveStaff(Long dentalOfficeId, StaffData staffData) {
		DentalOffice dentalOffice = findDentalOfficeById(dentalOfficeId);
		Long staffId = staffData.getStaffId();
		Staff staff = findOrCreateStaff(dentalOfficeId, staffId);
		copyStaffFields(staffData, staff);
		staff.setDentalOffice(dentalOffice);
		dentalOffice.getStaffs().add(staff);
		Staff dbStaff = staffDao.save(staff);
		
		return new StaffData(dbStaff);
	}

	private void copyStaffFields(StaffData staffData, Staff staff) {
		staff.setStaffId(staffData.getStaffId());
		staff.setStaffTitle(staffData.getStaffTitle());
		staff.setStaffName(staffData.getStaffName());
	}

	private Staff findOrCreateStaff(Long dentalOfficeId, Long staffId) {
		if(Objects.isNull(staffId)) {
			return new Staff();
		}
		return findStaffById(dentalOfficeId, staffId);
	}

	public StaffData retrieveStaffById(Long dentalOfficeId, Long staffId) {
		return new StaffData(findStaffById(dentalOfficeId, staffId));
	}

	private Staff findStaffById(Long dentalOfficeId, Long staffId) {
		Staff staff = staffDao.findById(staffId).orElseThrow(() -> new NoSuchElementException("Staff with ID " + staffId + " was not found."));
		if(staff.getDentalOffice().getDentalOfficeId() != dentalOfficeId) {
			throw new IllegalArgumentException("Staff with Id " + staffId + " is not part of Dental Office with Id " + dentalOfficeId);
		}
		
		return staff;
	}

	public void deleteStaff(Long dentalOfficeId, Long staffId) {
		Staff staff = findStaffById(dentalOfficeId, staffId);
		staffDao.delete(staff);
	}

}
