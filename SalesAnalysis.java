import java.io.*;
import java.util.Scanner;

/**
 * The SalesAnalysis class reads weekly sales from a text file, reporting weekly and monthly totals.
 * @author Alan Wu, awu007@student.sdccd.edu
 * @since 11/22/2026
 * @version v1.0
 */
public class SalesAnalysis
{
    public static final int WEEKS_IN_MONTH = 4;
    private double[] weeklyTotal;
    private String inputFile;
    private int lineNumber = 0;

    /**
     * Constructor for objects of class SalesAnalysis
     * @param inputFile path to file form Demo
     */
    public SalesAnalysis(String inputFile)
    {
        weeklyTotal = new double [WEEKS_IN_MONTH];
        this.inputFile = inputFile;
    }
    
    /**
     * Reads each line of the input file and splits it into daily sales values.
     */
    public void processFile() throws IOException
    {
        File file = new File(inputFile);
        Scanner input = new Scanner(file);
        
        //creates while loop to read in lines
        while(input.hasNextLine()) {
            String[] days = input.nextLine().split(",");
            setArrayElement(days);
        }
        input.close();
    }
    
    /**
     * Converts daily sales strings to doubles, totals them, and stores it
     * @param inArray array containing sales amounts
     */
    private void setArrayElement(String[] inArray) 
    {
        double total = 0;
        for(String value:inArray) {
            total += Double.parseDouble(value);
        }
        weeklyTotal[lineNumber] = total;
        lineNumber++;
    }
    
    /**
     * Displays each week's total and average daily sales, then total sales, average weekly sales, and the highest and lowest weeks.
     */
    public void writeOutput() {
        double sales; //weekly sales number
        double min, max; //used for min/max weeks
        double dailySales; //average daily sales per week
        int minWeek = 0, maxWeek = 0; //indices for min/max
        double totalSales = 0; //accumulator to add weeklyTotal
        min = max = weeklyTotal[0]; //sets min and max to first weeklyTotal number
        System.out.println(); //separation from input file dialogue
        
        //for loop to process through weeklyTotal
        for (int i=0;i<weeklyTotal.length;i++) {
            sales = weeklyTotal[i]; //grab weekly total value
            totalSales += sales; //add weekly total to totalSales
            if (sales < min) {
                min = sales;
                minWeek = i+1;
            }
            if (sales > max) {
                max = sales;
                maxWeek = i+1;
            }
            System.out.println("Week" + (i+1) + " Info");
            System.out.printf("Total Sales: $%,.2f\n", sales);
            dailySales = sales/7;
            System.out.printf("Avg Daily Sales for Week: $%,.2f\n\n", dailySales);
        }
        
        //Summary for the month
        System.out.printf("Total Salesfor all Weeks: $%,.2f\n", totalSales);
        System.out.printf("Avg Weekly Sales: $%,.2f\n", totalSales/WEEKS_IN_MONTH);
        System.out.println("Week" + maxWeek + " had the highest amount of sales");
        System.out.println("Week" + minWeek + " had the highest amount of sales");
    }
}