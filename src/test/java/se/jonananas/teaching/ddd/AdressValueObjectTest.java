package se.jonananas.teaching.ddd;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class AdressValueObjectTest {

	@Test
	public void shouldCreateAdress() throws Exception {
		Adress adress = new Adress("Storgatan 2");

		assertThat(adress.getGatuadress()).isEqualTo("Storgatan 2");
	}

	@Test
	public void shouldBeEqualByAttributes() throws Exception {
		Adress adress = new Adress("Storgatan 2");
		Adress same = new Adress("Storgatan 2");
		Adress other = new Adress("Storgatan 55");

		assertThat(adress).isEqualTo(same).isNotEqualTo(other).isNotEqualTo(new Object()).isNotEqualTo(null);
	}

}
