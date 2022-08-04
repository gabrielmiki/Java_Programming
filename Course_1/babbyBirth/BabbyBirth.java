import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabbyBirth {
    public FileResource getFile() {
        FileResource fr = new FileResource();
        return fr;
    }
    
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int countGirls = 0;
        int countBoys = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn; 
                countBoys += 1;
            }
            
            else {
                totalGirls += numBorn;
                countGirls += 1;
            }
        }
        
        System.out.println("Total Births: " + totalBirths);
        System.out.println("Total Names: " + (countBoys+countGirls));
        System.out.println("Total Boys: " + totalBoys); 
        System.out.println("Total Boys Names: " + countBoys);
        System.out.println("Total Girls: " + totalGirls); 
        System.out.println("Total Girls Names: " + countGirls);
    }
    
    public void testTotalBirths() {
        totalBirths(getFile());
    }
    
    public int getRank(FileResource fr, String Name, String Gender) {
        int countPrevious = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(Gender)) {
                countPrevious += 1;
                
                if (rec.get(0).equals(Name)) {
                    return countPrevious;
                }
            }
        }
        
        return -1;
    }
    
    public void testGetRank() {
        System.out.println(getRank(getFile(), "Frank", "M"));
    }
    
    public String getName(int year, String Gender, int rank) {
        FileResource fr = new FileResource();
        int countPrevious = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(Gender)) {
                countPrevious += 1;
                
                if (countPrevious == rank) {
                    return rec.get(0);
                }
            }
        }
        
        return "No Name";
    }
    
    public void testGetName() {
        System.out.println(getName(1980, "M", 450));
    }
    
    public void whatIsNameInYear(int year, String name, int newyear, String gender) {
        FileResource fr = new FileResource();
        
        int currRank = getRank(fr, name, gender);
        
        if (currRank == -1) {
            System.out.println("No such name in: " + year);
        }
        
        String newName = getName(newyear, gender, currRank);
        
        System.out.println("The name: " + name + " in " + year + ", \n" + 
                           "would be called: " + newName + " in " + newyear);
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear(1974, "Owen", 2014, "M");
    }
    
    public String yearOfHighestRank(String name, String gender) {
        String year = "";
        int largestRankSoFar = 5;
        int startIndex = 0;
        DirectoryResource dr = new DirectoryResource();
        int count = 0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);            

            if (getRank(fr, name, gender) != -1) {
                
                if (getRank(fr, name, gender) < largestRankSoFar) {
                    largestRankSoFar = getRank(fr, name, gender);
                    year = f.getName();                                      
                }
            }
            
        }
        
        if (largestRankSoFar == 0) {
            return "-1";
        }
        
        // return (year).indexOf("2");
        startIndex = (year).indexOf("2");
        return "alalal";
    }
    
    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Genevieve", "F"));
    }
    
    public double getAverageRank(String name, String gender) {
        double sumRank = 0.0;
        double count = 0.0;
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);            

            if (getRank(fr, name, gender) != -1) {
                sumRank += getRank(fr, name, gender);
                count += 1;
            }            
        }
        
        if (sumRank == 0.0) {
            return -1.0;
        }
        
        return (sumRank / count);
    }
    
    public void testGetAverageRank() {
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource();
        int nameRank = getRank(fr, name, gender);
        int countPrevious = 0;
        int numBirths = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender) && nameRank != -1) {
                countPrevious += 1;
                
                if (countPrevious == nameRank) {
                    break;
                }
                
                numBirths += Integer.parseInt(rec.get(2));
            }
        }
        
        if (numBirths == 0) {
            return -1;
        }
        
        return numBirths;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
