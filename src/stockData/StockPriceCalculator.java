package stockData;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

public class StockPriceCalculator {
	
	public String[] stockSymbolList;
	public ArrayList<Stock> stockList;
	
	public int percentCompleted = 0;
	private int stocksCompleted = 0;
	
	public StockPriceCalculator(String[] stockSymbolList) throws IOException {
		this.stockSymbolList = stockSymbolList;
		stockList = new ArrayList<Stock>();
	}
	
	public void populateStockList(JProgressBar progressBar) throws IOException {
		stockList.clear();
		stocksCompleted = 0;
		for (String stock : stockSymbolList) {
			try {
				Stock s = YahooFinance.get(stock, true);
				stockList.add(s);
				stocksCompleted++;
                progressBar.setValue(100 * stocksCompleted / stockSymbolList.length);
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
