package com.sen.pt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortfolioTracker {

	private final String STOCK_DELIMITER = ",";
	private final String COMPANY_PRICE_DELIMITER = "-";

	public List<String> readFile(String filename) {
		File file = new File(filename);
		List<String> lines = null;
		if (file.exists()) {
			lines = new ArrayList<>();
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					lines.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return lines;
	}

	public List<FileLine> processFileContent(List<String> lines) {
		List<FileLine> stocks = new ArrayList<>();
		for (String line : lines) {
			String[] companyPriceArray = line.split(STOCK_DELIMITER);
			double totalPrice = 0;
			for (String companyPrice : companyPriceArray) {
				String priceString = companyPrice.split(COMPANY_PRICE_DELIMITER)[1].trim();
				try {
					totalPrice += Double.parseDouble(priceString);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			FileLine sl = new FileLine();
			sl.setStockList(line);
			sl.setTotalPrice(totalPrice);
			stocks.add(sl);
		}
		Collections.sort(stocks, new StockComparator());
		return stocks;
	}

	public void printStockInDescOrder(String file) {
		List<String> lines = readFile(file);
		if (lines != null) {
			List<FileLine> sortedLines = processFileContent(lines);
			for (FileLine line : sortedLines) {
				System.out.println(line.getStockList());
			}
		}
	}

}
