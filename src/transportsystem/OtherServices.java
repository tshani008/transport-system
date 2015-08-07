/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportsystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author User
 */
public class OtherServices {
    
    public static void Serialize(Object object, String fileName) throws IOException {
        
        FileOutputStream out = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(object);
        oos.flush();
        out.close();
        System.out.println(fileName + "Serialized.......!!!!!");
        
    }
    

    
    public static SetOfAccounts deserialize_Accounts(String fileName) throws IOException, ClassNotFoundException{
        
        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(in);
        SetOfAccounts Accounts = (SetOfAccounts) ois.readObject();
        in.close();
        System.out.println(fileName + "Deserialized.......!!!!!");
        return Accounts;
    } 
    
    public static SetOfEmployees deserialize_Employees(String fileName) throws IOException, ClassNotFoundException{
        
        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(in);
        SetOfEmployees Employees = (SetOfEmployees) ois.readObject();
        in.close();
        System.out.println(fileName + "Deserialized.......!!!!!");
        return Employees;
    } 
    
    public static SetOfTokens deserialize_Tokens(String fileName) throws IOException, ClassNotFoundException{
        
        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(in);
        SetOfTokens Tokens = (SetOfTokens) ois.readObject();
        in.close();
        System.out.println(fileName + "Deserialized.......!!!!!");
        return Tokens;
    }
    
    public SetOfNetworks deserialize_routes(String filename)	throws IOException, ClassNotFoundException {

		FileInputStream in = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(in);
		SetOfNetworks routes = (SetOfNetworks) ois
				.readObject();
		in.close();
                System.out.println(filename+" Deserialized !");
		return routes;
	}
    
    public SetOfNetworks deserialize_vehicle(String filename) throws IOException, ClassNotFoundException {

		FileInputStream in = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(in);
		SetOfNetworks members = (SetOfNetworks) ois
				.readObject();
		in.close();
                System.out.println(filename+" Deserialized !");
		return members;
	}
  
  public SetOfFares deserialize_fare(String filename) throws IOException, ClassNotFoundException {

		FileInputStream in = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(in);
		SetOfFares fare = (SetOfFares) ois
				.readObject();
		in.close();
                System.out.println(filename+" Deserialized !");
		return fare;
	}
  
  public SetOfTimetables deserialize_timetable(String filename) throws IOException, ClassNotFoundException {

		FileInputStream in = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(in);
		    SetOfTimetables timetable = (SetOfTimetables) ois
				.readObject();
		in.close();
                System.out.println(filename+" Deserialized !");
		return timetable;
	}
  
    public SetOfFeedbacks deserialize_feedback(String filename) throws IOException, ClassNotFoundException {

		FileInputStream in = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(in);
		    SetOfFeedbacks feedback = (SetOfFeedbacks) ois
				.readObject();
		in.close();
                System.out.println(filename+" Deserialized !");
		return feedback;
	}
      public SetOfJourneys deserialize_journey(String filename) throws IOException, ClassNotFoundException {

		FileInputStream in = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(in);
		    SetOfJourneys journey = (SetOfJourneys) ois
				.readObject();
		in.close();
                System.out.println(filename+" Deserialized !");
		return journey;
	}
}
