
package it.akademija.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.akademija.App;

@SpringBootTest(classes = { App.class,
		UserController.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private UserController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
