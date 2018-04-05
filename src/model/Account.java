package model;

import org.glassfish.admin.rest.generator.ResourcesGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * JPA Entity, A persistent representation of an account
 */
@Entity
public class Account implements AccountDTO,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int acctNo;
    private String firstName;

    public Account(){    }

    public Account(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }
}
