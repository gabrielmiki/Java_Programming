import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Part2 {
    public CSVRecord getMinimumOfTwo(CSVRecord currRow, CSVRecord minimumSoFar) {
        
        if (currRow.get("Humidity").indexOf("N") == -1) {
            if (minimumSoFar == null) {
                minimumSoFar = currRow; 
            }
        
            else {
                double currHum = Double.parseDouble(currRow.get("Humidity"));
                double minimumHum = Double.parseDouble(minimumSoFar.get("Humidity"));
                
                if (currHum < minimumHum) {
                    minimumSoFar = currRow;
                }
            }
        }
        
        return minimumSoFar;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord minimumSoFar = null;
        
        for (CSVRecord currRow : parser) {
            minimumSoFar = getMinimumOfTwo(currRow, minimumSoFar);
        }
        
        return minimumSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("The lowest humidity was: " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            CSVRecord currRow = lowestHumidityInFile(fr.getCSVParser());
            

            lowestSoFar = getMinimumOfTwo(currRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    
    public void testLowestHumidityManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double media = 0.0;
        double count = 0.0;
        for (CSVRecord currRow : parser) {
            media = media + Double.parseDouble(currRow.get("TemperatureF"));
            count = count + 1;
        }
        
        return (media / count);
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println("The avavrage temperature was: " + averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureInFile(CSVParser parser, int value) {
        CSVRecord minimumSoFar = null;
        double media = 0.0;
        double count = 0.0;
        
        for (CSVRecord currRow : parser) {
            if (Double.parseDouble(currRow.get("Humidity")) > value) {
                media = media + Double.parseDouble(currRow.get("TemperatureF"));
                count = count + 1;
            }
        }
        
        if (count == 0.0) {
            return 0.0;
        }
        
        return (media / count);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double result = averageTemperatureInFile(parser, 80);
        if (result == 0.0) {
            System.out.println("No temperatures with that level of humidity");
        }
        else {
            System.out.println("The avavrage temperature byond some value was: " + result);
        }
    }
    
}
