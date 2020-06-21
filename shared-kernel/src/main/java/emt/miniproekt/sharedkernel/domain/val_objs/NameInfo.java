package emt.miniproekt.sharedkernel.domain.val_objs;

import emt.miniproekt.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class NameInfo implements ValueObject {

    private final String firstName;

    private final String lastName;

    public NameInfo(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public NameInfo valueOf(String firstName, String lastName){
        return new NameInfo(firstName, lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        NameInfo other = (NameInfo) obj;
        if (this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.firstName+" "+this.lastName;
    }
}
