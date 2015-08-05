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
public class Planning implements Serializable {
    
     private String algorithm;
     private String startpoint;
     private String endpoint;
     private String type;
     private String starttime;
     private String endtime;
     private String route;
     private String status;
     private String vehicleno;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(String startpoint) {
        this.startpoint = startpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Planning(String route, String vehicleno, String type, String starttime, String endtime, String status) {
        this.type = type;
        this.starttime = starttime;
        this.endtime = endtime;
        this.route = route;
        this.status = status;
        this.vehicleno = vehicleno;
    }

    public Planning() {
    }
     
     
     
    
}
