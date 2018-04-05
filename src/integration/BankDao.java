package integration;

import model.Account;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Contains DB calls and handles operations related to Entity Manager. ONLY JPA entities use this class
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class BankDao {
    @PersistenceContext(unitName = "bankPersistenceUnit")
    private EntityManager em;
    public void storeAccount(Account account){
        em.persist(account);
    }
}
