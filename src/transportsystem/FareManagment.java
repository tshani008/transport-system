/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportsystem;

import java.io.Serializable;

/**
 *
 * @author theja
 */
public class FareManagment implements Serializable{

    private int roteId;
    private String routeName;
    private String networkType;
    private String startPoint;
    private String endPoint;
    private String fare;

    /**
     *
     * @return
     */
    public int getRoteId() {
        return roteId;
    }

    /**
     *
     * @param roteId
     */
    public void setRoteId(int roteId) {
        this.roteId = roteId;
    }

    /**
     *
     * @return
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     *
     * @param routeName
     */
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    /**
     *
     * @return
     */
    public String getNetworkType() {
        return networkType;
    }

    /**
     *
     * @param networkType
     */
    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    /**
     *
     * @return
     */
    public String getStartPoint() {
        return startPoint;
    }

    /**
     *
     * @param startPoint
     */
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    /**
     *
     * @return
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     *
     * @param endPoint
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     *
     * @return
     */
    public String getFare() {
        return fare;
    }

    /**
     *
     * @param fare
     */
    public void setFare(String fare) {
        this.fare = fare;
    }

    /**
     *
     * @param routeName
     * @param networkType
     * @param startPoint
     * @param endPoint
     * @param fare
     */
    public FareManagment(String routeName, String networkType, String startPoint, String endPoint, String fare) {
       
        this.routeName = routeName;
        this.networkType = networkType;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.fare = fare;
    }

    /**
     * Constructor
     */
    public FareManagment() {
    }
    
    

}
