package com.nayek.reservation.system.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document
public class Reservation {

	private Long roomNumber;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate checkin;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate checkout;
	private Integer price;

	@Id
	private String id;

	public Reservation() {
		super();
	}

	public Reservation(Long roomNumber, LocalDate checkInDate, LocalDate checkOutDate, Integer price) {
		super();
		this.roomNumber = roomNumber;
		this.checkin = checkInDate;
		this.checkout = checkOutDate;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public Long getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Long roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
