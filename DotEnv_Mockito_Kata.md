# DotEnv Mockito Kata

We are going to create a utility that helps editing .env files, without writing or reading from disk.
We rely on the FileIO utility class for that, feel free to check @see FileIOTest.java to understand what it does.
See [Mockito](http://static.javadoc.io/org.mockito/mockito-core/2.18.3/org/mockito/Mockito.html#1) for help on Mockito.
The kata:

1. Create ```class DotEnv``` with a constructor that takes ```FileIO``` as parameter, 
		ie ```public DotEnv(FileIO fileIO)```
		Use ```@RunWith(MockitoJUnitRunner.class)``` and ```@Mock``` to initialize class member fileIO

2. DotEnv read method should take a ```Path``` to the file and return a ```Map<String, String>```, 
		ie ```public Map<String, String> read(Path path)```
	1. When path is ".env", return "KEY=VALUE" from fileIO
 		- Use ```Mockito.when()``` to tell fileIO what to return
 		- assert that the map returned by DotEnv is correct, ie get("KEY") should be "VALUE"
  	2. Assert that the lines ["KEY=VALUE", "KEY2=VALUE2"] returns a map with the keys KEY and KEY2.
	3. Make sure an ```IOException``` is propagated when ```Path``` cannot be found.
		- Use ```Mockito.when()``` to tell fileIO to throw an exception
 
3. DotEnv write method should take a Path to the file and a ```Map<String, String>```.
	1. Make sure ```fileio.writeLines``` is called from ```DotEnv.write```
		- Use ```Mockito.verify()``` and ```Mockito.any()```
	2. Make sure path is passed to ```fileio.writeLines```
 		- Use ```Mockito.eq()```
	3. Make sure the lines ["KEY=VALUE", "KEY2=VALUE2"] is passed to writeLines for a map {"KEY": "VALUE", "KEY2": "VALUE2"}
 		- Use the Mockito ```@Captor``` on class member ```ArgumentCaptor<List<String>>``` lines

You are done! You may optionally run an integration test to prove the file is read and written correctly
