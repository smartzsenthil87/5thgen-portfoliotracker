package com.sen.pt;

import java.util.Comparator;

public class StockComparator implements Comparator<FileLine> {

	@Override
	public int compare(FileLine o1, FileLine o2) {
		return (int) (o2.getTotalPrice() - o1.getTotalPrice());

	}

}
