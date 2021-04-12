package se.jonananas.tdd.mocks.DotEnv;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;


public class FileIOTest {
	List<String> lines = Arrays.asList("First line", "Second line");
	Path path = Paths.get(".env");
	FileIO fileIO = new FileIO();
	
	@After
	public void cleanup() throws Exception {
		Files.delete(path);		
	}

	@Test
	public void shouldWriteAndReadLines() throws Exception {
		fileIO.writeLines(lines, path);
		List<String> result = fileIO.readLines(path);
		assertThat(result).isEqualTo(lines);
	}
}
