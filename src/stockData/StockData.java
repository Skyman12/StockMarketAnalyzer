package stockData;
import java.math.BigDecimal;

import yahoofinance.Stock;

public class StockData implements Comparable {
	
	private Stock stock;
	private BigDecimal data;
	
	public StockData(Stock stock, BigDecimal data) {
		this.stock = stock;
		this.data = data;
	}
	
	public Stock getStock() {
		return stock;
	}
	
	public BigDecimal getData() {
		return data;
	}
	
	@Override
	public int compareTo(Object other) {
		if (other == null || other.getClass() != this.getClass()) {
			return -1;
		}
		
		StockData temp = (StockData) other;
		
	    return temp.data.compareTo(data);
	}
	
	public String toString() {
		return getStock().getSymbol() + " -- " + getStock().getName() + " : " + getData();
	}

}
