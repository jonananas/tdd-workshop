package se.jonananas.tdd.mocks.DotEnv;

/**
* DotEnvFiles has a DotEnv read(Path path) method that returns Map<String, String>  
* If path does not exist: Throws a java.nio.file.NoSuchFileException
* For file "KEY1=VALUE1", dotenv.get("KEY1") should return VALUE1
* For file "KEY1=VALUE1\nKEY2=VALUE2", dotenv.get("KEY2") should return VALUE2
 */

class DotEnvTest {


}
