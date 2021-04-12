package se.jonananas.tdd.mocks.testdoubles;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import se.jonananas.tdd.mocks.DotEnv.FileIO;


class DotEnv {

	FileIO fileIO;
	
	public DotEnv(FileIO fileIO) {
		this.fileIO = fileIO;
	}

	public Map<String, String> read() throws IOException {
		return fileIO.readLines(null).stream().collect(Collectors.toMap(item -> item, item -> item));
	}

	public void write(Map<String, String> map) throws IOException {	
		fileIO.writeLines(null, null);
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
		return Arrays.asList("First line");
	}
	
	@Override
	public void writeLines(java.util.List<String> lines, Path path) throws IOException {
	};
}




class FileIOSpy extends FileIO {
	public boolean writeLinesCalled;
	
	@Override
	public List<String> readLines(Path path) throws IOException {
		return Arrays.asList("First line");
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
	};
}


public class TestDoubles {
	
	@Test
	public void shouldCreateDotEnv() throws Exception {
		new DotEnv(new FileIODummy());
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
		
		dotEnv.write(null);
		
		assertThat(fileIO.writeLinesCalled).isTrue();
	}

	@Test
	public void shouldReadKeyValue() throws Exception {
		FileIOFake fileIO = new FileIOFake();
		fileIO.fileContents = Arrays.asList("KEY=VALUE");
		
		DotEnv dotEnv = new DotEnv(fileIO);
		
		assertThat(dotEnv.read().get("KEY=VALUE")).isEqualTo("KEY=VALUE");
	}
	
}
