package controller;

import integration.CurrencyDAO;
import model.Currency;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.HashMap;
import java.util.Map;

/** CurrencyFacade an EJB controller, orchestrates calls to model and integration layers */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CurrencyFacade {
    @EJB
    CurrencyDAO currencyDAO;

    public void createCorrencyRates(Currency[] currencies ) {
            currencyDAO.storeConversionRate(currencies);

    }
    public double convertCurrency(double amounttoConvert, String fromCurrencyName, String toCurrencyName) {
        double targetAmount = currencyDAO.convert(amounttoConvert, fromCurrencyName,toCurrencyName);
        return targetAmount;
    }

    public Map<String, String> listAllCurrencies() {
        Map<String, String> currencies = new HashMap<>();
        for(Currency currency: currencyDAO.getCurrencies()){
            currencies.put(currency.getcurrencyName(),currency.getcurrencyName());
        }
        return currencies;
    }
}
