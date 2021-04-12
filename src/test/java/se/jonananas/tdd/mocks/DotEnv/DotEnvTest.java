package se.jonananas.tdd.mocks.DotEnv;

import java.util.HashMap;


/**
* DotEnv is a Map<String, String> 
* DotEnvFiles has a DotEnv read(Path path) method
* If path does not exist: Throws a java.nio.file.NoSuchFileException
* For file "KEY1=VALUE1", dotenv.get("KEY1") should return VALUE1
* For file "KEY1=VALUE1\nKEY2=VALUE2", dotenv.get("KEY2") should return VALUE2
 */

class DotEnv extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;

}

class DotEnvFiles {
	
}

public class DotEnvTest {

}
