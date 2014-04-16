/**
 * Implementation of Roy Osheroves string calculator kata
 * @see http://osherove.com/tdd-kata-1/ 
 */
package se.agileexchange.tdd.stringCalculatorWithLogging;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


public class StringCalculatorLoggingTest {
	
	
	private StringCalculator stringCalculator;

	@Before
	public void setup() {
		stringCalculator = new StringCalculator();
		stringCalculator.logger = mock(Logger.class);
	}
	
	
	@Test
	public void shouldLogOutputOnEmptyString() throws Exception {
		stringCalculator.add("");
		
		verify(stringCalculator.logger).info("0");
	}
	
	@Test
	public void shouldLogOutputOnNumbers() throws Exception {
		stringCalculator.add("1,2");
		
		verify(stringCalculator.logger).info("3");
		verify(stringCalculator.logger, times(1)).info("3");
	}

	@Test
	public void shouldLogOutputOnNumbersUsingCaptor() throws Exception {
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		stringCalculator.add("1,2");
		
		verify(stringCalculator.logger).info(captor.capture());
		assertThat(captor.getValue()).isEqualTo("3");
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldLogOutputOnNumbers_() throws Exception {
		doThrow(new RuntimeException()).when(stringCalculator.logger).info(anyString());
		
		stringCalculator.add("1,2");
		
		verify(stringCalculator.logger, times(1)).info("3");
	}

	@Test
	public void shouldReturnZeroOnEmptyString() throws Exception {
		assertThatResultIs("", 0);
	}

	@Test
	public void shouldAddSingleNumber() throws Exception {
		assertThatResultIs("1", 1);
		assertThatResultIs("2", 2);
	}

	@Test
	public void shouldAddTwoNumbers() throws Exception {
		assertThatResultIs("1,2", 3);
	}

	@Test
	public void shouldAddFiveNumbers() throws Exception {
		assertThatResultIs("1,1,1,2,2", 7);
	}

	@Test
	public void shouldAllowNewLines() throws Exception {
		assertThatResultIs("1\n2,3", 6);
	}
	
	@Test
	public void shouldAllowChangedDelimiter() throws Exception {
		assertThatResultIs("//;\n1;2", 3);
	}

	@Test
	public void shouldAllowChangedDelimiterAndNewline() throws Exception {
		assertThatResultIs("//;\n1\n2", 3);
	}

	@Test
	public void shouldThrowOnNegative() throws Exception {
		assertThatThrowsWithMessage("-1", "negatives not allowed -1");
		assertThatThrowsWithMessage("-2", "negatives not allowed -2");
	}
	
	@Test
	public void shouldThrowAndReturnAllNegatives() throws Exception {
		assertThatThrowsWithMessage("-1,-2", "negatives not allowed -1,-2");
	}

	private void assertThatThrowsWithMessage(String numbers, final String expected) {
		String message = "";
		try {
			stringCalculator.add(numbers);
		} catch (IllegalArgumentException ex) {
			message = ex.getMessage();
		}
		assertThat(message).isEqualTo(expected);
	}

	private void assertThatResultIs(final String numbers, final int expected) {
		int result = stringCalculator.add(numbers);
		assertThat(result).isEqualTo(expected);
	}
	
}
