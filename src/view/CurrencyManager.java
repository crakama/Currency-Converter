package view;

import controller.CurrencyFacade;
import model.Currency;
import model.CurrencyDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    private double targetAmount;
    private Map<String, String> listCurrencies;
    @Inject
    Conversation conContext;


    @PostConstruct
    public void initCurrency() {
        createConversionRates();
        listCurrencies = currencyFacade.listAllCurrencies();
    }

    public Map<String, String> getListCurrencies() {
        return listCurrencies;
    }

    public void setListCurrencies(Map<String, String> currencies) {
        this.listCurrencies = currencies;
    }
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

    public String getFromCurrencyName() {
        return null;
    }

    public void setToCurrencyName(String toCurrencyName) {
        this.toCurrencyName = toCurrencyName;
    }

    public String getToCurrencyName() {
        return null;
    }

    public void convert(){
        double coversionResult = currencyFacade.convertCurrency(amounttoConvert,fromCurrencyName,toCurrencyName);
        setTargetAmount(coversionResult);
    }
    private void setTargetAmount(double coversionResult) {
        this.targetAmount = coversionResult;
    }
    public double getTargetAmount() {
        return targetAmount;
    }
    public void createConversionRates(){
        startConversation();

        Currency[] currencies = {new Currency("US Dollar",1.0),
                                new Currency("Euro",0.81),
                                new Currency("Swedish Kronor",8.39),
                                new Currency("Kenyan Shilling",101.00),
                                new Currency("Pakistani Rupee",115.50)};
        currencyFacade.createCorrencyRates(currencies);
    }
    private void startConversation() {
        if(conContext.isTransient()){
            conContext.begin();
        }
    }
    private void stopConversation() {
        if (!conContext.isTransient()) {
            conContext.end();
        }
    }
}
