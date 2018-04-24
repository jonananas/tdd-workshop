package se.jonananas.tdd.mockito;

public interface PaymentServiceClient {
	
	public void pay(String order);

	public int getNumberOfOrdersFor(String anyString);

}
