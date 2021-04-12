package se.jonananas.tdd.mocks.DotEnv;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * 
 * We are going to create a utility that helps editing .env files, without writing or reading from disk.
 * We rely on the FileIO utility class for that, feel free to check @see FileIOTest.java to understand what it does.
 * The kata:
 *  
 * 1. Create class DotEnv with a constructor that takes FileIO as parameter, 
 * 		ie public DotEnv(FileIO fileIO)
 * 		Use @RunWith(MockitoJUnitRunner.class) and @Mock to initialize class parameter fileIO
 * 
 * 2. DotEnv read method should take a Path to the file and return a Map<String, String>, 
 * 		ie public Map<String, String> read(Path path)
 * 	a. Make sure an IOException is thrown if Path is not ".env"
 * 		Use Mockito.when() to tell fileIO to throw an exception
 *  b. When path is ".env", return "KEY=VALUE" from fileIO
 *  	Use Mockito.when() to tell fileIO what to return
 *  	assert that the map returned by DotEnv is correct, ie get("KEY") should be "VALUE"
 *  c. Assert that the lines ["KEY=VALUE", "KEY2=VALUE2"] returns a map with the keys KEY and KEY2.
 *  
 * 3. DotEnv write method should take a Path to the file and a Map<String, String>.
 * 	a. Make sure fileio.writeLines is called from DotEnv.write
 * 		Use Mockito.verify() and Mockito.any()
 *  b. Make sure path is passed to fileio.writeLines
 *  	Use  Mockito.eq()
 *  c. Make sure the lines ["KEY=VALUE", "KEY2=VALUE2"] is passed to writeLines for a map {"KEY": "VALUE", "KEY2": "VALUE2"}
 *  	Use @Captor on class member ArgumentCaptor<List<String>> lines
 *  
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

@RunWith(MockitoJUnitRunner.class)
public class DotEnvTest {
	
	DotEnv dotEnv;
	Path path = Path.of(".env");

	@Mock
	FileIO fileIO;
	
	@Captor
	ArgumentCaptor<List<String>> lines;
	
	@Before
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
		
		DotEnv dotEnv = new DotEnv();
		
		dotEnv.write(path, env);
	}
}
