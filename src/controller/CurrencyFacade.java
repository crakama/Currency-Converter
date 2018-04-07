package controller;

import integration.CurrencyDAO;
import model.Currency;
import model.CurrencyDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/** CurrencyFacade an EJB controller, orchestrates calls to model and integration layers */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CurrencyFacade {
    @EJB
    CurrencyDAO currencyDAO;

    public void createConversionRates(Currency[] currencies ) {
        for(Currency currency: currencies){
            currencyDAO.storeConversionRate(currency);
        }
    }
    public double convertCurrency(double amounttoConvert, String fromCurrencyName, String toCurrencyName) {
        double targetAmount = currencyDAO.convert(amounttoConvert, fromCurrencyName,toCurrencyName);
        return targetAmount;
    }

}
