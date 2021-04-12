package se.jonananas.tdd.mocks.DotEnv;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

/**
* DotEnv is a Map<String, String> 
* DotEnvFiles has a DotEnv read(Path path) method
* If path does not exist: Throws a java.nio.file.NoSuchFileException
* For file "KEY1=VALUE1", dotenv.get("KEY1") should return VALUE1
* For file "KEY1=VALUE1\nKEY2=VALUE2", dotenv.get("KEY2") should return VALUE2
* doeten
 */

 class DotEnv extends HashMap<String, String> {
     

 }

public class FileIOTest {

    List<String> lines = Arrays.asList("First line", "Second line");
    
    @Test
    public void shouldReadLines() throws Exception {
        Path path = Paths.get(".env");
        Files.write(path, lines, Charset.forName("UTF8"));
        List<String> result = Files.lines(path).collect(Collectors.toList());
        assertThat(result).isEqualTo(lines);
        Files.delete(path);
    }
}
