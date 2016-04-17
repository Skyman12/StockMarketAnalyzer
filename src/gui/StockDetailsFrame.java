package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.io.IOException;

public class StockDetailsFrame extends JFrame {
	
	private JPanel contentPane;
	
	private JLabel lblStockName;
	private JLabel lblStockSymbol;
	private JLabel lblStockNameText;
	private JLabel lblStockSymbolText;
	private JScrollPane scrollPane;
	private JTable table;
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public StockDetailsFrame(Stock stock) throws IOException {
		setBounds(100, 100, 1269, 708);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblStockName = new JLabel("Stock Name:");
		lblStockName.setBounds(12, 13, 90, 16);
		contentPane.add(lblStockName);
		
		lblStockSymbol = new JLabel("Stock Symbol: ");
		lblStockSymbol.setBounds(12, 40, 142, 16);
		contentPane.add(lblStockSymbol);
		
		lblStockNameText = new JLabel(stock.getName());
		lblStockNameText.setBounds(114, 13, 125, 16);
		contentPane.add(lblStockNameText);
		
		lblStockSymbolText = new JLabel(stock.getSymbol());
		lblStockSymbolText.setBounds(114, 40, 125, 16);
		contentPane.add(lblStockSymbolText);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 85, 1227, 563);
		contentPane.add(scrollPane);
		
		StockQuote stockQuote = stock.getQuote(true);
		StockStats stockStats = stock.getStats(true);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setFocusable(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"-----Current Data-----", null},
				{"Current Price", stockQuote.getPrice()},
				{"Day Low", stockQuote.getDayLow()},
				{"Day High", stockQuote.getDayHigh()},
				{"-----Change Data-----", null},
				{"Today's Change", stockQuote.getChange()},
				{"Today's Change %", stockQuote.getChangeInPercent()},
				{"Change from Average 50", stockQuote.getChangeFromAvg50()},
				{"Change from Average 50 %", stockQuote.getChangeFromAvg50InPercent()},
				{"Change from Average 200", stockQuote.getChangeFromAvg200()},
				{"Change from Average 200 %", stockQuote.getChangeFromAvg200InPercent()},
				{"-----Year Data-----", null},
				{"Year Low", stockQuote.getYearLow()},
				{"Year High", stockQuote.getYearHigh()},
				{"-----Stock Stats Data-----", null},
				{"Book Value per Share", stockStats.getBookValuePerShare()},
				{"EBITDA", stockStats.getEBITDA()},
				{"Earning per Share", stockStats.getEps()},
				{"Market Cap", stockStats.getMarketCap()},
				{"Price / Earnings", stockStats.getPe()},
				{"Price / Earnings to Growth" , stockStats.getPeg()},
				{"Price Book", stockStats.getPriceBook()},
				{"Price Sales", stockStats.getPriceSales()},
				{"Revenue", stockStats.getRevenue()},
				{"Return on Equity", stockStats.getROE()},
				{"Shares Float", stockStats.getSharesFloat()},
				{"Shares Outstanding", stockStats.getSharesOutstanding()},
				{"Shares Owned", stockStats.getSharesOwned()},
				{"-----Stock Projections-----", null},
				{"Projected Earnings per Share -- Current Year", stockStats.getEpsEstimateCurrentYear()},
				{"Projected Earnings per Share -- Next Quarter", stockStats.getEpsEstimateNextQuarter()},
				{"Projected Earnings per Share -- Next Year", stockStats.getEpsEstimateNextYear()},
				{"One Year Target Price", stockStats.getOneYearTargetPrice()},
			},
			new String[] {
				"Data", "Current Value"
			}
		));
		scrollPane.setViewportView(table);
	}
}
