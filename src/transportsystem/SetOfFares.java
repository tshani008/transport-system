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
 * @author theja
 */
public class SetOfFares extends Vector<FareManagment> implements Serializable{

    public SetOfFares() {
        
        super();
    }
    
      public void addDistance(FareManagment fare) 
  {
    super.add(fare);
  }
    
   
  public void removeDistance(FareManagment fare2) 
  {
    super.remove(fare2);
  }  
  
    public String getFare(String p1,String p2)
  {
      FareManagment foundnumber=new FareManagment();
     String fare=null;
      for(FareManagment net:this)
      {
          if(net.getStartPoint()==p1 && net.getEndPoint()==p2)
          {
             fare=net.getFare();
              break;
          }
      }
      return fare;
  }
   

   
    
}
   