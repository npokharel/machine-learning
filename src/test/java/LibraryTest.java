/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {
    @Test public void testSomeLibraryMethod() {
        /*Library classUnderTest = new Library();

        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());*/

        /*BatchStochastic batchStochastic = new BatchStochastic();
        boolean result = batchStochastic.parseCsv();*/

        JackParse jackParse = new JackParse();
        //boolean result = jackParse.parse();
        boolean result = jackParse.dateTest();

        assertTrue( " should return 'true", result);
    }
}
