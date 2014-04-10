package se.jonananas.teaching.ddd;

import java.io.Serializable;
import java.util.UUID;


class Produkt implements Serializable {

	private String name;
	private UUID id;

	private Produkt(String name) {
		this.name = name;
		this.id = UUID.randomUUID();
	}

	public static Produkt createFromName(String name) {
		return new Produkt(name);
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Produkt))
			return false;
		Produkt other = (Produkt)obj;
		return this.id.equals(other.id);
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
