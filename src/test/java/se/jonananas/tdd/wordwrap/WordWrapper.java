package se.jonananas.tdd.wordwrap;

class WordWrapper {

	public String wrap(String text, int preferredLength) {
		int wrapPoint = findWrapPoint(text, preferredLength);
		if (wrapPoint <= 0)
			return text;
		return makeLine(text, wrapPoint) + wrap(text.substring(wrapPoint + 1), preferredLength);
	}

	private String makeLine(String text, int wrapPoint) {
		return text.substring(0,wrapPoint) + "\n";
	}

	private int findWrapPoint(String text, int preferredLength) {
		return text.indexOf(" ", preferredLength-1);
	}
}