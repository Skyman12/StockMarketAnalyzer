package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import stockData.StockData;
import stockData.StockPriceCalculator;
import stockData.TwelvePointSimulation;
import stockData.UserStocksManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainApplication extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	
	private static StockPriceCalculator stockPriceCalculator;
	private static StockPriceCalculator watchlistPriceCalculator;
	private String[] sortingOptions = {
			"By Change", 
			"By Price", 
			"By Percent Change", 
			"By Dividend Annual Yield" , 
			"By Dividend Annual Yield Percent" , 
			"By PE", 
			"By Revenue", 
			"By One Year Target Price" , 
			"By One Year Target Change",
			"By 8 Point Analysis"};
	
	private JScrollPane allStocksScrollPane;
	private JPanel allStocksPanel;
	private JList allStocksList;
	private JButton btnUpdateStocks;
	private JProgressBar progressBar;
	private JLabel lblLastUpdated;
	private JLabel lblLastUpdatedValue;
	private JPanel panelAllStocksUpdate;
	private JPanel panelAllStocksInteraction;
	private JButton btnAddStockToWatchlist;
	private JButton btnAllStocksViewStock;
	private JLabel lblSortBy;
	private JComboBox allStocksSortBy;
	
	private JScrollPane myWatchlistScrollPane;
	private JPanel myWatchlistPanel;
	private JPanel panelMyWatchlistUpdate;
	private JLabel labelMyWatchlistLastUpdated;
	private JLabel labelMyWatchlistLastUpdateText;
	private JProgressBar myWatchlistProgressbar;
	private JButton btnUpdateWatchlistStocks;
	private JPanel panelMyWatchlistInteraction;
	private JButton btnRemoveStockFrom;
	private JLabel labelWatchlistSortBy;
	private JComboBox watchlistComboBox;
	private JButton btnWatchlistViewStock;
	private JList usersWatchlist;
	private JButton btnRunPoint;
	private JButton btnWatchListSimulation;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stockPriceCalculator = new StockPriceCalculator("companylist.txt");
					watchlistPriceCalculator = new StockPriceCalculator("usersStocks.txt");
					
					MainApplication frame = new MainApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		allStocksScrollPane = new JScrollPane();
		tabbedPane.addTab("All Stocks", null, allStocksScrollPane, null);
		
		allStocksPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) allStocksPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		allStocksScrollPane.setColumnHeaderView(allStocksPanel);
		
		panelAllStocksUpdate = new JPanel();
		FlowLayout fl_panelAllStocksUpdate = (FlowLayout) panelAllStocksUpdate.getLayout();
		fl_panelAllStocksUpdate.setAlignment(FlowLayout.LEFT);
		allStocksPanel.add(panelAllStocksUpdate);
		
		lblLastUpdated = new JLabel("Last Updated: ");
		panelAllStocksUpdate.add(lblLastUpdated);
		
		lblLastUpdatedValue = new JLabel("Never");
		panelAllStocksUpdate.add(lblLastUpdatedValue);
		
		progressBar = new JProgressBar();
		panelAllStocksUpdate.add(progressBar);
		
		btnUpdateStocks = new JButton("Update Stocks");
		btnUpdateStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					stockPriceCalculator.populateStockList(progressBar);
					allStocksList.setListData(getChoice(stockPriceCalculator, allStocksSortBy.getSelectedIndex()).toArray());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
				Calendar cal = Calendar.getInstance();
				lblLastUpdatedValue.setText(dateFormat.format(cal.getTime()));
			}
		});
		panelAllStocksUpdate.add(btnUpdateStocks);
		
		panelAllStocksInteraction = new JPanel();
		allStocksPanel.add(panelAllStocksInteraction);
		
		btnAddStockToWatchlist = new JButton("Add Stock to Watchlist");
		btnAddStockToWatchlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StockData currentStock = (StockData) allStocksList.getSelectedValue();
				try {
					UserStocksManager.addStock(watchlistPriceCalculator, currentStock.getStock());
					usersWatchlist.setListData(getChoice(watchlistPriceCalculator, watchlistComboBox.getSelectedIndex()).toArray());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelAllStocksInteraction.add(btnAddStockToWatchlist);
		
		btnAllStocksViewStock = new JButton("View Stock");
		btnAllStocksViewStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				viewStock(allStocksList);
			}
		});
		panelAllStocksInteraction.add(btnAllStocksViewStock);
		
		btnRunPoint = new JButton("Run 8 Point Analysis");
		btnRunPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRunPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				run8PointSimulation(allStocksList);
			}
		});
		panelAllStocksInteraction.add(btnRunPoint);
		
		lblSortBy = new JLabel("Sort by: ");
		panelAllStocksInteraction.add(lblSortBy);
		
		allStocksSortBy = new JComboBox(sortingOptions);
		allStocksSortBy.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				allStocksList.setListData(getChoice(stockPriceCalculator, allStocksSortBy.getSelectedIndex()).toArray());
			}
		});
		panelAllStocksInteraction.add(allStocksSortBy);
		
		allStocksList = new JList();
		
		allStocksScrollPane.setViewportView(allStocksList);
		
		myWatchlistScrollPane = new JScrollPane();
		tabbedPane.addTab("My Watchlist", null, myWatchlistScrollPane, null);
		
		myWatchlistPanel = new JPanel();
		flowLayout_1 = (FlowLayout) myWatchlistPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		myWatchlistScrollPane.setColumnHeaderView(myWatchlistPanel);
		
		panelMyWatchlistUpdate = new JPanel();
		myWatchlistPanel.add(panelMyWatchlistUpdate);
		
		labelMyWatchlistLastUpdated = new JLabel("Last Updated: ");
		panelMyWatchlistUpdate.add(labelMyWatchlistLastUpdated);
		
		labelMyWatchlistLastUpdateText = new JLabel("Never");
		panelMyWatchlistUpdate.add(labelMyWatchlistLastUpdateText);
		
		myWatchlistProgressbar = new JProgressBar();
		panelMyWatchlistUpdate.add(myWatchlistProgressbar);
		
		btnUpdateWatchlistStocks = new JButton("Update Stocks");
		btnUpdateWatchlistStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					watchlistPriceCalculator.populateStockList(myWatchlistProgressbar);
					usersWatchlist.setListData(getChoice(watchlistPriceCalculator, watchlistComboBox.getSelectedIndex()).toArray());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				DateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
				Calendar cal = Calendar.getInstance();
				labelMyWatchlistLastUpdateText.setText(dateFormat.format(cal.getTime()));
			}
		});
		panelMyWatchlistUpdate.add(btnUpdateWatchlistStocks);
		
		panelMyWatchlistInteraction = new JPanel();
		myWatchlistPanel.add(panelMyWatchlistInteraction);
		
		btnRemoveStockFrom = new JButton("Remove Stock from Watchlist");
		btnRemoveStockFrom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StockData currentStock = (StockData) usersWatchlist.getSelectedValue();
				try {
					UserStocksManager.removeStock(watchlistPriceCalculator, currentStock.getStock());
					usersWatchlist.setListData(getChoice(watchlistPriceCalculator, watchlistComboBox.getSelectedIndex()).toArray());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelMyWatchlistInteraction.add(btnRemoveStockFrom);
		
		btnWatchlistViewStock = new JButton("View Stock");
		btnWatchlistViewStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewStock(usersWatchlist);
			}
		});
		panelMyWatchlistInteraction.add(btnWatchlistViewStock);
		
		btnWatchListSimulation = new JButton("Run 8 Point Simulation");
		btnWatchListSimulation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				run8PointSimulation(usersWatchlist);
			}
		});
		panelMyWatchlistInteraction.add(btnWatchListSimulation);
		
		labelWatchlistSortBy = new JLabel("Sort by: ");
		panelMyWatchlistInteraction.add(labelWatchlistSortBy);
		
		watchlistComboBox = new JComboBox(sortingOptions);
		watchlistComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				usersWatchlist.setListData(getChoice(watchlistPriceCalculator, watchlistComboBox.getSelectedIndex()).toArray());
			}
		});
		panelMyWatchlistInteraction.add(watchlistComboBox);
		
		usersWatchlist = new JList();
		myWatchlistScrollPane.setViewportView(usersWatchlist);
		
	}
	
	protected void run8PointSimulation(JList currentList) {
		StockData currentStock = (StockData) currentList.getSelectedValue();
		TwelvePointSimulationFrame newPanel = new TwelvePointSimulationFrame(currentStock);
		newPanel.setVisible(true);	
	}

	protected void viewStock(JList currentList) {
		StockData currentStock = (StockData) currentList.getSelectedValue();
		StockDetailsFrame newPanel = null;
		try {
			newPanel = new StockDetailsFrame(currentStock.getStock());
		} catch (IOException e) {
			e.printStackTrace();
		}
		newPanel.setVisible(true);	
	}

	private ArrayList<StockData> getChoice(StockPriceCalculator calculator, int choice) {
		switch (choice) {
		case 0: 
			return calculator.getChangeForAllStocks();
		case 1: 
			return calculator.getPriceForAllStocks();
		case 2: 
			return calculator.getPercentChangeForAllStocks();
		case 3: 
			return calculator.getDividendAnnualYieldForAllStocks();
		case 4: 
			return calculator.getDividendAnnualYieldPercentForAllStocks();
		case 5: 
			return calculator.getPEForAllStocks();
		case 6: 
			return calculator.getRevenueForAllStocks();
		case 7: 
			return calculator.getOneYearTargetPrice();
		case 8: 
			return calculator.getOneYearTargetChange();
		case 9:
			return calculator.get8PointAnalysisForAllStocks();
		default:
			return calculator.getPriceForAllStocks();
		}	
	}
}
