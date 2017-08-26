package com.sen.pt;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0) {
			System.out.println("please pass file name as argument.");
			return;
		}
		PortfolioTracker portfolioTracker = new PortfolioTracker();
		portfolioTracker.printStockInDescOrder(args[0]);
	}

}
