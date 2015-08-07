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
 * @author User
 */
public class SetOfEmployees extends Vector<Employee> implements Serializable{

    /**
     *
     */
    public SetOfEmployees() {
        super();
    }
    
    /**
     *
     * @param employee
     */
    public void addEmployee(Employee employee){
        super.add(employee);
  }
    
    /**
     *
     * @param employee
     * @return
     */
    public boolean removeEmployee(Employee employee)
  {
      return super.remove(employee);
  }
    
    /**
     *
     * @param number
     * @return
     */
    public Employee findEmployeeFromEmpNumber (String number)
  {
      Employee foundemployee=new Employee();
      for(Employee employee: this )
      {
          if(employee.getEmpID().equals(number))
          {
              foundemployee=employee;
              break;
          }
      }
      return foundemployee;
  }
    
}
