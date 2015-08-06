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

    public Employee() {
    }

    public Employee(String empID, String empName, int age, int contactNo, String designation) {
        this.empID = empID;
        this.empName = empName;
        this.age = age;
        this.contactNo = contactNo;
        this.designation = designation;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

   

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
