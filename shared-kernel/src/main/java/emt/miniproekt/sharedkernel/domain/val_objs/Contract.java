package emt.miniproekt.sharedkernel.domain.val_objs;

import emt.miniproekt.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
public class Contract implements ValueObject {      // fixme: mozebi treba @JsonCreator i @JsonProperty za deserijalizacija

    private final LocalDate start;

    private final LocalDate end;

    public Contract(LocalDate start, LocalDate end){
        if(start.isAfter(end))
            throw new IllegalArgumentException("End of contract is before start");
        this.start = start;
        this.end = end;
    }

    // factory method
    public Contract valueOf(LocalDate start, LocalDate end){
        return new Contract(start, end);
    }

    public boolean isLongerThenAYear(){
        if(end.getYear()-start.getYear()>0){
            if(end.getMonthValue()-start.getMonthValue()>0)
                return true;
            if(end.getMonthValue()-start.getMonthValue()==0){
                if(end.getDayOfMonth()-start.getDayOfMonth()>0)
                    return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Contract other = (Contract) obj;
        if (this.start.isEqual(other.start) && this.end.isEqual(other.end))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return start.toString()+" - "+end.toString();
    }
}
