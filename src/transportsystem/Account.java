/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportsystem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author User
 */
public class Account implements Serializable{
    
    private int accountNo;
    private String passengerName;
    private String passengerNIC;
    private String address;
    private float balance;
    private float loanAmount;
    private String userName;
    private String password;

    
    public Account(int accountNo, String passengerName, String passengerNIC, String address, String userName, String password, float balance, float loanAmount) {
        this.accountNo = accountNo;
        this.passengerName = passengerName;
        this.passengerNIC = passengerNIC;
        this.address = address;
        this.balance = balance;
        this.loanAmount = loanAmount;
        this.userName = userName;
        this.password = password;
    }

    public Account(int accountNo, String passengerName, String passengerNIC, String address, String userName, String password) {
        this.accountNo = accountNo;
        this.passengerName = passengerName;
        this.passengerNIC = passengerNIC;
        this.address = address;
        this.userName = userName;
        this.password = password;
    }

    public Account() {
    }

    
    

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerNIC() {
        return passengerNIC;
    }

    public void setPassengerNIC(String passengerNIC) {
        this.passengerNIC = passengerNIC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(float loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    private void writeObject(ObjectOutputStream oos)
        throws IOException 
    {
        oos.defaultWriteObject();
        //oos.writeObject(new Integer(bookCount));
    }

    private void readObject(ObjectInputStream ois)
    throws ClassNotFoundException, IOException 
    {
        ois.defaultReadObject();
        //bookCount = (Integer)ois.readObject();
    }
    
    
    
}
