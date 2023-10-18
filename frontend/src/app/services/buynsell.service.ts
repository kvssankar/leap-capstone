import { Injectable } from '@angular/core';
import { Holding } from '../models/Holding';
import { Transaction } from '../models/Transaction';
import { Instrument } from '../models/Instrument';
import { Observable, catchError, of, throwError } from 'rxjs';
import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { User } from '../models/User';
import { AuthService } from './auth.service';
import { Stock } from '../models/Stock';

@Injectable({
  providedIn: 'root',
})
export class BuynsellService {
  constructor(private http: HttpClient, private authService: AuthService) {}

  holdings: Holding[] = [];

  transactions: Transaction[] = [];

  placeBuyOrder(
    instrument: Instrument,
    buySellPrice: number,
    quantity: number
  ): Observable<Boolean> {
    const stock: Stock = new Stock(Number(buySellPrice), 0, '', instrument);
    const d = {
      stock: stock,
      quantity: quantity,
      userId: 1,
    };
    console.log('placeBuyOrder', d);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    console.log('placeBuyOrder', headers);
    // this.http.get('http://13.233.161.221:5000/buynsell/health');
    // this.http
    //   .post<any>(
    //     'http://13.233.161.221:5000/buynsell/placeorder/buy',
    //     JSON.stringify(d),

    //     headers: new HttpHeaders({ 'Content-Type': 'application/json',
    //     'Access-Control-Allow-Origin': '*',
    //     'Access-Control-Allow-Credentials': 'true',
    //     'Access-Control-Allow-Headers': 'Content-Type',
    //     'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE', }
    //     ),
    //   )
    //   .pipe(catchError(this.handleError));
    return this.http
      .post<boolean>(
        'http://13.233.161.221:5000/buynsell/placeorder/buy',
        JSON.stringify(d),
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Credentials': 'true',
            'Access-Control-Allow-Headers': 'Content-Type',
            'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
          }),
        }
      )
      .pipe(catchError(this.handleError));
  }

  placeSellOrder(
    instrument: Instrument,
    buySellPrice: number,
    quantity: number
  ): Observable<Boolean> {
    const stock: Stock = new Stock(0, Number(buySellPrice), '', instrument);
    const d = {
      stock: stock,
      quantity: quantity,
      userId: 1,
    };
    console.log('placeBuyOrder', d);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    console.log('placeBuyOrder', headers);
    // this.http.get('http://13.233.161.221:5000/buynsell/health');
    // this.http
    //   .post<any>(
    //     'http://13.233.161.221:5000/buynsell/placeorder/buy',
    //     JSON.stringify(d),

    //     headers: new HttpHeaders({ 'Content-Type': 'application/json',
    //     'Access-Control-Allow-Origin': '*',
    //     'Access-Control-Allow-Credentials': 'true',
    //     'Access-Control-Allow-Headers': 'Content-Type',
    //     'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE', }
    //     ),
    //   )
    //   .pipe(catchError(this.handleError));
    return this.http
      .post<boolean>(
        'http://13.233.161.221:5000/buynsell/placeorder/sell',
        JSON.stringify(d),
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Credentials': 'true',
            'Access-Control-Allow-Headers': 'Content-Type',
            'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
          }),
        }
      )
      .pipe(catchError(this.handleError));
  }

  getInstrumentById(id: string): Observable<Instrument> {
    return this.http
      .get<Instrument>(
        `http://13.233.161.221:3000/fmts/trades/instrument/${id}`
      )
      .pipe(catchError(this.handleError));
  }

  getHoldings(): Observable<Holding[]> {
    const user: User = this.authService.getUser();
    return this.http
      .get<Holding[]>(`http://13.233.161.221:5000/portfolio/1`)
      .pipe(catchError(this.handleError));
  }

  getTransactions(): Observable<Transaction[]> {
    const user: User = this.authService.getUser();
    return this.http
      .get<Transaction[]>(`http://13.233.161.221:5000/transactions/1`)
      .pipe(catchError(this.handleError));
  }

  handleError(response: HttpErrorResponse) {
    console.log(response);

    return throwError(
      () => response.error || 'Something went wrong while logging in'
    );
  }
}
