package transportsystem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Theja
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
  
   //Employee Test Cases
   TimetableTestCase.class,
   
   //Customer Test Cases
   NetworkTestCase.class
})
public class JunitTestSuite {
    
}
