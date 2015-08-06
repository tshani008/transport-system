

package transportsystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import transportsystem.OtherServices;

/**
 *
 * @author Theja
 */


public class NetworkTestCase {
    	String message = " Add Routes ";
        public  OtherServices services =new OtherServices();
   
	MessageUtil messageUtil = new MessageUtil(message);

	@Test
	public void testPrintMessage() {
		System.out.println("Inside testPrintMessage()");
		Assert.assertEquals(message, messageUtil.printMessage());
        }
        
        
        
        
        private static final String FILE_NAME = "Routes.ser";
	private static  SetOfNetworks netList = new SetOfNetworks();

	static {

        try {
            netList.add(new Network(138,"Kottawa-Petta","Bus"));
            netList.add(new Network(336, "Kottawa-Malabe","Bus"));
          
            
            
            OtherServices service = new OtherServices();
            //Serializer.Serialize(empList, FILE_NAME);
            service.Serialize(netList, FILE_NAME);
        } catch (IOException ex) {
            Logger.getLogger(NetworkTestCase.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
                        
		
	}
        
        /**
	 * Positive test case for Routes Serialization
	 */
	@Test
	public void networkTestCase() {

		try {  OtherServices services1 = new OtherServices();
			int i = 0;
			for (Network net : services1.deserialize_routes(FILE_NAME)) {
				
                            
                                Assert.assertEquals(new Integer(net.getRoteId()).intValue(),new Integer(netList.get(i).getRoteId()).intValue());
                             
                                Assert.assertEquals(net.getRouteName(),netList.get(i).getRouteName());
                                Assert.assertEquals(net.getNetworkType(),netList.get(i).getNetworkType());
				
				i++;
                                  System.out.println(i +" networkTestcase Passed");
			}
                      
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    	/**
	 * Negative test case for Deserialize network class.
	 * Use expected exception FileNotFoundException.class for this case
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test(expected = FileNotFoundException.class)
        
	public void negativeTestCaseForDeserialize() throws ClassNotFoundException,
			IOException {
            OtherServices services2=new OtherServices();

	
                services2.deserialize_routes("Not Found");

	}    
        
        
        
}
