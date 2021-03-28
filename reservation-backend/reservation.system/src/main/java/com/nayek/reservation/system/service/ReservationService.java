package com.nayek.reservation.system.service;

import com.nayek.reservation.system.model.Reservation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {

	Mono<Reservation> getReservation(String id);

	Mono<Reservation> createReservation(Mono<Reservation> reservation);

	Mono<Reservation> updateReservation(String idString, Mono<Reservation> reservation);

	Mono<Boolean> deleteReservation(String id);

	Flux<Reservation> listAllReservations();
}
