package dental.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dental.office.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {

}
