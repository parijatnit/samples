package com.practice.functional.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pbhattacharya on 3/29/17.
 */
@SuppressWarnings("unchecked")
public class ChainedComparator<T> implements Comparator<T> {

	private List<Comparator<T>> list;

	public ChainedComparator(Comparator<T>... comparators) {
		list = new ArrayList<>(Arrays.asList(comparators));
	}

	@Override
	public int compare(T o1, T o2) {
		int result = 0;
		for(Comparator<T> current : list) {
			result = current.compare(o1, o2);
			if(result != 0) {
				return result;
			}
		}
		return result;
	}
}
