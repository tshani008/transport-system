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

    public SetOfEmployees() {
        super();
    }
    
    
    public void addEmployee(Employee employee){
        super.add(employee);
  }
    
    public boolean removeEmployee(Employee employee)
  {
      return super.remove(employee);
  }
    
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
