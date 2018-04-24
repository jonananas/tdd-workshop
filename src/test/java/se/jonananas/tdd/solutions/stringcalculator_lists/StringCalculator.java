package se.jonananas.tdd.solutions.stringcalculator_lists;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

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

	private int add(String[] split) {
		List<Integer> ints = parseNumbers(split);
		throwOnNegativeNumbers(ints);
		return ints.stream().mapToInt(x -> x).sum();
	}

	private List<Integer> parseNumbers(String[] numbers) {
		return Arrays.stream(numbers).map(Integer::valueOf).collect(toList());
	}

	private void throwOnNegativeNumbers(List<Integer> ints) {
		if (ints.stream().anyMatch(i -> i < 0))
			throw new IllegalArgumentException(
					"negatives not allowed " + ints.stream().filter(i -> i < 0).map(x -> "" + x).collect(joining(",")));
	}
}
