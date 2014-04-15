package se.agileexchange.tdd.stringCalculatorWithLogging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StringCalculator {

	Logger logger;

	public int add(String numberString) {
		String delimiter = parseDelimiterOrUseDefault(numberString);
		String numbers = getNumbersWithoutDelimiter(numberString);
		final int result = add(numbers, delimiter);
		logger.log(Level.INFO, "" + result);
		return result;
	}

	private String parseDelimiterOrUseDefault(String string) {
		String delimiter = "[,\n]";
		if (hasNewDelimiter(string)) {
			delimiter = parseDelimiter(string);
		}
		return delimiter;
	}

	private String getNumbersWithoutDelimiter(String numbers) {
		if (hasNewDelimiter(numbers)) {
			return removeDelimiterDefinition(numbers);
		}
		return numbers;
	}
	
	private boolean hasNewDelimiter(String numbers) {
		return numbers.startsWith("//");
	}

	private int add(String numbers, String delimiter) {
		if (numbers.isEmpty())
			return 0;
		return addNumbers(numbers.split(delimiter));
	}

	private String removeDelimiterDefinition(String numbers) {
		return numbers.substring(4);
	}

	private String parseDelimiter(String numbers) {
		return "[" + numbers.substring(2, 3) + "\n]";
	}

	private Integer parseNumber(String numbers) {
		return Integer.valueOf(numbers);
	}

	private int addNumbers(String[] split) {
		throwOnNegativeNumbers(split);
		int sum = 0;
		for (String number : split) {
			Integer intNum = parseNumber(number);
			sum += intNum;
		}
		return sum;
	}

	private void throwOnNegativeNumbers(String[] split) {
		String invalidNumbers = "";
		for (String number : split) {
			Integer intNum = parseNumber(number);
			if (intNum < 0)
				invalidNumbers += intNum + ",";
		}
		if (!invalidNumbers.isEmpty())
			throw new IllegalArgumentException("negatives not allowed "
					+ invalidNumbers.substring(0, invalidNumbers.length() - 1));
	}

}
