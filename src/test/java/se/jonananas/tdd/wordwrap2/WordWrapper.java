package se.jonananas.tdd.wordwrap2;

class WordWrapper {

	public String wrap(String text, int preferredLength) {
		int wrapPoint = findWrapPoint(text, preferredLength);
		if (wrapPoint <= 0)
			return text;
		return beforeWrapPoint(text, wrapPoint) + "\n" + wrap(text.substring(wrapPoint+1), preferredLength);
	}

	private int findWrapPoint(String text, int preferredLength) {
		return text.indexOf(" ", preferredLength -1);
	}

	private String beforeWrapPoint(String text, int wrapPoint) {
		return text.substring(0, wrapPoint);
	}
	
}