package integration;

import model.Currency;
import model.CurrencyDTO;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Contains DB calls and handles operations related to Entity Manager. ONLY JPA entities use this class
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class CurrencyDAO {
    @PersistenceContext(unitName = "bankPersistenceUnit")
    private EntityManager em;
    public void storeConversionRate(Currency currency) {
        em.persist(currency);
    }

    public List<Currency> getCurrencies() {
        Query query = em.createQuery("SELECT money FROM Currency money", Currency.class);
        List<Currency> currencyList = query.getResultList();
        return currencyList;
    }

    public void convert(double amounttoConvert, String fromCurrency, String toCurrency) {

        System.out.println("amounttoConvert"+amounttoConvert+"fromCurrency"+fromCurrency+"toCurrency"+toCurrency);

    }
}
