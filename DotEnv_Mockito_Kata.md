# DotEnv Mockito Kata

We are going to create a utility that helps editing .env files using TDD. The tests will use Mockito to mock the FileIO utility class so that we do not create tests that read or write from disk.  
Feel free to take a look at [FileIOTest.java](src/test/java/se/jonananas/FileIOTest.java) to understand what the utility class does.

See [Mockito](http://static.javadoc.io/org.mockito/mockito-core/4.4.0/org/mockito/Mockito.html#1) for help on Mockito.

## The kata

1. DotEnvTest should inject `FileIO` mock, e.g. `assertThat(fileIO).isNotNull()`

    - Use ```@ExtendWith(MockitoExtension.class)``` and ```@Mock FileIO fileIO``` to initialize class field fileIO

2. `DotEnv` class should be created with ```FileIO``` as parameter, ie ```public DotEnv(FileIO fileIO)```

3. `DotEnv::read(Path path)` should return a `Map<String, String>`, e.g. `assertThat(dotEnv.read(path)).isNotNull()`

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
    - Inject an ArgumentCaptor field using `@Captor ArgumentCaptor<List<String>> captor`
    - Use `verify(fileIO).writeLines(captor.capture(), any())` to capture the parameter call.

10. Make sure an `IOException` is propagated when `Path` cannot be written to.

You are done! You can optionally write and run an integration test to prove the file is read and written correctly!
