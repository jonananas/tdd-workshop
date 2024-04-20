package se.jonananas.tdd.mocks.testdoubles;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import se.jonananas.FileIO;


class DotEnv {

	FileIO fileIO;

	public DotEnv(FileIO fileIO) {
		this.fileIO = fileIO;
	}

	public Map<String, String> read() throws IOException {
		Function<String, String> key = str -> str.split("=")[0];
		Function<String, String> value = str -> str.split("=")[1];
		return fileIO
				.readLines(null)
				.stream()
				.collect(toMap(key, value));
	}

	public void write(Map<String, String> map) throws IOException {
		List<String> lines = map.keySet()
				.stream()
				.map(key -> key + "=" + map.get(key))
				.collect(toList());
		fileIO.writeLines(lines, null);
	}
}



class FileIODummy extends FileIO {
	@Override
	public List<String> readLines(Path path) throws IOException {
		return null;
	}

	@Override
	public void writeLines(java.util.List<String> lines, Path path) throws IOException {
	};
}



class FileIOStub extends FileIO {
	@Override
	public List<String> readLines(Path path) throws IOException {
		return Arrays.asList("key=value");
	}

	@Override
	public void writeLines(java.util.List<String> lines, Path path) throws IOException {
	};
}




class FileIOSpy extends FileIO {
	public boolean writeLinesCalled;

	@Override
	public List<String> readLines(Path path) throws IOException {
		return null;
	}

	@Override
	public void writeLines(java.util.List<String> lines, Path path) throws IOException {
		writeLinesCalled = true;
	};
}



class FileIOFake extends FileIO {
	public List<String> fileContents;

	@Override
	public List<String> readLines(Path path) throws IOException {
		return fileContents;
	}

	@Override
	public void writeLines(java.util.List<String> lines, Path path) throws IOException {
		this.fileContents = lines;
	};
}


public class TestDoubles {

	@Test
	public void shouldIgnoreWrites() throws Exception {
		DotEnv dotEnv = new DotEnv(new FileIODummy());
		dotEnv.write(Map.of());
	}

	@Test
	public void shouldRead() throws Exception {
		DotEnv dotEnv = new DotEnv(new FileIOStub());

		assertThat(dotEnv.read()).isNotNull();
	}

	@Test
	public void shouldWrite() throws Exception {
		FileIOSpy fileIO = new FileIOSpy();
		DotEnv dotEnv = new DotEnv(fileIO);

		dotEnv.write(Map.of());

		assertThat(fileIO.writeLinesCalled).isTrue();
	}

	@Test
	public void shouldReturnWrittenLines() throws Exception {
		DotEnv dotEnv = new DotEnv(new FileIOFake());

		dotEnv.write(Map.of("KEY", "VALUE"));

		assertThat(dotEnv.read().get("KEY")).isEqualTo("VALUE");
	}

}
