/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportsystem;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author User
 */
public class SetOfFeedbacks extends Vector<Feedback> implements Serializable{

    public SetOfFeedbacks() {
        super();
    }
    
     public void addFeedback(Feedback feedback){
        super.add(feedback);
  }
    
    
}
