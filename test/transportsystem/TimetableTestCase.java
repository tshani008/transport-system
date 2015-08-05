package transportsystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import transportsystem.Services;

/**
 *
 * @author Theja
 */
public class TimetableTestCase {

    String message = "Create Timetable ";

    /**
     *
     */
    public Services services = new Services();

    MessageUtil messageUtil = new MessageUtil(message);

    /**
     *Test git comment
     */
    @Test
    public void testPrintMessage() {
        System.out.println("Inside testPrintMessage()");
        Assert.assertEquals(message, messageUtil.printMessage());
    }

    private static final String FILE_NAME = "TimeTable.ser";
    private static SetOfTimetables tablelist = new SetOfTimetables();

    static {

        try {
            tablelist.add(new Planning("Kottawa-Malabe", "Bus", "GA-1236", "10.30", "12.30", "Daily"));
            tablelist.add(new Planning("Kottawa-Petta", "Bus", "36-1236", "10.00", "12.30", "Daily"));
            tablelist.add(new Planning("Kaduwela-Malabe", "Bus", "GAA-1236", "1.30", "2.30", "Weekend"));

            Services service = new Services();

            service.Serialize(tablelist, FILE_NAME);
        } catch (IOException ex) {
            Logger.getLogger(NetworkTestCase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Positive test case for Routes Serialization
     */
    @Test
    public void timeTableTestCase() {

        try {
            Services services1 = new Services();
            int i = 0;
            for (Planning plan : services1.deserialize_timetable(FILE_NAME)) {

                Assert.assertEquals(plan.getRoute(), tablelist.get(i).getRoute());

                i++;
                System.out.println(i + " timeTablekTestcase Passed");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Negative test case for Deserialize Planning class. Use expected exception
     * FileNotFoundException.class for this case
     *
     * @throws java.io.FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test(expected = FileNotFoundException.class)

    public void negativeTestCaseForDeserialize() throws FileNotFoundException, IOException, ClassNotFoundException {
        Services services2 = new Services();

        services2.deserialize_timetable("");

    }

}
