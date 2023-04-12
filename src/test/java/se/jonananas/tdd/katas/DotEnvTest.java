package se.jonananas.tdd.katas;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import se.jonananas.FileIO;

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



class DotEnv {
	private FileIO fileIO;

	protected DotEnv(FileIO fileIO) {
		this.fileIO = fileIO;
	}

	public DotEnv() {
		this.fileIO = new FileIO();
	}

	public Map<String, String> read(Path path) throws IOException {
		List<String> readLines = fileIO.readLines(path);
		return readLines.stream().collect(Collectors.toMap(line -> key(line), line -> value(line)));
	}

	private String key(String line) {
		return line.split("=")[0];
	}
	
	private String value(String line) {
		return line.split("=")[1];
	}

	public void write(Path path, Map<String, String> env) throws IOException {
		Function<String, String> toString = x -> x + "=" + env.get(x);
		List<String> lines = env.keySet().stream().map(toString).collect(toList());
		fileIO.writeLines(lines, path);
	}
}

@ExtendWith(MockitoExtension.class)
public class DotEnvTest {
	
	DotEnv dotEnv;
	Path path = Path.of(".env");

	@Mock
	FileIO fileIO;
	
	@Captor
	ArgumentCaptor<List<String>> lines;
	
	@BeforeEach
	public void init() {
		dotEnv = new DotEnv(fileIO);
	}
	
	@Test
	public void testThatThrowsOnBadPath() throws Exception {
		Path path = Path.of("nope");
		when(fileIO.readLines(path)).thenThrow(new IOException());

		assertThatThrownBy(() -> dotEnv.read(path)).isInstanceOf(IOException.class);
	}
	
	@Test
	public void testThatReadReturnsMap() throws Exception {
		when(fileIO.readLines(path)).thenReturn(Arrays.asList("KEY=VALUE"));
		
		Map<String, String> env = dotEnv.read(path);
		assertThat(env.get("KEY")).isEqualTo("VALUE");
	}
	
	@Test
	public void testThatReadReturnsMapWithTwoKeys() throws Exception {
		when(fileIO.readLines(path)).thenReturn(Arrays.asList("KEY=VALUE", "KEY2=VALUE2"));
		
		Map<String, String> env = dotEnv.read(path);
		assertThat(env.keySet()).contains("KEY", "KEY2");
	}
	
	@Test
	public void testThatWriteCallsWriteLines() throws Exception {
		Map<String, String> env = Map.of("KEY", "VALUE", "KEY2", "VALUE2");
		
		dotEnv.write(path, env);

		verify(fileIO).writeLines(lines.capture(), eq(path));
		assertThat(lines.getValue()).contains("KEY=VALUE", "KEY2=VALUE2");
	}
	
	@Test
	public void integrationTest() throws Exception {
		Map<String, String> env = Map.of("KEY", "VALUE", "KEY2", "VALUE2");
		new DotEnv().write(path, env);

		Map<String, String> readEnv = new DotEnv().read(path);
		assertThat(readEnv).isEqualTo(env);

		Files.delete(path);
	}
}
