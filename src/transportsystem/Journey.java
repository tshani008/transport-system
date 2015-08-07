/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transportsystem;

import java.io.Serializable;

/**
 *
 * @author Theja
 */
public class Journey implements Serializable{
    
    int journeyid;
    String stratingpoint;
    String  endingString;
    String customerid;
    String fare;
   String date;

    /**
     *
     * @return
     */
    public int getJourneyid() {
        return journeyid;
    }

    /**
     *
     * @param journeyid
     */
    public void setJourneyid(int journeyid) {
        this.journeyid = journeyid;
    }

    /**
     *
     * @return
     */
    public String getStratingpoint() {
        return stratingpoint;
    }

    /**
     *
     * @param stratingpoint
     */
    public void setStratingpoint(String stratingpoint) {
        this.stratingpoint = stratingpoint;
    }

    /**
     *
     * @return
     */
    public String getEndingString() {
        return endingString;
    }

    /**
     *
     * @param endingString
     */
    public void setEndingString(String endingString) {
        this.endingString = endingString;
    }

    /**
     *
     * @return
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     *
     * @param customerid
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
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
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @param journeyid
     * @param stratingpoint
     * @param customerid
     * @param date
     */
    public Journey(int journeyid, String stratingpoint, String customerid, String date) {
        this.journeyid = journeyid;
        this.stratingpoint = stratingpoint;
        this.customerid = customerid;
        this.date = date;
    }

    /**
     *
     * @param journeyid
     * @param stratingpoint
     * @param endingString
     * @param customerid
     * @param fare
     * @param date
     */
    public Journey(int journeyid, String stratingpoint, String endingString, String customerid, String fare, String date) {
        this.journeyid = journeyid;
        this.stratingpoint = stratingpoint;
        this.endingString = endingString;
        this.customerid = customerid;
        this.fare = fare;
        this.date = date;
    }
    
    
    
}
