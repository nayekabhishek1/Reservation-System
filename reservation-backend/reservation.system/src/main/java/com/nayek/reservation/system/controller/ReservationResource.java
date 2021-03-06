package com.nayek.reservation.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nayek.reservation.system.model.Reservation;
import com.nayek.reservation.system.service.ReservationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ReservationResource.ROOM_V1_RESERVATION)
@CrossOrigin
public class ReservationResource {

	public static final String ROOM_V1_RESERVATION = "/room/v1/reservation/";
	private final ReservationService reservationService;

	@Autowired
	public ReservationResource(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	/**
	 * Get reservation status
	 * 
	 * @param roomId
	 * @return
	 */
	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Reservation> getReservationById(@PathVariable String id) {
		return reservationService.getReservation(id);
	}

	/**
	 * Add new reservation
	 * 
	 * @param reservation
	 * @return
	 */
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation) {
		return reservationService.createReservation(reservation);
	}

	/**
	 * Modify existing reservation
	 * 
	 * @param roomId
	 * @param reservation
	 * @return
	 */
	@PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Reservation> updatePrice(@PathVariable String id, @RequestBody Mono<Reservation> reservation) {
		return reservationService.updateReservation(id, reservation);
	}

	/**
	 * Delete a reservation
	 * 
	 * @param roomId
	 * @return
	 */
	@DeleteMapping(path = "{id}")
	public Mono<Boolean> deleteReservationMono(@PathVariable String id) {
		return reservationService.deleteReservation(id);
	}

	/**
	 * Get all reservations
	 * 
	 * @return
	 */
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Reservation> getAllReservations() {
		return reservationService.listAllReservations();
	}
}
