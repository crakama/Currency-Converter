package controller;

import integration.BankDao;
import model.Account;
import model.AccountDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/** Controller, orchestrate calls to model and integration layers */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class Controller {
    @EJB
    BankDao bankDao;

    public AccountDTO createAccount(String firstName) {
        Account newAcct = new Account(firstName);
        bankDao.storeAccount(newAcct);
        return newAcct;
    }
}
