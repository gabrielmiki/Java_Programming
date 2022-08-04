import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Part1 {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        
        for (CSVRecord currRow : parser) {
            largestSoFar = getLargestOfTwo(currRow, largestSoFar);
        }
        
        return largestSoFar;
    }
    
    public void hottestDayIn() {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }
    
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            CSVRecord currRow = hottestHourInFile(fr.getCSVParser());
            
            largestSoFar = getLargestOfTwo(currRow, largestSoFar);
        }
        
        return largestSoFar;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currRow, CSVRecord largestSoFar) {
        
        if (largestSoFar == null) {
            largestSoFar = currRow; 
        }
        
        else {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                
            if (currTemp > largestTemp) {
                largestSoFar = currRow;
            }
        }
        
        return largestSoFar;
    }
    
    public void testhottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord minimumSoFar = null;
        
        for (CSVRecord currRow : parser) {
            minimumSoFar = getMinimumOfTwo(currRow, minimumSoFar);
        }
        
        return minimumSoFar;
    }
    
    public CSVRecord getMinimumOfTwo(CSVRecord currRow, CSVRecord minimumSoFar) {
        
        if (minimumSoFar == null) {
            minimumSoFar = currRow; 
        }
        
        else {
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double minimumTemp = Double.parseDouble(minimumSoFar.get("TemperatureF"));
                
            if (currTemp < minimumTemp && currTemp != -9999) {
                minimumSoFar = currRow;
            }
        }
        
        return minimumSoFar;
    }
    
    public void testMinDayIn() {
        FileResource fr = new FileResource();
        CSVRecord minimum = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + minimum.get("TemperatureF") + " at " + minimum.get("TimeEDT"));
        // Other possible features: Dew PointF / Humidity / Sea Level PressureIn / VisibilityMPH / Wind Direction
    }
    
    public CSVRecord fileWithColdestTemperature() {
        CSVRecord minimumSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            CSVRecord currRow = coldestHourInFile(fr.getCSVParser());
            
            minimumSoFar = getMinimumOfTwo(currRow, minimumSoFar);
        }
        
        return minimumSoFar;
    }
    
    public void testFileWithColdestTemperature() {
        CSVRecord minimum = fileWithColdestTemperature();
        System.out.println("Lowest temperature was " + minimum.get("TemperatureF") + " at " + minimum.get("DateUTC"));
    }
}
