package se.jonananas.tdd.katas;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Instructions: https://github.com/jonananas/tdd-workshop/blob/main/DotEnv_Mockito_Kata.md
 *
 * Pro tip:
 * - Add org.assertj.core.api.Assertions to Favorites
 * - Add org.mockito.Mockito to Favorites
 * - Add org.mockito.ArgumentMatchers to Favorites
 *
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
