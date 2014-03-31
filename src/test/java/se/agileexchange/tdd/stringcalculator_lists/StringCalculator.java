package se.agileexchange.tdd.stringcalculator_lists;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class StringCalculator {
	
	public int add(String string) {
		String delimiter = "[,\n]";
		String numbers = string;
		if (numbers.isEmpty())
			return 0;
		if (hasNewDelimiter(numbers)) {
			delimiter = parseDelimiter(numbers);
			numbers = removeDelimiterLine(numbers);
		}
		return add(numbers.split(delimiter));
	}

	private String removeDelimiterLine(String numbers) {
		return numbers.substring(4);
	}

	private String parseDelimiter(String numbers) {
		return "[" + numbers.substring(2, 3) + "\n]";
	}

	private boolean hasNewDelimiter(String numbers) {
		return numbers.startsWith("//");
	}

	private Integer parseNumber(String numbers) {
		return Integer.valueOf(numbers);
	}

	private int add(String[] split) {
		List<Integer> ints = parseNumbers(split);
		throwOnNegativeNumbers(ints);
		return sum(ints);
	}

	private int sum(List<Integer> ints) {
		int sum = 0;
		for(int number:ints) {
			sum += number;
		}
		return sum;
	}

	private List<Integer> parseNumbers(String[] split) {
		List<Integer> ints = Lists.newArrayList();  
		for(String number:split) {
			ints.add(parseNumber(number));
		}
		return ints;
	}
	
	private List<Integer> getNegatives(List<Integer> ints) {
		List<Integer> negatives = Lists.newArrayList();  
		for(int intNum:ints) {
			if (intNum < 0)
				negatives.add(intNum);
		}
		return negatives;
	}

	private void throwOnNegativeNumbers(List<Integer> ints) {
		List<Integer> negatives = getNegatives(ints);
		if (!negatives.isEmpty())
			throw new IllegalArgumentException("negatives not allowed " + toString(negatives));
	}

	private String toString(List<Integer> negatives) {
		Joiner joiner = Joiner.on(",");
		return joiner.join(negatives);
	}
}
