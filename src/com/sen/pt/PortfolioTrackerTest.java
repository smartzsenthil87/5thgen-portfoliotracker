package com.sen.pt;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PortfolioTrackerTest {

	private PortfolioTracker portfolioTracker;

	@Before
	public void init() {
		portfolioTracker = new PortfolioTracker();
	}

	@Test
	public void processFileContentTest() {
		List<String> lines = new ArrayList<>();
		lines.add("GOOG - 100, AMZN - 90, MS - 80");
		lines.add("GOOG - 50, MS - 10");
		lines.add("SGI - 100, GOOG - 50, MS - 10");
		List<FileLine> stockList = portfolioTracker.processFileContent(lines);
		assertEquals("GOOG - 100, AMZN - 90, MS - 80", stockList.get(0).getStockList());
		assertEquals("SGI - 100, GOOG - 50, MS - 10", stockList.get(1).getStockList());
		assertEquals("GOOG - 50, MS - 10", stockList.get(2).getStockList());
		for (FileLine line : stockList) {
			System.out.println(line.getStockList());
		}
	}

	@Test
	public void readFileTest() {
		List<String> lines = portfolioTracker.readFile(getClass().getResource("portfolio.txt").getFile());
		assertEquals("GOOG - 100, AMZN - 90, MS - 80", lines.get(2));
		assertEquals("SGI - 100, GOOG - 50, MS - 10", lines.get(1));
		assertEquals("GOOG - 50, MS - 10", lines.get(0));
	}

}
