package emt.miniproekt.hr_enhancer.employeesector.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class EventsService {

    private final EmployeeRepo employeeRepo;


    public EventsService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


}
