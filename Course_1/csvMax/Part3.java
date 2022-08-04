import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part3 {
    public void testLowestHumidityManyFiles() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord currRow : parser) {
            if (currRow.get("PrecipitationIn").indexOf("N") != -1) { 
                System.out.println("--------Etendeu---------");
            }
        }   
    }
}
