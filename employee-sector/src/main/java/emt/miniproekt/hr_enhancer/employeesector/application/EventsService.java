package emt.miniproekt.hr_enhancer.employeesector.application;

import emt.miniproekt.hr_enhancer.employeesector.domain.model.Employee;
import emt.miniproekt.hr_enhancer.employeesector.integration.ComplaintEvent;
import emt.miniproekt.hr_enhancer.employeesector.integration.PositionChangeEvent;
import emt.miniproekt.hr_enhancer.employeesector.integration.RaiseEvent;
import emt.miniproekt.hr_enhancer.employeesector.integration.RestDaysEvent;
import emt.miniproekt.sharedkernel.domain.base.Position;
import emt.miniproekt.sharedkernel.domain.financial.Currency;
import emt.miniproekt.sharedkernel.domain.val_objs.Contract;
import emt.miniproekt.sharedkernel.domain.val_objs.Salary;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional

public class EventsService {

    private final EmployeeRepo employeeRepo;


    public EventsService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @NonNull
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @NonNull
    public Employee findById(@NonNull int employeeId) {
        return employeeRepo.findById(employeeId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onComplaintEvent(ComplaintEvent event) {

        // pagja, employeeId i requestId se sekogas 0, greska pri deserijalizacija

        Employee emp = employeeRepo.findById(event.getEmployeeId());
        if(CheckNumberOfRequests(emp)) {
            emp.setComplaintReqId(event.getComplaintId());
            employeeRepo.saveNewEmployee(emp);
        }
        else {
            throw new IllegalArgumentException("This employee already has an active request");
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPositionChangeEvent(PositionChangeEvent event) {
        Employee emp = employeeRepo.findById(event.getEmployeeId());
        if(CheckNumberOfRequests(emp)) {
            emp.setComplaintReqId(event.getPositionChangeId());
            emp.setPosition(Position.valueOf(event.getNewPosition()));
            employeeRepo.saveNewEmployee(emp);
        }
        else {
            throw new IllegalArgumentException("This employee already has an active request");
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onRaiseEvent(RaiseEvent event) {
        Employee emp = employeeRepo.findById(event.getEmployeeId());
        if(CheckNumberOfRequests(emp)) {
            emp.setComplaintReqId(event.getRaiseId());
            Salary salary = new Salary(emp.getSalary().getCurrency(), event.getNewSalary(), emp.getSalary().getBonus());
            emp.setSalary(salary);
            employeeRepo.saveNewEmployee(emp);
        }
        else {
            throw new IllegalArgumentException("This employee already has an active request");
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onRestDaysEvent(RestDaysEvent event) {

        Employee emp = employeeRepo.findById(event.getEmployeeId());
        if(CheckNumberOfRequests(emp)) {
            emp.setComplaintReqId(event.getRestDaysId());
            Contract contract = new Contract(emp.getContract().getStart(), emp.getContract().getEnd(), event.getNumDays());
            employeeRepo.saveNewEmployee(emp);
        }
        else {
            throw new IllegalArgumentException("This employee already has an active request");
        }
    }

    public boolean CheckNumberOfRequests(Employee emp) {
        return emp.getPositionChangeReqId() == null && emp.getComplaintReqId() == null &&
                emp.getRaiseReqId() == null && emp.getRestDaysReqId() == null;
    }

}
