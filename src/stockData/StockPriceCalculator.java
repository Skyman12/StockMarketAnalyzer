package stockData;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

public class StockPriceCalculator {
	private String fileName;
	public ArrayList<String> stockSymbolList;
	public ArrayList<Stock> stockList;
	
	public int percentCompleted = 0;
	private int stocksCompleted = 0;
	
	public StockPriceCalculator(String fileName) throws IOException {
		stockList = new ArrayList<Stock>();
		this.fileName = fileName;
	}
	
	public void addStock(String stockSymbol) throws IOException {
		Stock s = YahooFinance.get(stockSymbol, true);
		stockList.add(s);
	}
	
	public void removeStock(String stockSymbol) throws IOException {
		Stock stock = null;
		for (Stock s : stockList) {
			if (s.getSymbol().equals(stockSymbol)) {
				stock = s;
				break;
			}
		}
		
		stockList.remove(stock);
	}
	
	public void populateStockList(JProgressBar progressBar) throws IOException {
		File file = new File(fileName);
		Scanner reader = new Scanner(file);
		stockSymbolList = new ArrayList<>();
		while (reader.hasNext()) {
			String text = reader.next();
			stockSymbolList.add(text.trim());
			reader.nextLine();
		}
		
		stockList.clear();
		stocksCompleted = 0;
		for (String stock : stockSymbolList) {
			try {
				Stock s = YahooFinance.get(stock, true);
				stockList.add(s);
				stocksCompleted++;
                progressBar.setValue(100 * stocksCompleted / stockSymbolList.size());
                progressBar.update(progressBar.getGraphics());  
			} catch (Exception e) {
				
			}
		}

//		Map<String, Stock> stockMap = YahooFinance.get(stockSymbolList);
//		stockList.addAll(stockMap.values());
	}

	public ArrayList<StockData> getChangeForAllStocks() {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
			stockWithChange.add(new StockData(stock, stock.getQuote().getChange()));
		}
		
		Collections.sort(stockWithChange);
		return stockWithChange;
	}
	
	public ArrayList<StockData> getPercentChangeForAllStocks() {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
				try {
					BigDecimal data = stock.getQuote().getChange().divide(stock.getQuote().getOpen(), 3);
					data = data.multiply(new BigDecimal(100));
					stockWithChange.add(new StockData(stock, data));
				} catch (Exception e) {}
		}
		Collections.sort(stockWithChange);
		return stockWithChange;
	}
	
	public ArrayList<StockData> get8PointAnalysisForAllStocks () {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
				try {
					TwelvePointSimulation simluation = new TwelvePointSimulation(new StockData(stock, null));
					int total = simluation.runAnaylsis();
					stockWithChange.add(new StockData(stock, new BigDecimal(total)));
				} catch (Exception e) {}
		}
		Collections.sort(stockWithChange);
		return stockWithChange;
	}
	
	public ArrayList<StockData> getPriceForAllStocks() {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
			stockWithChange.add(new StockData(stock, stock.getQuote().getPrice()));
		}
		Collections.sort(stockWithChange);
		return stockWithChange;
	}
	
	public ArrayList<StockData> getDividendAnnualYieldForAllStocks() {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
			stockWithChange.add(new StockData(stock, stock.getDividend().getAnnualYield()));
		}
		Collections.sort(stockWithChange);
		return stockWithChange;
	}
	
	public ArrayList<StockData> getDividendAnnualYieldPercentForAllStocks() {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
			stockWithChange.add(new StockData(stock, stock.getDividend().getAnnualYieldPercent()));
		}
		Collections.sort(stockWithChange);
		return stockWithChange;
	}
	
	public ArrayList<StockData> getPEForAllStocks() {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
			stockWithChange.add(new StockData(stock, stock.getStats().getPe()));
		}
		Collections.sort(stockWithChange);
		return stockWithChange;
	}

	public ArrayList<StockData> getRevenueForAllStocks() {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
			stockWithChange.add(new StockData(stock, stock.getStats().getRevenue()));
		}
		Collections.sort(stockWithChange);
		return stockWithChange;
	}
	
	public ArrayList<StockData> getOneYearTargetPrice() {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
			stockWithChange.add(new StockData(stock, stock.getStats().getOneYearTargetPrice()));
		}
		Collections.sort(stockWithChange);
		return stockWithChange;
	}
	
	public ArrayList<StockData> getOneYearTargetChange() {
		ArrayList<StockData> stockWithChange = new ArrayList<StockData>();
		for (Stock stock : stockList) {
			stockWithChange.add(new StockData(stock, stock.getStats().getOneYearTargetPrice().subtract(stock.getQuote().getPrice())));
		}
		Collections.sort(stockWithChange);
		return stockWithChange;
	}
	
	public String displayData(ArrayList<StockData> stockData) {
		return displayData(stockData, SortingTypes.NONE);
	}

	public static String displayData(ArrayList<StockData> stockData, SortingTypes types) {
		Collections.sort(stockData);
		String string = "";
		for (StockData s : stockData) {
			string = string + s.getStock().getSymbol() + " --- " + s.getStock().getName() + " --- " + s.getData() + "\n";
		}
		
		return string;
	}
	
}
