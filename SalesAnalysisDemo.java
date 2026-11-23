import java.util.Scanner;
import java.io.*;

/**
 * The SalesAnalysisDemo class is used to run weekly/months sales numbers
 * @author Alan Wu, awu007@student.sdccd.edu
 * @since 11/22/2026
 * @version v1.0
 */
public class SalesAnalysisDemo
{
    public static void main (String[] args) throws IOException {
        //variables
        String input;
        File file;
        
        //Get user input from user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter path to SalesData.txt file: ");
        input = keyboard.nextLine();
        
        //Ensure that file path exists
        file = new File(input);
        while(!file.exists()) {
            System.out.print("File not found/Incorrect file path!");
            System.out.print("Please enter path to SalesData.txt file: ");
            input = keyboard.nextLine();
        }
        SalesAnalysis numbers = new SalesAnalysis(input);
        numbers.processFile();
        numbers.writeOutput();
    }
}