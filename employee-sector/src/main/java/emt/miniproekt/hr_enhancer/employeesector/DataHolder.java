package emt.miniproekt.hr_enhancer.employeesector;

import emt.miniproekt.hr_enhancer.employeesector.application.EmployeeRepo;
import emt.miniproekt.hr_enhancer.employeesector.application.SectorRepo;
import emt.miniproekt.hr_enhancer.employeesector.domain.model.Employee;
import emt.miniproekt.hr_enhancer.employeesector.domain.model.Sector;
import emt.miniproekt.sharedkernel.domain.base.Position;
import emt.miniproekt.sharedkernel.domain.financial.Currency;
import emt.miniproekt.sharedkernel.domain.val_objs.Contract;
import emt.miniproekt.sharedkernel.domain.val_objs.NameInfo;
import emt.miniproekt.sharedkernel.domain.val_objs.Salary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataHolder {

    EmployeeRepo employeeRepo;
    SectorRepo sectorRepo;

    public DataHolder(EmployeeRepo employeeRepo, SectorRepo sectorRepo){
        this.employeeRepo = employeeRepo;
        this.sectorRepo = sectorRepo;
         //addData();
    }


    public void addData(){
        Sector sector1 = new Sector("IT");

        sectorRepo.saveNewSector(sector1);

        Employee e1 = new Employee(new NameInfo("Dirk", "Nowitzki"),
                new Salary(Currency.MKD, 12000, 0),
                new Contract(LocalDate.of(2020,1,1), LocalDate.of(2021,1,1),20),
                "123456789",
                Position.JUNIOR,
                sector1);

        employeeRepo.saveNewEmployee(e1);



        Sector s = sectorRepo.findById(2);
        Employee e2 = new Employee(new NameInfo("Amare", "Stoudemire"),
                new Salary(Currency.MKD, 16000, 500),
                new Contract(LocalDate.of(2020,1,1), LocalDate.of(2021,3,1),25),
                "123456111",
                Position.SENIOR,
                s);

        employeeRepo.saveNewEmployee(e2);
    }
}
