package se.jonananas.tdd.mocks.DotEnv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;

/**
 * Pro tip:
 * - Add org.assertj.core.api.Assertions to Favorites
 * - Add org.mockito.Mockito to Favorites
 * - Add org.mockito.ArgumentMatchers to Favorites
 *
 */

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


class DotEnvTest {

	FileIO fileIO;

	// mock here
	@Test
	public void shouldInjectFileIO() {

		assertThat(fileIO).isNotNull();
	}
}
