package emt.miniproekt.hr_enhancer.employeesector.application;

import emt.miniproekt.hr_enhancer.employeesector.domain.model.Employee;
import emt.miniproekt.hr_enhancer.employeesector.domain.repository.EmployeeRepoJPA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeRepo {

    EmployeeRepoJPA employeeRepoJPA;

    public EmployeeRepo(EmployeeRepoJPA employeeRepoJPA){
        this.employeeRepoJPA = employeeRepoJPA;
    }

    public List<Employee> findAll(){
        return employeeRepoJPA.findAll();
    }

    public Employee findById(int id){
        return employeeRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public Employee saveNewEmployee(Employee employee){
        return employeeRepoJPA.save(employee);
    }

    public void deleteById(int id){
        employeeRepoJPA.deleteById(id);
    }

}
