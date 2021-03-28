import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Reservation } from './model/Reservation';
import { ReservationRequest } from './model/ReservationRequest';
import { Room } from './model/Room';
import { ReservationService } from './reservation.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'reservation-frontend';

  constructor(private reservationService: ReservationService) {}

  rooms: Room[];
  roomSearchForm: FormGroup;
  currentCheckInVal: string;
  currentCheckOutVal: string;
  currentPrice: number;
  currentRoomNumber: number;
  currentReservations: Reservation[];

  ngOnInit() {
    this.roomSearchForm = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl(''),
      roomNumber: new FormControl(''),
    });

    this.roomSearchForm.valueChanges.subscribe((form) => {
      this.currentCheckInVal = form.checkin;
      this.currentCheckOutVal = form.checkout;
      if (form.roomNumber) {
        let roomValues: string[] = form.roomNumber.split('|');
        this.currentRoomNumber = Number(roomValues[0]);
        this.currentPrice = Number(roomValues[1]);
      }
    });

    this.rooms = [
      new Room('127', '127', '150'),
      new Room('138', '138', '180'),
      new Room('254', '254', '200'),
    ];

    this.getCurrentReservation();
  }

  createReservation() {
    console.log(this.currentCheckInVal);
    console.log(this.currentCheckOutVal);
    this.reservationService
      .createReservation(
        new ReservationRequest(
          this.currentRoomNumber,
          this.currentCheckInVal,
          this.currentCheckOutVal,
          this.currentPrice
        )
      )
      .subscribe((data) => {
        console.log(data);
        this.getCurrentReservation();
      });
  }

  getCurrentReservation() {
    this.reservationService.getReservations().subscribe((data) => {
      console.log(data);
      this.currentReservations = data;
    });
  }
}
