package se.agileexchange.tdd.wordwrap3;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WordWrapperTest {

	private WordWrapper wordWrapper;

	class WordWrapper {

		public String wrap(String text, int preferredLength) {
			int wrapPoint = findWrapPoint(text, preferredLength);
			if (wrapPoint <= -1)
				return text;
			return makeLine(text, wrapPoint) + wrap(text.substring(wrapPoint+1), preferredLength);
		}

		private String makeLine(String text, int wrapPoint) {
			return text.substring(0, wrapPoint) + "\n";
		}

		private int findWrapPoint(String text, int preferredLength) {
			return text.indexOf(" ", preferredLength-1);
		}

	}
	
	@Before
	public void setup() {
		wordWrapper = new WordWrapper();
	}

	@Test
	public void shouldWrapSentence() throws Exception {
		final String expected = "detta är en\n" + //
				"lite längre\n" + //
				"mening";
		assertThatWrappedTextEquals("detta är en lite längre mening", 10,
			expected);
	}

	@Test
	public void shouldNotWrapEmptyString() throws Exception {
		assertThatWrappedTextEquals("", 10, "");
	}
	
	@Test
	public void shouldNotWrapOneWord() throws Exception {
		assertThatWrappedTextEquals("one", 0, "one");
	}

	@Test
	public void shouldNotWrapTwoWordsShorterThanPreferredLength() throws Exception {
		assertThatWrappedTextEquals("two words", 10, "two words");
	}
	
	@Test
	public void shouldWrapTwoWordsLongerThanPreferredLength() throws Exception {
		assertThatWrappedTextEquals("two words", 0, "two\nwords");
	}

	@Test
	public void shouldWrapThreeWordsLongerThanPreferredLength() throws Exception {
		assertThatWrappedTextEquals("one two three", 0, "one\ntwo\nthree");
	}
	
	@Test
	public void shouldNotWrapBeforePreferredLength() throws Exception {
		assertThatWrappedTextEquals("one two three", 4, "one\ntwo\nthree");
	}
	
	@Test
	public void shouldWrapAfterPreferredLength() throws Exception {
		assertThatWrappedTextEquals("one two three", 5, "one two\nthree");
	}
	
	private void assertThatWrappedTextEquals(String text, final int preferredLength, final String expected) {
		String wrapped = wordWrapper.wrap(text, preferredLength);
		assertEquals(expected, wrapped);
	}
	
}
