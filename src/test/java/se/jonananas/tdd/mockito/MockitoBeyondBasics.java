package se.jonananas.tdd.mockito;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoBeyondBasics {
	
	@Mock
	Logger log;
	
	
	@Test
	public void usingCaptor() throws Exception {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		log.info("a very complicated message");
		
		verify(log).info(captor.capture());
		assertThat(captor.getValue()).matches(".+ message");
	}
	
	
	@Captor
	ArgumentCaptor<String> captor;
	
	@Test
	public void usingInjectedCaptor() throws Exception {
		log.info("a very complicated message");
		
		verify(log).info(captor.capture());
		assertThat(captor.getValue()).matches(".+ message");
	}
}
