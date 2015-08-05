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
 * @author theja
 */
public class Network implements Serializable{
    
    private int roteId;
   private String routeName;
   private String networkType;
    private int subNetworkCount;
    private String vehicleNo;

    
    public int getRoteId() {
        return roteId;
    }

    public void setRoteId(int roteId) {
        this.roteId = roteId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public int getSubNetworkCount() {
        return subNetworkCount;
    }

    public void setSubNetworkCount(int subNetworkCount) {
        this.subNetworkCount = subNetworkCount;
    }

    public Network(int roteId) {
        this.roteId = roteId;
    }

    public Network() {
    }

    public Network(int roteId, String routeName, String networkType) {
        this.roteId = roteId;
        this.routeName = routeName;
        this.networkType = networkType;
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

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Network(String routeName, String vehicleNo, String networkType) {
        this.routeName = routeName;
        this.networkType = networkType;
        this.vehicleNo = vehicleNo;
    }
    
}
