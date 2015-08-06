/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportsystem;

import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class SetOfAccounts extends Vector<Account> implements Serializable {

    transient private SetOfAccounts accountList;
    private float amount;
    private Iterable<Account> SetOfAccounts;
    private float newbalance;

    public SetOfAccounts() {
        super();
    }

    public void addAccount(Account account) {
        super.add(account);
    }

    public Account findAccountFromAccNumber(int accNo) {
        Account foundaccount = new Account();
        for (Account account : this) {
            if (account.getAccountNo() == accNo) {
                foundaccount = account;
                break;
            }
        }
        return foundaccount;
    }

    public Account findBalance(float balance) {
        Account foundaccount = new Account();
        for (Account account : this) {
            if (account.getBalance() == balance) {
                foundaccount.setBalance(balance);
            }
        }
        return foundaccount;
    }

    public Account updateBalance(Account ac, int accNo, float amount) {
        
        for (Account account : SetOfAccounts) {
            if (account.getAccountNo() == accNo) {
                float balance = account.getBalance();
                ac.setBalance(balance + amount);
            }

        }
        return ac;
    }

    public boolean removeBalance(int accNo) {
        return this.remove(findAccountFromAccNumber(accNo).getBalance());
        //return super.remove(balance1);
    }
    
        public float getbalanceFromNumber(int number)
  {
     Account foundnumber=new Account();
     float balance=0;
         for(Account net:this)
            {
                 if(net.getAccountNo()==number)
            {
              balance=net.getBalance();
              break;
             }
      }
      return  balance;
  }

}
