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
  
//    public FareManagment getRouteFromNumber(int number)
//  {
//     Network foundnumber=new Network();
//      for(Network net:this)
//      {
//          if(net.getRoteId()==number)
//          {
//              foundnumber=net;
//              break;
//          }
//      }
//      return foundnumber; 
//  }
//   
    
   
    
}
   