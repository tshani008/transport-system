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
 * @author Theja
 */
public class SetOfTimetables extends Vector<Planning> implements Serializable {

    /**
     *
     */
    public SetOfTimetables() {
        
         super();
    }
    
    /**
     *
     * @param timetable
     */ 
    public void addTimeTable(Planning timetable) 
  {
    super.add(timetable);
  }
    
    /**
     *
     * @param timetable
     */
    public void removeTimeTable(Planning timetable) 
  {
    super.remove(timetable);
  } 
  
    /**
     *
     * @param number
     * @return
     */
    public Planning getRouteFromNumber(String number)
  {
     Planning foundnumber=new Planning();
      for(Planning net:this)
      {
          if(net.getVehicleno() == null ? number == null : net.getVehicleno().equals(number))
          {
              foundnumber=net;
              break;
          }
      }
      return foundnumber; 
  }
      
  
    
}
