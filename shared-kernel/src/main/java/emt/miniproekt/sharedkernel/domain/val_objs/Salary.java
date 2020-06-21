package emt.miniproekt.sharedkernel.domain.val_objs;

import emt.miniproekt.sharedkernel.domain.base.ValueObject;
import emt.miniproekt.sharedkernel.domain.financial.Currency;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Salary implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    private final double amount;

    private final double bonus;

    public Salary(Currency currency, double amount, double bonus){
        if(amount<0 || bonus<0)
            throw new IllegalArgumentException("Amount or bonus must be > 0");
        this.currency = currency;
        this.amount = amount;
        this.bonus = bonus;
    }

    public Salary valueOf(Currency currency, double amount, double bonus){
        return new Salary(currency,amount,bonus);
    }

    public Salary increaseSalary(double inc){
        if (inc<0)
            throw new IllegalArgumentException("Amount must be > 0");
        if (((this.amount*15)/100) > inc)
            throw new IllegalArgumentException("Cannot increase salary over 15%");  // biznis pravilo
        return new Salary(this.currency, this.amount+inc, this.bonus);
    }

    public Salary decreaseSalary(double dec){
        if (dec<0)
            throw new IllegalArgumentException("Amount must be > 0");
        if (this.amount-dec<0)
            throw new IllegalArgumentException("Cannot have salary amount <0");
        return new Salary(this.currency, this.amount-dec, this.bonus);
    }

    public Salary increaseBonus(double inc){
        if (inc<0)
            throw new IllegalArgumentException("Bonus must be > 0");
        return new Salary(this.currency, this.amount, this.bonus+inc);
    }

    public Salary decreaseBonus(double dec){
        if (dec<0)
            throw new IllegalArgumentException("Bonus must be > 0");
        if (this.bonus-dec<0)
            throw new IllegalArgumentException("Cannot have salary bonus <0");
        return new Salary(this.currency, this.amount, this.bonus-dec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency,amount, bonus);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Salary other = (Salary) obj;
        if (this.amount==other.amount && this.bonus == other.bonus && this.currency.equals(other.currency))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return String.format("Currency: %s, Amount: %f, Bonus: %f",this.currency, this.amount, this.bonus);
    }

}
