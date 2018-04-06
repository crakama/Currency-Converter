package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * JPA Container,contains JPA entity, A persistent representation of an Currency
 */
@Entity
public class Currency implements CurrencyDTO,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int acctNo;
    private String currencyName;
    private double currencyrate;

    public Currency(){    }

    public Currency(String currencyName,double rate) {
        this.currencyName = currencyName;
        this.currencyrate = rate;
    }


    @Override
    public String getcurrencyName() {
        return currencyName;
    }

    @Override
    public double getCurrencyRate() {
        return currencyrate;
    }

}
