package view;

import controller.Controller;
import model.AccountDTO;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Managed Bean
 */

@Named("accountManager")
@ConversationScoped
public class AccountManager  implements Serializable {
    @EJB
    private Controller controller;
    private AccountDTO accountDTO;
    private Integer transactionAmount;
    private String newAccountHolderFirstName;

    @Inject
    Conversation conContext;
    /**
     * index.xhtml, <h:inputText id="transactionAmount" value="#{accountManager.transactionAmount}"/>
     * @param transactionAmount
     */
    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public void setNewAccountHolderFirstName(String newAccountHolderFirstName) {
        this.newAccountHolderFirstName = newAccountHolderFirstName;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getNewAccountHolderFirstName() {
        return null;
    }

//    public void deposit(){
//        controller.deposit(accountDTO.getAccountNo(), transactionAmount);
//    }

    public void createAccount(){
        startConversation();
        accountDTO = controller.createAccount(newAccountHolderFirstName);
    }

    private void startConversation() {
        if(conContext.isTransient()){
            conContext.begin();
        }
    }
}
