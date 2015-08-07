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
public class Feedback implements Serializable{
    private int accNO;
    private String name;
    private String comments;

    /**
     *
     */
    public Feedback() {
    }

    /**
     *
     * @param accNO
     * @param name
     * @param comments
     */
    public Feedback(int accNO, String name, String comments) {
        this.accNO = accNO;
        this.name = name;
        this.comments = comments;
    }

    /**
     *
     * @return
     */
    public int getAccNO() {
        return accNO;
    }

    /**
     *
     * @param accNO
     */
    public void setAccNO(int accNO) {
        this.accNO = accNO;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}
