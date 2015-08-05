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
 * @author theja
 */
public class Services {
   public void Serialize(Object object, String filename) throws IOException {

		FileOutputStream out = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(object);
		oos.flush();
		out.close();
                System.out.println(filename+" Serialized !");
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
}
