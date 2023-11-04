package dental.office.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dental.office.entity.Staff;

public interface StaffDao extends JpaRepository<Staff, Long> {

}
