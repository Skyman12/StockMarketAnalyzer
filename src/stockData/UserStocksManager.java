package stockData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import yahoofinance.Stock;

public class UserStocksManager {
	final static String fileName = "usersStocks.txt";
	static File file = new File(fileName);
	
	static public void addStock(StockPriceCalculator calc, Stock stock) throws IOException {
		Scanner reader = new Scanner(file);
		ArrayList<String> stockSymbolList = new ArrayList<String>();
		while (reader.hasNextLine()) {
			if (!reader.hasNext()) {
				break;
			}
			String data = reader.next();
			
			if (data.trim().equals(stock.getSymbol())) {
				return;
			} else {
				stockSymbolList.add(data);
			}
		}
		
		stockSymbolList.add(stock.getSymbol());
		calc.addStock(stock.getSymbol());
		writeToFile(stockSymbolList);	
	}
	
	static public void removeStock(StockPriceCalculator calc, Stock stock) throws IOException {
		Scanner reader = new Scanner(file);
		ArrayList<String> stockSymbolList = new ArrayList<String>();
		while (reader.hasNextLine()) {
			if (!reader.hasNext()) {
				break;
			}
			String data = reader.next();
			
			if (!data.trim().equals(stock.getSymbol())) {
				stockSymbolList.add(data);
			} else {
				calc.removeStock(stock.getSymbol());
			}
		}
		
		writeToFile(stockSymbolList);	
	}
	
	public static void writeToFile(ArrayList<String> toWrite) throws IOException {
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		for (String s : toWrite) {
			writer.println(s);
		}
		
		writer.close();
	}

}
