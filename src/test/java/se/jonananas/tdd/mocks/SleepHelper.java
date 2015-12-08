package se.jonananas.tdd.mocks;

public class SleepHelper {

	private long numSleeps = 0;
	private long cycleTime;
	private long maxSleepTime;

	public SleepHelper(long maxSleepTime, long cycleTime) {
		this.maxSleepTime = maxSleepTime;
		this.cycleTime = cycleTime;
	}

	public SleepHelper(long maxSleepTime) {
		this(maxSleepTime, 10);
	}

	public void sleepOneCycle() throws InterruptedException {
		sleep(cycleTime * 1000);
		numSleeps++;
	}

	void sleep(long millis) throws InterruptedException {
		Thread.sleep(millis);
	}

	public boolean hasSleeptimeLeft() {
		return numSleeps * cycleTime < maxSleepTime;
	}
}