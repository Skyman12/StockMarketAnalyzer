package stockData;

import yahoofinance.Stock;

public class TwelvePointSimulation {
	public StockData stockData;
	public Stock stock;
	
	public double revenueAnalysis;
	public double earningsPerShareAnalysis;
	public double returnOnEquityAnalysis;
	public double earningsForcastAnalysis;
	public double pegAnalysis;
	public double dividendYieldAnalysis;
	public double ebitdaAnalysis;
	public double targetPriceAnalysis;
	
	public TwelvePointSimulation(StockData stockData) {
		this.stockData = stockData;
		this.stock = stockData.getStock();
	}
	
	// Checks if we have a positive revenue
	public int revenueAnalysis() {
		revenueAnalysis = stock.getStats().getRevenue().doubleValue();
		
		if (revenueAnalysis > 0.0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// Checks if we have a positive earnings per share
	public int earningsPerShareAnalysis() {
		earningsPerShareAnalysis = stock.getStats().getEps().doubleValue();
		
		if (earningsPerShareAnalysis > 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// Checks if we have a positive earnings per share
	public int returnOnEquityAnalysis() {
		returnOnEquityAnalysis = stock.getStats().getROE().doubleValue();
		
		if (returnOnEquityAnalysis > 0.0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// Checks if we have a positive earnings forcast for the next year
	public int earningsForcastAnalysis() {
		earningsForcastAnalysis = stock.getStats().getEpsEstimateNextYear().doubleValue();
		
		if (earningsForcastAnalysis > 0.0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// Checks if we have a peg score
	public int pegAnalysis() {
		pegAnalysis = stock.getStats().getPeg().doubleValue();
		
		if (pegAnalysis < 1.0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// Checks if we have a peg score
	public int dividendYieldAnalysis() {
		dividendYieldAnalysis = stock.getDividend().getAnnualYieldPercent().doubleValue();
		
		if (dividendYieldAnalysis == 0) {
			return 0;
		} else if (dividendYieldAnalysis > 2) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// Checks if we have ebitda score less than 10
	public int ebitdaAnalysis() {
		ebitdaAnalysis = stock.getStats().getRevenue().doubleValue() / stock.getStats().getEBITDA().doubleValue();
		
		// If negative cash flow, cannot use this measure.
		if (stock.getStats().getRevenue().doubleValue() < 0 || stock.getStats().getEBITDA().doubleValue() < 0) {
			return 0;
		} else if (ebitdaAnalysis < 10.0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// Checks if the target price projects greater than 5% profit
	public int targetPriceAnalysis() {
		double difference = stock.getStats().getOneYearTargetPrice().doubleValue() - stock.getQuote().getPrice().doubleValue();
		targetPriceAnalysis = (difference * 100) / stock.getQuote().getPrice().doubleValue();
		
		if (targetPriceAnalysis > .05 ) {
			return 1;
		} else {
			return -1;
		}
	}
		
	public int runAnaylsis() {
		int total = 0;
		total += revenueAnalysis();
		total += earningsPerShareAnalysis();
		total += returnOnEquityAnalysis();
		total += earningsForcastAnalysis();
		total += pegAnalysis();
		total += dividendYieldAnalysis();
		total += ebitdaAnalysis();
		total += targetPriceAnalysis();
		
		return total;
	}
	
	
	

}
