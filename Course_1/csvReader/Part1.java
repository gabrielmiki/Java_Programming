import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String exportItem1 = "cotton";
        String exportItem2 = "flowers";
        
        System.out.println("The countrys that exports and are: \n");
        listExportersTwoProducts(parser, exportItem1, exportItem2);
        System.out.println("\n");
        
        parser = fr.getCSVParser();
        
        String country = "Nauru";
        
        System.out.println(country + " infos: \n");
        System.out.println(countryinfo(parser, country));
        System.out.println("\n");
        
        parser = fr.getCSVParser();
        
        String exportItem = "cocoa";
        
        System.out.println("Number of countrys that exports " + exportItem + " are: \n");
        System.out.println(numberOfExporters(parser, exportItem));
        System.out.println("\n");
        
        parser = fr.getCSVParser();
        
        String amount = "$999,999,999,999";
        
        System.out.println("Number of countrys that earns more than " + amount + " are: \n");
        bigExporters(parser, amount);
    }
    
    public String countryinfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String search = record.get("Country");
            
            if (search.contains(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return country + ": " + exports + ": " + value;
            }
        }
        
        return "Not Found!";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            
            if (export.contains(exportItem)) {
                count = count + 1;
            }
        }
        
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            
            if (value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + ": " + value);
            }
        }
    }
    
    public void testXXX() { 
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String country = "Germany";
        
        System.out.println(country + " infos: \n");
        System.out.println(countryinfo(parser, country));
        System.out.println("\n");
    }
}
