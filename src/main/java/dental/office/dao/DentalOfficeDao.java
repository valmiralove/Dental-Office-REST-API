package dental.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dental.office.entity.DentalOffice;

public interface DentalOfficeDao extends JpaRepository<DentalOffice, Long> {

}
