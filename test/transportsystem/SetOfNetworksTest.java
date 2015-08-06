/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportsystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Theja
 */
public class SetOfNetworksTest {

    String message = " Add Routes ";

    /**
     *
     */
    public OtherServices services = new OtherServices();

    MessageUtil messageUtil = new MessageUtil(message);

    /**
     *
     */
    @Test
    public void testPrintMessage() {
        System.out.println("Inside testPrintMessage()");
        Assert.assertEquals(message, messageUtil.printMessage());
    }

    private static final String FILE_NAME = "Routes.ser";
    private static SetOfNetworks netList = new SetOfNetworks();

    static {

        try {
            netList.add(new Network(138, "Kottawa-Petta", "Bus"));
            netList.add(new Network(336, "Kottawa-Malabe", "Bus"));

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

        try {
            OtherServices services1 = new OtherServices();
            int i = 0;
            for (Network net : services1.deserialize_routes(FILE_NAME)) {

                Assert.assertEquals(new Integer(net.getRoteId()).intValue(), new Integer(netList.get(i).getRoteId()).intValue());

                Assert.assertEquals(net.getRouteName(), netList.get(i).getRouteName());
                Assert.assertEquals(net.getNetworkType(), netList.get(i).getNetworkType());

                i++;
                System.out.println(i + " networkTestcase Passed");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Negative test case for Deserialize network class. Use expected exception
     * FileNotFoundException.class for this case
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test(expected = FileNotFoundException.class)

    public void negativeTestCaseForDeserialize() throws ClassNotFoundException,
            IOException {
        OtherServices services2 = new OtherServices();

        services2.deserialize_routes("Not Found");

    }

    /**
     *
     */
    public SetOfNetworksTest() {
    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getRouteFromNumber method, of class SetOfNetworks.
     */
    @Test
    public void testGetRouteFromNumber() {
        System.out.println("getRouteFromNumber");
        int number = 336;
        SetOfNetworks instance = new SetOfNetworks();
        Network n = null;
        try {
            Integer expResult = Integer.valueOf(n.getRoteId());
            Network result = instance.getRouteFromNumber(number);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is Invalid");
        } catch (Exception e) {
        }

    }

    /**
     * Test of getVehicleFromNumber method, of class SetOfNetworks.
     */
    @Test
    public void testGetVehicleFromNumber() {
        System.out.println("getVehicleFromNumber");
        String number = "";
        SetOfNetworks instance = new SetOfNetworks();
        Network n = null;
        try {
            @SuppressWarnings("null")

            String expResult = String.valueOf(n.getVehicleNo());
            Network result = instance.getVehicleFromNumber(number);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a Invalid Test");

        } catch (Exception e) {

        }

    }

}
