package se.jonananas.tdd.mockito;

public interface BetalServiceClient {
	
	public void pay(String order);

	public int getNumberOfOrdersFor(String anyString);

}
