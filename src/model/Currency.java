package model;

import javax.persistence.*;
import javax.xml.soap.Name;
import java.io.Serializable;

/**
 * JPA Container,contains JPA entity, A persistent representation of an Currency
 */
@Entity
public class Currency implements CurrencyDTO,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "currency_id",length = 50)
    private int acctNo;

    @Column(name = "currency_name",length = 50)
    private String currencyName;
    @Column(name = "currency_rate",length = 50)
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
