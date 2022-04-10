package se.jonananas.tdd.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class MockitoBasics {
	
	@Test
	public void basicFlow() throws Exception {
		// Build
		Collaborator collaborator = mock(Collaborator.class);
		when(collaborator.query()).thenReturn(3);
		
		// operate
		int length = collaborator.query();
		
		// check
		assertThat(length).isEqualTo(3);
		verify(collaborator).query();
	}

	public static class Collaborator {
		public int query() {
			throw new RuntimeException("Not implemented very complicated query!");
		}
	}
	
}
