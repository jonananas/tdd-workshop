/**
 * Implementation of Roy Osheroves string calculator kata
 * @see http://osherove.com/tdd-kata-1/ 
 */
package se.jonananas.tdd.solutions.stringcalculator_lists;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

	private StringCalculator stringCalculator;

	@BeforeEach
	public void setup() {

		stringCalculator = new StringCalculator();
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
