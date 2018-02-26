import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/**
 *
 * @author niraj
 */
public class BatchStochastic {

    /**
     * @param args the command line arguments
     */
    
    public double theta0 = 2.232;
    public double theta1 = -5.232;
    public double alpha = 0.001;
    double summation;
    final int M = 150;
    
    //dataset
    //double x1[]= {68,72,71,69,60,58,36,30,43,56};
    //double x0[]= {1,1,1,1,1,1,1,1,1,1};
    //double y[]= {62,75,72,65,58,52,40,25,38,54};
    
    
    //double x0[]= {1,1,1,1,1,1,1,1,1,1};
    //double x1[]= {1,2,3,4,5,6,7,8,9,10};
    //double y[]= {1.1,4.4,9.9,15.77,25.25,35.96,49.35,64.88,82.32,100.11};

    double xp[] = new double[151];
    double yp[] = new double[151];
    int breaker = 0;
    
    public void calc(double x0[], double x1[], double y[]) {
        for (int repeat =0; repeat <=22500; repeat++ ){


            //System.out.println("\n");
            summation = 0.0;
            for (int i=0; i<M; i++ )
            {
                summation += (y[i] - theta0 - theta1 *x1[i])*x0[i];                
            }            
            theta0 += alpha*summation;

                
            summation = 0.0;                
            for (int i=0; i<M; i++ ) 
            {
                summation += (y[i] - theta0 - theta1 *x1[i])*x1[i];
                //System.out.println("summation= " + summation);
            }
            theta1 += alpha*summation;


            if(repeat%M == 0 ) {
                System.out.println("theta0= " + theta0 + " repeat = " + repeat);
                System.out.println("theta1= " + theta1 + " breaker = "+ breaker);
                xp[breaker] = theta0;
                yp[breaker] = theta1;
                breaker++;
            }
                            
        }
        System.out.println("final value of theta0 = " + theta0);
        System.out.println("final value of theta1 = " + theta1);
        System.out.println("Arrays.toString(xp) = " + Arrays.toString(xp));
        System.out.println("Arrays.toString(yp) = " + Arrays.toString(yp));
    }

    public boolean parseCsv() {

        double x1[] = new double[150];
        double y1[] = new double[150];
        double x0[] = new double[150];

        try {
            Reader in = new FileReader("/home/niraj/Dropbox/msc/ml/data/fertility_Diagnosis.txt");
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

            int count = 0;
            for (CSVRecord record : records) {
                String age = record.get(0);
                String balance = record.get(1);

                x0[count] = 1.0;
                x1[count] = Double.valueOf(age);
                y1[count] = Double.valueOf(balance);
                count++;

            }

            System.out.println("x0 = " + x0);
            System.out.println("x1 = " + x1);
            System.out.println("y1 = " + y1);

            calc(x0, x1, y1 );


        }catch (IOException ioe) {
            System.out.println("ioe = " + ioe);
        }

        return true;
    }
    
}
