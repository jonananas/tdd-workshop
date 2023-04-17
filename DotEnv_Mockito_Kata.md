# DotEnv Mockito Kata

The purpose of this kata is to practice how to TDD unit tests when we have a dependency that access some kind of system resource, i.e using the system resource would make the test slow and possibly system dependent.

We are going to TDD a utility that read/write .env files from/to a Map. A .env file is a text file containing key value pairs of environment variables required by an application at runtime, it can look like this:

```bash
VERSION=v1.5
FOOPATH=/path/to/foo
```

The tests will use Mockito to mock a FileIO utility class so that our unit tests does not read or write from disk. The purpose of FileIO is to simplify file access by reading and writing lines to a text file. It basically reads and writes text lines as UTF8:

```java
    fileIO.writeLines(java.util.Arrays.asList("First line", "Second line"), java.nio.file.Paths.get("/path/to/file"));
    List<String> result = fileIO.readLines(path);
```

See [FileIOTest.java](src/test/java/se/jonananas/FileIOTest.java) to understand what the FileIO class does.
See [Mockito](http://static.javadoc.io/org.mockito/mockito-core/4.4.0/org/mockito/Mockito.html#1) for help on Mockito.

When we are done with the kata, we should be able to run an integration test like below:

```java
    Map<String, String> env = Map.of("VERSION", "1.5", "FOOPATH", "/path/to/foo");
    new DotEnv().write(path, Paths.get(".env"));
    
    // At this point a .env file is created, so that we can read it:
    Map<String, String> readEnv = new DotEnv().read(Paths.get(".env"));
```

## The kata

1. DotEnvTest should inject `FileIO` mock, e.g. `assertThat(fileIO).isNotNull()`

    - Use ```@ExtendWith(MockitoExtension.class)``` and ```@Mock FileIO fileIO``` to initialize class field fileIO

2. `DotEnv` class should be created with ```FileIO``` as parameter, ie ```public DotEnv(FileIO fileIO)```

3. `DotEnv::read(java.nio.file.Path path)` should return a `java.util.Map<String, String>`, e.g. `assertThat(dotEnv.read(path)).isNotNull()`

4. The map returned by `DotEnv::read(Path path)` should contain key "KEY" mapping to value "VALUE" for file containing "KEY=VALUE"

    - e.g. `assertThat(env.get("KEY")).isEqualTo("VALUE")`
    - Use `org.mockito.Mockito.when()` to tell fileIO what to return, e.g. `when(fileIO.readLines(path)).thenReturn(lines)`

5. Assert that the lines ["KEY=VALUE", "KEY2=VALUE2"] returns a map with the keys KEY and KEY2, e.g. `assertThat(env.keySet()).contains("KEY", "KEY2")`

6. Make sure an `IOException` is returned when `Path` cannot be found, e.g. `assertThrows(IOException.class, () -> dotEnv.read(path))`
    - Use `when(...).thenThrow(...)` to tell fileIO to throw an exception

7. `DotEnv::write(Map<String, String>, Path path)` should write file, i.e. call `FileIO::writeLines()` with `KEY=VALUE`.
    - Use `Mockito.verify()` and `Mockito.any()`, e.g. `verify(fileIO).writeLines(asList("KEY=VALUE"), path)`

8. `DotEnv::write(Map<String, String>, Path path)` should write the correct file, i.e. call `fileio.writeLines` with `path`
    - Use `org.mockito.Mockito.eq()`, e.g. `verify(fileIO).writeLines(eq(asList("KEY=VALUE")), eq(path))`

9. Make sure the lines ["KEY=VALUE", "KEY2=VALUE2"] is passed to writeLines for a map `Map.of("KEY", "VALUE", "KEY2", "VALUE2")`
    - Inject an ArgumentCaptor field into the test class using `@org.mockito.Captor org.mockito.ArgumentCaptor<List<String>> captor`
    - Use `verify(fileIO).writeLines(captor.capture(), any())` to capture the parameter call.

10. Make sure an `IOException` is propagated when `Path` cannot be written to.
    - Use `doThrow(...).when(fileIO)...

You are done! But write and run the integration test above to prove the file is read and written correctly!
