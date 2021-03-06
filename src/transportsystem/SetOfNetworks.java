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
public class SetOfNetworks extends Vector<Network> implements Serializable{
    
    /**
     *
     */
    public SetOfNetworks() {
        
        super();
    }
  
    private volatile static SetOfNetworks firstInstance;
    
    /**
     *
     * @return
     */
    public static synchronized SetOfNetworks getInstance()
  {
      if(firstInstance==null)
      {
          synchronized(SetOfNetworks.class)
          {
              if(firstInstance==null)
                firstInstance=new SetOfNetworks();
          }
      }
      return firstInstance;
  }
    
    /**
     *
     * @param network
     */
    public void addNetowrk(Network network) 
  {
    super.add(network);
  }
    
    /**
     *
     * @param aRoute
     */
    public void removeNetwork(Network aRoute) 
  {
    super.remove(aRoute);
  }  
  
    /**
     *
     * @param number
     * @return
     */
    public Network getRouteFromNumber(int number)
  {
     Network foundnumber=new Network();
         for(Network net:this)
            {
                 if(net.getRoteId()==number)
            {
              foundnumber=net;
              break;
             }
      }
      return foundnumber; 
  }
   
    /**
     *
     * @param route
     */
    public void addVehical(Network route) 
  {
    super.add(route);
  }
   
    /**
     *
     * @param vehicle
     */
    public void removeVehical(Network vehicle) 
  {
    super.remove(vehicle);
  } 
   
    /**
     *
     * @param number
     * @return
     */
    public Network getVehicleFromNumber(String number)
  {
    Network foundnumber=new Network();
        for(Network net:this)
            {
                           if(net.getVehicleNo().matches(number))
                    {
                      foundnumber=net;
                      break;
                     }     
            }
            return foundnumber; 
  }
}

  
  
