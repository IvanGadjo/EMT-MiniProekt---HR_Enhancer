package emt.miniproekt.hr_enhancer.employeesector.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import emt.miniproekt.sharedkernel.domain.base.Position;
import emt.miniproekt.sharedkernel.domain.val_objs.Contract;
import emt.miniproekt.sharedkernel.domain.val_objs.NameInfo;
import emt.miniproekt.sharedkernel.domain.val_objs.Salary;
import lombok.Getter;

import javax.persistence.*;


@Entity
@Table(name = "employees")
@Getter
public class Employee {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // value object
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "firstName", column = @Column(name = "firstName")),
        @AttributeOverride(name = "lastName", column = @Column(name = "lastName"))
    })
    private NameInfo nameInfo;

    // value object
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount")),
            @AttributeOverride(name = "bonus", column = @Column(name = "bonus"))
    })
    private Salary salary;

    // value object
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "contract_start")),
            @AttributeOverride(name = "end", column = @Column(name = "contract_end"))
    })
    private Contract contract;

    private String embg;

    @Column(name = "employee_position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Sector sectorId;


     private Integer complaintReqId;
     private Integer raiseReqId;
     private Integer positionChangeReqId;
     private Integer restDaysReqId;


    @SuppressWarnings("unused")
    private Employee(){}

    public Employee(NameInfo nameInfo, Salary salary, Contract contract, String embg, Position position, Sector sectorId){
        this.nameInfo = nameInfo;
        this.salary = salary;
        this.contract = contract;
        this.embg = embg;
        this.position = position;
        this.sectorId = sectorId;
    }

    public void setNameInfo(NameInfo nameInfo) {
        this.nameInfo = nameInfo;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setEmbg(String embg) {
        this.embg = embg;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setSectorId(Sector sectorId) {
        this.sectorId = sectorId;
    }

    public void setComplaintReqId(int complaintReqId) {
        this.complaintReqId = complaintReqId;
    }

    public void setRaiseReqId(int raiseReqId) {
        this.raiseReqId = raiseReqId;
    }

    public void setPositionChangeReqId(int positionChangeReqId) {
        this.positionChangeReqId = positionChangeReqId;
    }

    public void setRestDaysReqId(int restDaysReqId) {
        this.restDaysReqId = restDaysReqId;
    }
}

