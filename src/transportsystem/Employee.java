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
public class Employee implements Serializable{
    
    private String empID;
    private String empName;
    private int age;
    private int contactNo;
    private String designation;
    private String userName;
    private String password;

    /**
     *
     */
    public Employee() {
    }

    /**
     *
     * @param empID
     * @param empName
     * @param age
     * @param contactNo
     * @param designation
     */
    public Employee(String empID, String empName, int age, int contactNo, String designation) {
        this.empID = empID;
        this.empName = empName;
        this.age = age;
        this.contactNo = contactNo;
        this.designation = designation;
    }

    /**
     *
     * @return
     */
    public String getEmpID() {
        return empID;
    }

    /**
     *
     * @param empID
     */
    public void setEmpID(String empID) {
        this.empID = empID;
    }

    /**
     *
     * @return
     */
    public String getEmpName() {
        return empName;
    }

    /**
     *
     * @param empName
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return
     */
    public int getContactNo() {
        return contactNo;
    }

    /**
     *
     * @param contactNo
     */
    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    /**
     *
     * @return
     */
    public String getDesignation() {
        return designation;
    }

    /**
     *
     * @param designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param empID
     * @param empName
     * @param age
     * @param contactNo
     * @param designation
     * @param userName
     * @param password
     */
    public Employee(String empID, String empName, int age, int contactNo, String designation, String userName, String password) {
        this.empID = empID;
        this.empName = empName;
        this.age = age;
        this.contactNo = contactNo;
        this.designation = designation;
        this.userName = userName;
        this.password = password;
    }
    
    
}
