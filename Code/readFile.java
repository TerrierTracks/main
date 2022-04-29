import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
public class readFile  
{  
public static void main(String[] args)   
{  
String line = "";  
String splitBy = ",";  
try   
{  
//parsing a CSV file into BufferedReader class constructor  
BufferedReader br = new BufferedReader(new FileReader("src/TerrierTracksStocks.csv"));  
while ((line = br.readLine()) != null) {  
String[] stock = line.split(splitBy);    // use comma as separator  
if (stock[0].equals("MMM")) {
	System.out.println("Stock [Ticker=" + stock[0] + ", Name=" + stock[1] + ", Opening Price=" + stock[2] + ", Current Price=" + stock[3] + ", Market Cap= " + stock[4] + ", Low= " + stock[5] + ", High= " + stock[6] +"]");  
}} br.close();
}   
catch (IOException e)   
{  
e.printStackTrace();  
}  
}  
}  
