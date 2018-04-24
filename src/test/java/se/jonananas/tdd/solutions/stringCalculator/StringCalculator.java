package se.jonananas.tdd.solutions.stringCalculator;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

	public int add(String numbers) {
		String delimiter = "[,\n]";
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

	private int add(String[] numbers) {
		List<Integer> intList = stream(numbers).map(Integer::valueOf).collect(Collectors.toList());
		if (intList.stream().anyMatch(i -> i < 0))
			throw new IllegalArgumentException(
					"negatives not allowed " + intList.stream().filter(i -> i < 0).map(x -> "" + x).collect(joining(",")));
		return intList.stream().mapToInt(x -> x).sum();
	}
}
