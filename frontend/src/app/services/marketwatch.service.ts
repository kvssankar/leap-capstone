import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Stock } from '../models/Stock';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class MarketwatchService {
  private url: string = 'http://13.233.161.221:3000/fmts/trades/prices';

  constructor(private http: HttpClient) {}

  getMarketWatchData(): Observable<Stock[]> {
    return this.http.get<Stock[]>(this.url);
  }
}
