package hu.oktatas.transport.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.oktatas.transport.config.TransportPlanConfigProperties;
import hu.oktatas.transport.dt.LoginDto;
import hu.oktatas.transport.model.UserType;
import hu.oktatas.transport.repository.MilestoneRepository;
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
	MilestoneRepository milestoneRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	TransportPlanConfigProperties config;

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
		webTestClient.post()
				.uri(UriBuilder -> UriBuilder.path(path).queryParam("milestoneId", "40").queryParam("delayInMin", "10")
						.build())
				.headers(headers -> headers.setBearerAuth(jwt)).exchange().expectStatus().isBadRequest();
	}

	@Test
	void testMilestoneModifyFromMinusMoneyFor130Min() throws Exception {
		int plannedIncomeBefore = transportPlanRepository.findById(1L).get().getPlannedIncome();
		LocalDateTime TimeFromBefore = milestoneRepository.findById(1L).get().getPlannedTime();
		LocalDateTime TimeToBefore = milestoneRepository.findById(21L).get().getPlannedTime();
		String path = BASE_URI + "/1/delay";
		webTestClient
				.post().uri(UriBuilder -> UriBuilder.path(path).queryParam("milestoneId", "1")
						.queryParam("delayInMin", "130").build())
				.headers(headers -> headers.setBearerAuth(jwt)).exchange().expectStatus().isOk();

		int plannedIncomeAfter = transportPlanRepository.findById(1L).get().getPlannedIncome();
		LocalDateTime TimeFromAfter = milestoneRepository.findById(1L).get().getPlannedTime();
		LocalDateTime TimeToAfter = milestoneRepository.findById(21L).get().getPlannedTime();

		assertThat(plannedIncomeAfter).isEqualTo(
				(long) (plannedIncomeBefore * ((100.0 - config.getPercentMins().getPenalty120Min()) / 100.0)));
		assertThat(TimeFromAfter).isEqualTo(TimeFromBefore.plusMinutes(130));
		assertThat(TimeToAfter).isEqualTo(TimeToBefore.plusMinutes(130));
	}

	@Test
	void testMilestoneModifyToMinusMoneyFor40Min() throws Exception {
		int plannedIncomeBefore = transportPlanRepository.findById(2L).get().getPlannedIncome();
		LocalDateTime TimeToBefore = milestoneRepository.findById(26L).get().getPlannedTime();
		LocalDateTime TimeFromBefore = milestoneRepository.findById(7L).get().getPlannedTime();
		String path = BASE_URI + "/2/delay";
		webTestClient
				.post().uri(UriBuilder -> UriBuilder.path(path).queryParam("milestoneId", "26")
						.queryParam("delayInMin", "40").build())
				.headers(headers -> headers.setBearerAuth(jwt)).exchange().expectStatus().isOk();
		
		int plannedIncomeAfter = transportPlanRepository.findById(2L).get().getPlannedIncome();
		LocalDateTime TimeToAfter = milestoneRepository.findById(26L).get().getPlannedTime();
		LocalDateTime TimeFromAfter = milestoneRepository.findById(7L).get().getPlannedTime();

		assertThat(plannedIncomeAfter).isEqualTo(
				(long) (plannedIncomeBefore * ((100.0 - config.getPercentMins().getPenalty30Min()) / 100.0)));
		assertThat(TimeToAfter).isEqualTo(TimeToBefore.plusMinutes(40));
		assertThat(TimeFromAfter).isEqualTo(TimeFromBefore.plusMinutes(40));
	}

}
