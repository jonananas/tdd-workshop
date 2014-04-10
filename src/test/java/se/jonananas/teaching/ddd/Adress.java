package se.jonananas.teaching.ddd;

public class Adress {

	private String gatuAdress;

	public Adress(String gatuAdress) {
		this.gatuAdress = gatuAdress;
	}

	public String getGatuadress() {
		return gatuAdress;
	}

	@Override
	public int hashCode() {
		return 31 + ((gatuAdress == null) ? 0 : gatuAdress.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Adress))
			return false;
		Adress other = (Adress) obj;
		if (!gatuAdress.equals(other.gatuAdress))
			return false;
		return true;
	}
}
