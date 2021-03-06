package com.nayek.reservation.system.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nayek.reservation.system.model.Reservation;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class ReservationResourceTest {

	private static final String ROOM_V1_RESERVATION = "/room/v1/reservation/";

	@Autowired
	private ApplicationContext context;
	private WebTestClient webTestClient;
	private Reservation reservation;

	@BeforeAll
	void setUpBeforeClass() throws Exception {
		webTestClient = WebTestClient.bindToApplicationContext(this.context).build();
		reservation = new Reservation(123l, LocalDate.now(), LocalDate.now().plus(10, ChronoUnit.DAYS), 150);
	}

	@Test
	void testCreateReservation() {
		webTestClient.post().uri(ROOM_V1_RESERVATION).body(Mono.just(reservation), Reservation.class).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
				.expectBody(Reservation.class);
	}

	@Test
	void testGetAllReservations() {
		webTestClient.get().uri(ROOM_V1_RESERVATION).exchange().expectStatus().isOk().expectBodyList(Reservation.class);
	}

}
