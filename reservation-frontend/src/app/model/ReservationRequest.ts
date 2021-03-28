export class ReservationRequest {
    roomNumber: number;
    checkin: string;
    checkout: string;
    price: number;
  
    constructor(
      roomNumber: number,
      checkin: string,
      checkout: string,
      price: number
    ) {
      this.checkin = checkin;
      this.checkout = checkout;
      this.roomNumber = roomNumber;
      this.price = price;
    }
  }