package se.jonananas;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


public class FileIOTest {
	List<String> lines = asList("First line", "Second line");
	Path path = Paths.get(".env");
	FileIO fileIO = new FileIO();
	
	@AfterEach
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
