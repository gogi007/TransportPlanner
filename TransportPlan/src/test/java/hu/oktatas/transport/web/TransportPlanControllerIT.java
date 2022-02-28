package hu.oktatas.transport.web;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.oktatas.transport.dt.LoginDto;
import hu.oktatas.transport.model.UserType;
import hu.oktatas.transport.repository.TransportPlanRepository;
import hu.oktatas.transport.service.StaticUsers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class TransportPlanControllerIT {
	private static final String BASE_URI = "/api/transportPlans";

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	TransportPlanRepository transportPlanRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	private String userName = "testuser";
	private String password = "pass";
	private String jwt;

	@BeforeEach
	public void init() {
		UserType resultUser = new UserType(userName, passwordEncoder.encode(password), Set.of("TransportManager"));
		StaticUsers.users.add(resultUser);

		LoginDto loginDto = new LoginDto();
		loginDto.setUsername(userName);
		loginDto.setPassword(password);
		jwt = webTestClient.post().uri("/api/login").bodyValue(loginDto).exchange().expectBody(String.class)
				.returnResult().getResponseBody();
	}

	@Test
	void testTransportPlanOrMilestoneNotExists() throws Exception {
		String path = BASE_URI + "/200/delay";
		webTestClient
				.post().uri(UriBuilder -> UriBuilder.path(path).queryParam("milestoneId", "1")
						.queryParam("delayInMin", "10").build())
				.headers(headers -> headers.setBearerAuth(jwt)).exchange().expectStatus().isNotFound();
	}
	
	@Test
	void testTransportPlanOrMilestoneNotInSameSection() throws Exception {
		String path = BASE_URI + "/1/delay";
		webTestClient
				.post().uri(UriBuilder -> UriBuilder.path(path).queryParam("milestoneId", "40")
						.queryParam("delayInMin", "10").build())
				.headers(headers -> headers.setBearerAuth(jwt)).exchange().expectStatus().isBadRequest();
	}
	
	@Test
	void testMilestoneModifyFromMinusMoneyFor130Min() throws Exception {
		String path = BASE_URI + "/1/delay";
		webTestClient
				.post().uri(UriBuilder -> UriBuilder.path(path).queryParam("milestoneId", "40")
						.queryParam("delayInMin", "10").build())
				.headers(headers -> headers.setBearerAuth(jwt)).exchange().expectStatus().isBadRequest();
	}
	
	@Test
	void testMilestoneModifyToMinusMoneyFor40Min() throws Exception {
		String path = BASE_URI + "/1/delay";
		webTestClient
				.post().uri(UriBuilder -> UriBuilder.path(path).queryParam("milestoneId", "40")
						.queryParam("delayInMin", "10").build())
				.headers(headers -> headers.setBearerAuth(jwt)).exchange().expectStatus().isBadRequest();
	}

}
