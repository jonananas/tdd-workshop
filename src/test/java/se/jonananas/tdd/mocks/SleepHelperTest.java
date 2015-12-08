package se.jonananas.tdd.mocks;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SleepHelperTest {

	public static class SleepHelperSpy extends SleepHelper {

		public long lastSleepInMillisecs = 0;
		public long totalSleepInMillisecs = 0;

		public SleepHelperSpy(long maxSleepTime, long cycleTime) {
			super(maxSleepTime, cycleTime);
		}

		public SleepHelperSpy(long maxSleepTime) {
			super(maxSleepTime);
		}

		@Override
		void sleep(long millis) {
			lastSleepInMillisecs = millis;
			totalSleepInMillisecs += millis;
		}
	}

	@Test
	public void shouldSleepOneCycle() throws Exception {
		SleepHelperSpy sleep = new SleepHelperSpy(10, 2);

		sleep.sleepOneCycle();
		assertThat(sleep.lastSleepInMillisecs).isEqualTo(2000L);
	}

	@Test
	public void shouldSleepAtMost10Seconds() throws Exception {
		SleepHelperSpy sleep = new SleepHelperSpy(10, 1);

		while (sleep.hasSleeptimeLeft()) {
			sleep.sleepOneCycle();
		}

		assertThat(sleep.totalSleepInMillisecs).isEqualTo(10000L);
	}

	@Test
	public void shouldSleepAtMost30Seconds() throws Exception {
		SleepHelperSpy sleep = new SleepHelperSpy(30, 1);

		while (sleep.hasSleeptimeLeft()) {
			sleep.sleepOneCycle();
		}

		assertThat(sleep.totalSleepInMillisecs).isEqualTo(30000L);
	}

	@Test
	public void shouldDefaultTo10SecondsCycletime() throws Exception {
		SleepHelperSpy sleep = new SleepHelperSpy(300);

		sleep.sleepOneCycle();
		assertThat(sleep.lastSleepInMillisecs).isEqualTo(10000L);
	}

}
