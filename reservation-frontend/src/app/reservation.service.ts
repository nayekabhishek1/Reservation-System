import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ReservationRequest } from './model/ReservationRequest';
import { Observable } from 'rxjs';
import { Reservation } from './model/Reservation';
@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  constructor(private http: HttpClient) {}

  private baseURL: string = 'http://localhost:8080';
  private reservationURL: string = `${this.baseURL}/room/v1/reservation/`;

  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.reservationURL);
  }

  createReservation(body: ReservationRequest): Observable<Reservation> {
    let httpOptions = {
      headers: new HttpHeaders({ 'content-type': 'application/json' }),
    };
    return this.http.post<Reservation>(this.reservationURL, body, httpOptions);
  }
}
