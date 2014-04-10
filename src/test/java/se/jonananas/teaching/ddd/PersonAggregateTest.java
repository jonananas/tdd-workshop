package se.jonananas.teaching.ddd;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PersonAggregateTest {

	private Person gräsklippare;
	private Adress adress;

	@Before
	public void setup() {
		adress = Adress.createFrom("Storgatan");
		gräsklippare = Person.createFromNameAndAdress("Pelle Person", adress);
	}

	@Test
	public void shouldCreatePerson() throws Exception {
		assertThat(gräsklippare.getName()).isEqualTo("Pelle Person");
		assertThat(gräsklippare.getAdress()).isEqualTo(adress);
	}

	@Test
	public void shouldBeEqualOnlyToItself() throws Exception {
		Person other = Person.createFromNameAndAdress("Pelle Person", Adress.createFrom("Storgatan"));
		
		Person same = gräsklippare;

		assertThat(same).isEqualTo(gräsklippare)
				.isNotEqualTo(other)
				.isNotEqualTo(null)
				.isNotEqualTo(new Object());
	}
	
	@Test
	public void shouldSetName() throws Exception {
		gräsklippare.setName("Palle Parsson");
		assertThat(gräsklippare.getName()).isEqualTo("Palle Parsson");
	}
}
