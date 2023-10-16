import { Component, OnInit } from '@angular/core';
import { GameService } from '../service/game.service';
import { Game } from '../models/game';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {throwError} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-playgame',
  templateUrl: './playgame.component.html',
  styleUrls: ['./playgame.component.css']
})
export class PlaygameComponent implements OnInit {

  game: any;
  checkPlayerAValidMove: boolean;
  checkPlayerBValidMove: boolean;
  message: string;

  constructor(private gameService: GameService, private httpClient: HttpClient,
              private activatedRoute: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
    const d = this.activatedRoute.paramMap.pipe(map(() => window.history.state));
    d.subscribe(data => {
      this.game = data;
      if (this.game.currentPlayer.bigPitIndex === 13) {
          this.checkPlayerAValidMove = false;
          this.checkPlayerBValidMove = true;
      } else {
        this.checkPlayerAValidMove = true;
        this.checkPlayerBValidMove = false;
      }

      if (this.game.completed) {
        this.message = 'Congratulations ' + this.game.winner;
      } else {
        this.message = this.game.currentPlayer.name + ' turn';
      }
    });
  }

  submit(num: number) {
    this.game = this.httpClient.post('http://localhost:8080/api/game/play?currentIndex='+num, {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    }).pipe(
      map((data: Game) => {
        return data;
      }), catchError( error => {
        console.log(error);
        return throwError( 'Something went wrong!' );
      })
    ).subscribe((data) => {
    this.game = data;
    if (this.game.currentPlayer.bigPitIndex === 13) {
      this.checkPlayerAValidMove = false;
      this.checkPlayerBValidMove = true;
    } else {
      this.checkPlayerAValidMove = true;
      this.checkPlayerBValidMove = false;
    }
    if (this.game.completed) {
      this.message = 'Congratulations ' + this.game.winner;
    } else {
    this.message = this.game.currentPlayer.name + ' turn';
    }
  });

  }

  reset() {
    this.router.navigate(['/home']).then(nav => {
    }, err => {
    });
  }

}
