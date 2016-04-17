package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import stockData.StockData;
import stockData.TwelvePointSimulation;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;

public class TwelvePointSimulationFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblStockName;
	private JLabel lblStockNameText;
	private JLabel lblStockSymbol;
	private JLabel lblStockSymbolText;
	private JLabel lblNewLabel;
	private JLabel lblRevenueAnalysis;
	private JLabel lblRevenueAnalysisText;
	private JLabel lblEarningsPerShareAnalysis;
	private JLabel lblEarningsPerShareAnalysisText;
	private JLabel lblReturnOnEquityAnalysis;
	private JLabel lblReturnOnEquityAnalysisText;
	private JLabel lblEarningsForcastAnalysis;
	private JLabel lblEarningsForcastAnalysisText;
	private JLabel lblPegAnalysis;
	private JLabel lblPegAnalysisText;
	private JLabel lblDividendYieldAnalysis;
	private JLabel lblDividendYieldAnalysisText;
	private JLabel lblBITDAAnalysis;
	private JLabel lblBITDAAnalysisText;
	private JLabel lblTargetPriceAnalysis;
	private JLabel lblTargetPriceAnalysisText;
	private JLabel label;
	private JLabel lblTotal;
	private JLabel totalScoreText;
	
	/**
	 * Create the panel.
	 */
	public TwelvePointSimulationFrame(StockData stockData) {
		setBounds(100, 100, 721, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TwelvePointSimulation simulation = new TwelvePointSimulation(stockData);
		int total = simulation.runAnaylsis();
		
		lblStockName = new JLabel("Stock Name: ");
		lblStockName.setBounds(12, 13, 91, 16);
		contentPane.add(lblStockName);
		
		lblStockNameText = new JLabel(simulation.stock.getName());
		lblStockNameText.setBounds(103, 13, 445, 16);
		contentPane.add(lblStockNameText);
		
		lblStockSymbol = new JLabel("Stock Symbol: ");
		lblStockSymbol.setBounds(12, 42, 91, 16);
		contentPane.add(lblStockSymbol);
		
		lblStockSymbolText = new JLabel(simulation.stock.getSymbol());
		lblStockSymbolText.setBounds(103, 42, 397, 16);
		contentPane.add(lblStockSymbolText);
		
		lblNewLabel = new JLabel("-----------------------------------------------------");
		lblNewLabel.setBounds(12, 71, 322, 16);
		contentPane.add(lblNewLabel);
		
		lblRevenueAnalysis = new JLabel("Revenue Analysis: ");
		lblRevenueAnalysis.setOpaque(true);
		lblRevenueAnalysis.setBackground(Color.BLACK);
		lblRevenueAnalysis.setForeground(getColor(simulation.revenueAnalysis()));
		lblRevenueAnalysis.setBounds(12, 100, 182, 16);
		contentPane.add(lblRevenueAnalysis);
		
		lblRevenueAnalysisText = new JLabel(simulation.revenueAnalysis + "");
		lblRevenueAnalysisText.setBounds(206, 100, 239, 16);
		contentPane.add(lblRevenueAnalysisText);
		
		lblEarningsPerShareAnalysis = new JLabel("Earnings Per Share Analysis: ");
		lblEarningsPerShareAnalysis.setOpaque(true);
		lblEarningsPerShareAnalysis.setBackground(Color.BLACK);
		lblEarningsPerShareAnalysis.setForeground(getColor(simulation.earningsPerShareAnalysis()));
		lblEarningsPerShareAnalysis.setBounds(12, 129, 182, 16);
		contentPane.add(lblEarningsPerShareAnalysis);
		
		lblEarningsPerShareAnalysisText = new JLabel(simulation.earningsPerShareAnalysis + "");
		lblEarningsPerShareAnalysisText.setBounds(206, 129, 239, 16);
		contentPane.add(lblEarningsPerShareAnalysisText);
		
		lblReturnOnEquityAnalysis = new JLabel("Return on Equity Analysis: ");
		lblReturnOnEquityAnalysis.setOpaque(true);
		lblReturnOnEquityAnalysis.setBackground(Color.BLACK);
		lblReturnOnEquityAnalysis.setForeground(getColor(simulation.returnOnEquityAnalysis()));
		lblReturnOnEquityAnalysis.setBounds(12, 158, 182, 16);
		contentPane.add(lblReturnOnEquityAnalysis);
		
		lblReturnOnEquityAnalysisText = new JLabel(simulation.returnOnEquityAnalysis + "");
		lblReturnOnEquityAnalysisText.setBounds(206, 158, 239, 16);
		contentPane.add(lblReturnOnEquityAnalysisText);
		
		lblEarningsForcastAnalysis = new JLabel("Earnings Forcast Analysis: ");
		lblEarningsForcastAnalysis.setOpaque(true);
		lblEarningsForcastAnalysis.setBackground(Color.BLACK);
		lblEarningsForcastAnalysis.setForeground(getColor(simulation.earningsForcastAnalysis()));
		lblEarningsForcastAnalysis.setBounds(12, 187, 182, 16);
		contentPane.add(lblEarningsForcastAnalysis);
		
		lblEarningsForcastAnalysisText = new JLabel(simulation.earningsForcastAnalysis + "");
		lblEarningsForcastAnalysisText.setBounds(206, 187, 239, 16);
		contentPane.add(lblEarningsForcastAnalysisText);
		
		lblPegAnalysis = new JLabel("PEG Analysis: ");
		lblPegAnalysis.setOpaque(true);
		lblPegAnalysis.setBackground(Color.BLACK);
		lblPegAnalysis.setForeground(getColor(simulation.pegAnalysis()));
		lblPegAnalysis.setBounds(12, 216, 182, 16);
		contentPane.add(lblPegAnalysis);
		
		lblPegAnalysisText = new JLabel(simulation.pegAnalysis + "");
		lblPegAnalysisText.setBounds(206, 216, 239, 16);
		contentPane.add(lblPegAnalysisText);
		
		lblDividendYieldAnalysis = new JLabel("Dividend Yield Analysis: ");
		lblDividendYieldAnalysis.setOpaque(true);
		lblDividendYieldAnalysis.setBackground(Color.BLACK);
		lblDividendYieldAnalysis.setForeground(getColor(simulation.dividendYieldAnalysis()));
		lblDividendYieldAnalysis.setBounds(12, 245, 182, 16);
		contentPane.add(lblDividendYieldAnalysis);
		
		lblDividendYieldAnalysisText = new JLabel(simulation.dividendYieldAnalysis + "");
		lblDividendYieldAnalysisText.setBounds(206, 245, 239, 16);
		contentPane.add(lblDividendYieldAnalysisText);
		
		lblBITDAAnalysis = new JLabel("EBITDA Analysis: ");
		lblBITDAAnalysis.setOpaque(true);
		lblBITDAAnalysis.setBackground(Color.BLACK);
		lblBITDAAnalysis.setForeground(getColor(simulation.ebitdaAnalysis()));
		lblBITDAAnalysis.setBounds(12, 274, 182, 16);
		contentPane.add(lblBITDAAnalysis);
		
		lblBITDAAnalysisText = new JLabel(simulation.ebitdaAnalysis + "");
		lblBITDAAnalysisText.setBounds(206, 274, 239, 16);
		contentPane.add(lblBITDAAnalysisText);
		
		lblTargetPriceAnalysis = new JLabel("Target Price Analysis: ");
		lblTargetPriceAnalysis.setOpaque(true);
		lblTargetPriceAnalysis.setBackground(Color.BLACK);
		lblTargetPriceAnalysis.setForeground(getColor(simulation.targetPriceAnalysis()));
		lblTargetPriceAnalysis.setBounds(12, 303, 182, 16);
		contentPane.add(lblTargetPriceAnalysis);
		
		lblTargetPriceAnalysisText = new JLabel(simulation.targetPriceAnalysis + "");
		lblTargetPriceAnalysisText.setBounds(206, 303, 239, 16);
		contentPane.add(lblTargetPriceAnalysisText);
		
		label = new JLabel("-----------------------------------------------------");
		label.setBounds(12, 332, 322, 16);
		contentPane.add(label);
		
		lblTotal = new JLabel("Total Score: ");
		lblTotal.setOpaque(true);
		lblTotal.setBackground(Color.BLACK);
		lblTotal.setForeground(Color.GREEN);
		lblTotal.setBounds(12, 303, 182, 16);
		lblTotal.setBounds(12, 361, 91, 16);
		contentPane.add(lblTotal);
		
		totalScoreText = new JLabel(total + "");
		totalScoreText.setBounds(120, 361, 41, 16);
		contentPane.add(totalScoreText);
	}
	
	public Color getColor(int choice) {
		if (choice > 0) {
			return Color.GREEN;
		} else if (choice < 0) {
			return Color.RED;
		} else {
			return Color.WHITE;
		}
	}

}
