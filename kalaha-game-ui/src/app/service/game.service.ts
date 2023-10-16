import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {throwError} from 'rxjs';
import { Game } from '../models/game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private httpClient: HttpClient) { }
  game;
  baseUrl = 'http://localhost:8080/api/game';

  public startGame(gameObj){
    return this.httpClient.post(this.baseUrl + '/create', gameObj, {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    }).pipe(
      map((data: Game) => {
        return data;
      }), catchError( error => {
        return throwError( 'Something went wrong!' );
      })
    );

  }

  public updateGame(gameObj){
    return this.httpClient.post(this.baseUrl + '/play', gameObj, {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    }).pipe(
      map((data: Game) => {
        return data;
      }), catchError( error => {
        return throwError( 'Something went wrong!' );
      })
    );
  }
}
