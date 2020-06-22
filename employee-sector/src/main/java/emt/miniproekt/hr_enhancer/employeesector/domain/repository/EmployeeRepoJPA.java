package emt.miniproekt.hr_enhancer.employeesector.domain.repository;

import emt.miniproekt.hr_enhancer.employeesector.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepoJPA extends JpaRepository<Employee, Integer> {
}
