package se.jonananas.tdd.mocks.DotEnv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileIO {
	public void writeLines(List<String> lines, Path path) throws IOException {
		Files.write(path, lines, Charset.forName("UTF8"));
	}
	
	public List<String> readLines(Path path) throws IOException {
		return Files.lines(path).collect(Collectors.toList());
	}
}