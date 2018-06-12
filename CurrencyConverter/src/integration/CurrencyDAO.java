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
    private double rateOfTargetToDollars;
    private double rateOfSourceToDollars;
    public void storeConversionRate(Currency[] currencies) {

        for(Currency curr: currencies){
            Currency currency = em.find(Currency.class,curr.getId());
            if(currency == null){
                em.persist(curr);
            }

        }
    }

    public List<Currency> getCurrencies() {
        Query query = em.createQuery("SELECT money FROM Currency money", Currency.class);
        List<Currency> currencyList = query.getResultList();
        return currencyList;
    }

    public double convert(double amounttoConvert, String nameOfFromCurrency, String nameOfToCurrency) {
        for(Currency currency: getCurrencies()){
            if(currency.getcurrencyName().equalsIgnoreCase(nameOfFromCurrency)){
                rateOfSourceToDollars = currency.getCurrencyRate();
            }
            if (currency.getcurrencyName().equalsIgnoreCase(nameOfToCurrency)){
                rateOfTargetToDollars = currency.getCurrencyRate();
            }
        }


        double targetAmount = (amounttoConvert / rateOfSourceToDollars) * rateOfTargetToDollars;
        return targetAmount;
    }
}
