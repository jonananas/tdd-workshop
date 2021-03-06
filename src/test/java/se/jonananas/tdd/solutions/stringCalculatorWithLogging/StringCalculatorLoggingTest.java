/**
 * Implementation of Roy Osheroves string calculator kata
 * @see http://osherove.com/tdd-kata-1/
 * @see http://docs.mockito.googlecode.com/hg/org/mockito/Mockito.html
 */
package se.jonananas.tdd.solutions.stringCalculatorWithLogging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


public class StringCalculatorLoggingTest {
	
	private StringCalculator stringCalculator;

	@Before
	public void setup() {
		stringCalculator = new StringCalculator();
		stringCalculator.logger = mock(Logger.class);
		when(stringCalculator.logger.isLoggable(Level.INFO)).thenReturn(true);
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
	public void shouldThrowOnLoggerException() throws Exception {
		doThrow(new RuntimeException()).when(stringCalculator.logger).info(anyString());

		stringCalculator.add("1,2");
	}

	@Test
	public void shouldNotCallInfoWhenLoglevelWARN() throws Exception {
		when(stringCalculator.logger.isLoggable(Level.INFO)).thenReturn(false);

		stringCalculator.add("1,2");

		verify(stringCalculator.logger, times(0)).info(anyString());
	}

	@Test
	public void shouldReturnZeroOnEmptyString() throws Exception {
		assertThatAdd("").isEqualTo(0);
	}

	@Test
	public void shouldAddSingleNumber() throws Exception {
		assertThatAdd("1").isEqualTo(1);
		assertThatAdd("2").isEqualTo(2);
	}

	@Test
	public void shouldAddTwoNumbers() throws Exception {
		assertThatAdd("1,2").isEqualTo(3);
	}

	@Test
	public void shouldAddFiveNumbers() throws Exception {
		assertThatAdd("1,1,1,2,2").isEqualTo(7);
	}

	@Test
	public void shouldAllowNewLines() throws Exception {
		assertThatAdd("1\n2,3").isEqualTo(6);
	}
	
	@Test
	public void shouldAllowChangedDelimiter() throws Exception {
		assertThatAdd("//;\n1;2").isEqualTo(3);
	}

	@Test
	public void shouldAllowChangedDelimiterAndNewline() throws Exception {
		assertThatAdd("//;\n1\n2").isEqualTo(3);
	}

	@Test
	public void shouldThrowOnNegative() throws Exception {
		assertThrownByAdd("-1").hasMessage("negatives not allowed -1");
		assertThrownByAdd("-2").hasMessage("negatives not allowed -2");
	}
	
	@Test
	public void shouldThrowAndReturnAllNegatives() throws Exception {
		assertThrownByAdd("-1,-2").hasMessage("negatives not allowed -1,-2");
	}

	private AbstractThrowableAssert<?, ? extends Throwable> assertThrownByAdd(String numbers) {
		return assertThatThrownBy(() -> stringCalculator.add(numbers));
	}

	private AbstractIntegerAssert<?> assertThatAdd(String numbers) {
		return assertThat(stringCalculator.add(numbers));
	}
}
