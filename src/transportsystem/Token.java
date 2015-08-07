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
public class Token implements Serializable {
    
    private String TokenID;
    private int AccountNo;
    private String issuedDate;
    private String expireDate;
    
    /**
     *
     */
    public Token() {
    }

    /**
     *
     * @param TokenID
     * @param AccountNo
     * @param issuedDate
     * @param expireDate
     */
    public Token(String TokenID, int AccountNo, String issuedDate, String expireDate) {
        this.TokenID = TokenID;
        this.AccountNo = AccountNo;
        this.issuedDate = issuedDate;
        this.expireDate = expireDate;
        
    }

    /**
     *
     * @return
     */
    public String getTokenID() {
        return TokenID;
    }

    /**
     *
     * @param TokenID
     */
    public void setTokenID(String TokenID) {
        this.TokenID = TokenID;
    }

    /**
     *
     * @return
     */
    public int getAccountNo() {
        return AccountNo;
    }

    /**
     *
     * @param AccountNo
     */
    public void setAccountNo(int AccountNo) {
        this.AccountNo = AccountNo;
    }

    /**
     *
     * @return
     */
    public String getIssuedDate() {
        return issuedDate;
    }

    /**
     *
     * @param issuedDate
     */
    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    /**
     *
     * @return
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     *
     * @param expireDate
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
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
