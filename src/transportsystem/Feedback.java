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

    public Feedback() {
    }

    public Feedback(int accNO, String name, String comments) {
        this.accNO = accNO;
        this.name = name;
        this.comments = comments;
    }

    public int getAccNO() {
        return accNO;
    }

    public void setAccNO(int accNO) {
        this.accNO = accNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}
