package se.jonananas.tdd.solutions.wordwrap;

class WordWrapper {

	public String wrap(String text, int preferredLength) {
		int wrapPoint = findWrapPoint(text, preferredLength);
		if (wrapPoint <= 0)
			return text;
		String head = text.substring(0,wrapPoint);
		String tail = text.substring(wrapPoint + 1);
		return head + "\n" + wrap(tail, preferredLength);
	}

	private int findWrapPoint(String text, int preferredLength) {
		return text.indexOf(" ", preferredLength-1);
	}
}