package se.jonananas.tdd.mockito;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class Basics {
	
	@Test
	public void basicFlow() throws Exception {
		// Build
		String collaborator = mock(String.class);
		when(collaborator.length()).thenReturn(3);
		
		// operate
		int length = collaborator.length();
		
		// check
		assertThat(length).isEqualTo(3);
		verify(collaborator).length();
	}

}
