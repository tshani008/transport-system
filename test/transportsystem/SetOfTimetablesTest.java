/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportsystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Theja
 */
public class SetOfTimetablesTest {

    /**
     *
     */
    public SetOfTimetablesTest() {
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
     * Test of addTimeTable method, of class SetOfTimetables.
     */
    @Test
    public void testAddTimeTable() {
        System.out.println("addTimeTable");
        Planning timetable = new Planning("GA-1236", "Bus", "kottawa-Malabe", "8.30", "9.30", "Daily");
        SetOfTimetables instance = new SetOfTimetables();
     
       instance.addTimeTable(timetable);
      // assertEquals(instance, timetable);
       assertNotNull("Pass Add Method", timetable);
     
        
    

    }

    /**
     * Test of removeTimeTable method, of class SetOfTimetables.
     */
    @Test
    public void testRemoveTimeTable() {
        System.out.println("removeTimeTable");
        Planning timetable = new Planning();
        SetOfTimetables instance = new SetOfTimetables();
        instance.removeTimeTable(timetable);
        assertSame("matches", instance.remove(timetable),false);
      fail("The test case is a Failed Case.");
    }

    /**
     * Test of getRouteFromNumber method, of class SetOfTimetables.
     */
    @Test
    public void testGetRouteFromNumber() {
        System.out.println("getRouteFromNumber");
        String number = "GA-1236";
        SetOfTimetables instance = new SetOfTimetables();
        Planning p = null;
        try {
            String expResult = String.valueOf(p.getVehicleno());
            Planning result = instance.getRouteFromNumber(number);
            assertEquals(expResult, result);
             // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a Failed Test case");
        } catch (Exception e) {
        }

       
    }

    /**
     *
     */
    @Test
    public void testGetRouteFromNumber2() {
        System.out.println("getRouteFromNumber");
        String number = "15-1236";
        SetOfTimetables instance = new SetOfTimetables();
        Planning p = null;
        try {
            String expResult = String.valueOf(p.getVehicleno());
            Planning result = instance.getRouteFromNumber(number);
            assertEquals(expResult, result);
             // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (Exception e) {
           
        }

       
    }

    /**
     *
     */
    @Test
    public void testGetRouteFromNumber3() {
        System.out.println("getRouteFromNumber");
        String number = "3699141";
        SetOfTimetables instance = new SetOfTimetables();
        Planning p = null;
        try {
            String expResult = String.valueOf(p.getVehicleno());
            Planning result = instance.getRouteFromNumber(number);
            assertEquals(expResult, result);
             // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a failed Test case.");
        } catch (Exception e) {
         
        }

       
    }
}
