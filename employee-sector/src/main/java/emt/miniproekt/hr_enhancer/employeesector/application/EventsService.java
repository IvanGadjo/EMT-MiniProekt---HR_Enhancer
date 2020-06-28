package emt.miniproekt.hr_enhancer.employeesector.application;

import emt.miniproekt.hr_enhancer.employeesector.domain.model.Employee;
import emt.miniproekt.hr_enhancer.employeesector.integration.ComplaintEvent;
import emt.miniproekt.hr_enhancer.employeesector.integration.PositionChangeEvent;
import emt.miniproekt.hr_enhancer.employeesector.integration.RaiseEvent;
import emt.miniproekt.hr_enhancer.employeesector.integration.RestDaysEvent;
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
        Employee emp = employeeRepo.findById(event.getEmployeeId());
        if(CheckNumberOfRequests(emp) == true) {
            emp.setComplaintReqId(1);
        }
        else {
            throw new IllegalArgumentException("This employee already has an active request");
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPositionChangeEvent(PositionChangeEvent event) {

    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onRaiseEvent(RaiseEvent event) {

    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onRestDaysEvent(RestDaysEvent event) {

    }

    public boolean CheckNumberOfRequests(Employee emp) {
        if(emp.getPositionChangeReqId() == null && emp.getComplaintReqId() == null &&
                emp.getRaiseReqId() == null && emp.getRestDaysReqId() == null ) {
            return true;
        }
        else {
            return false;
        }
    }

}
