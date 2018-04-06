package view;

import controller.CurrencyFacade;
import model.Currency;
import model.CurrencyDTO;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * JSF Managed Bean,POJO class that coordinates operations JSF and read property values of JSF
 */

@Named("currencyManager")
@ConversationScoped
public class CurrencyManager implements Serializable {
    @EJB
    private CurrencyFacade currencyFacade;
    private CurrencyDTO currencyDTO;
    private double amounttoConvert;
    private String toCurrencyName;
    private String fromCurrencyName;

    @Inject
    Conversation conContext;
    /**
     * @param amounttoConvert
     */
    public void setAmounttoConvert(double amounttoConvert) {
        this.amounttoConvert = amounttoConvert;
    }
    /**
     * Never used but JSF does not support write-only properties.
     */
    public double getAmounttoConvert() {
        return 0.0;
    }

    public void setFromCurrencyName(String fromCurrencyName) {
        this.fromCurrencyName = fromCurrencyName;
    }
    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getFromCurrencyName() {
        return null;
    }

    public void setToCurrencyName(String toCurrencyName) {
        this.toCurrencyName = toCurrencyName;
    }
    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getToCurrencyName() {
        return null;
    }

    public void convert(){
        createConversionRates();
        //currencyDTO = currencyFacade.convertCurrency(amounttoConvert,fromCurrencyName,toCurrencyName);
        currencyFacade.convertCurrency(amounttoConvert,fromCurrencyName,toCurrencyName);
    }

    public void createConversionRates(){
        startConversation();
        Currency[] currencies = {new Currency("USD",1.0),
                                  new Currency("EUR",0.0738)};
        currencyFacade.createConversionRates(currencies);
    }

    private void startConversation() {
        if(conContext.isTransient()){
            conContext.begin();
        }
    }
}
