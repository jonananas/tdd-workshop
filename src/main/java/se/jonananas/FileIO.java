package se.jonananas;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileIO {
	public void writeLines(List<String> lines, Path path) throws IOException {
		Files.write(path, lines, Charset.forName("UTF8"));
	}
	
	public List<String> readLines(Path path) throws IOException {
		return lines(path).collect(toList());
	}
}
