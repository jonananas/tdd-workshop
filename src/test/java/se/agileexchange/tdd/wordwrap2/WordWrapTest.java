package se.agileexchange.tdd.wordwrap2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WordWrapTest {
	
	private WordWrapper wordWrapper;

	@Before
	public void setup() {
		wordWrapper = new WordWrapper();
	}
	
	@Test
	public void shouldWrapSentenceIntoThreeLines() throws Exception {
		assertWrappedTextEquals("detta är en lite längre mening", 10, "detta är en\n"+
				"lite längre\n"+
				"mening");
	}

	private void assertWrappedTextEquals(final String text, final int preferredLength, final String expected) {
		String wrapped = wordWrapper.wrap(text, preferredLength);
		assertEquals(expected, wrapped);
	}
	
	@Test
	public void shouldNotWrapEmptyString() throws Exception {
		assertWrappedTextEquals("", 10, "");
	}

	@Test
	public void shouldNotWrapOneWord() throws Exception {
		assertWrappedTextEquals("one", 0, "one");
	}

	@Test
	public void shouldWrapTwoWordsWhenLongerThanPreferredLength() throws Exception {
		assertWrappedTextEquals("two words", 0, "two\nwords");
	}

	@Test
	public void shoulWrapTwoWordsAtPreferredLength() throws Exception {
		assertWrappedTextEquals("two words", 4, "two\nwords");
	}

	@Test
	public void shoulNotWrapTwoWordsAfterPreferredLength() throws Exception {
		assertWrappedTextEquals("two words", 5, "two words");
	}

	@Test
	public void shoulWrapThreeWords() throws Exception {
		assertWrappedTextEquals("one two three", 0, "one\ntwo\nthree");
	}

}
