package se.jonananas.tdd.katas;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Instructions: https://github.com/jonananas/tdd-workshop/blob/main/DotEnv_Mockito_Kata.md
 *
 * Pro tip:
 * - Add org.assertj.core.api.Assertions to Favorites
 * - Add org.mockito.ArgumentMatchers to Favorites
 * - Add org.mockito.Mockito to Favorites
 *
 * Alternatively these imports can be helpful:
 * import static org.assertj.core.api.Assertions.*;
 * import static org.mockito.ArgumentMatchers.*;
 * import static org.mockito.Mockito.*;
 * 
 * import org.junit.jupiter.api.extension.ExtendWith;
 * import org.mockito.junit.jupiter.MockitoExtension;
 * import org.mockito.ArgumentCaptor;
 * import org.mockito.Captor;
 * import org.mockito.Mock;
 */

import org.junit.jupiter.api.Test;

import se.jonananas.FileIO;


class DotEnvTest {

	FileIO fileIO;

	// mock here
	@Test
	public void shouldInjectFileIO() {

		assertThat(fileIO).isNotNull();
	}
}
