package se.jonananas.teaching.ddd;

public interface ProduktRepository {

	void persist(Produkt produkt);

	Produkt findByName(String name);

	void delete(Produkt gräsklippare);

}
