package se.jonananas.tdd.solutions.wordwrap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordWrapTest {
	
	private WordWrapper wordWrapper;

	@BeforeEach
	public void setup() {
		wordWrapper = new WordWrapper();
	}
	
	@Test
	public void shouldThrowOnNullString() throws Exception {

		assertThrows(NullPointerException.class, () -> wordWrapper.wrap(null, 0));
	}
	
	@Test
	public void shouldWrapALongSentence() throws Exception {
		assertWrappedTextEquals("detta är en lite längre mening", "detta är en\nlite längre\nmening", 10);
	}
	
	@Test
	public void shouldNotWrapEmptyString() throws Exception {
		assertWrappedTextEquals("", "", 0);
		assertWrappedTextEquals("", "", 10);
	}

	@Test
	public void shouldNotWrapOneWord() throws Exception {
		assertWrappedTextEquals("one", "one", 0);
	}

	@Test
	public void shouldWrapTwoWordsAboveLongerThanPreferred() throws Exception {
		assertWrappedTextEquals("two words", "two\nwords", 0);
	}

	@Test
	public void shouldWrapThreeWordsLongerThanPreferred() throws Exception {
		assertWrappedTextEquals("this is three", "this\nis\nthree", 0);
	}

	@Test
	public void shouldNotWrapTwoWordsAtPreferredLength() throws Exception {
		assertWrappedTextEquals("two words", "two words", 5);
	}

	@Test
	public void shouldNotWrapTwoWordsBelowThreshold() throws Exception {
		assertWrappedTextEquals("two words", "two\nwords", 4);
	}

	private void assertWrappedTextEquals(final String text, final String expected, final int preferredLength) {
		String wrapped = wordWrapper.wrap(text, preferredLength);
		assertEquals(expected, wrapped);
	}

}
