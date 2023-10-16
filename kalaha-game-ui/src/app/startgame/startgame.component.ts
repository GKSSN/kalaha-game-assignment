import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { GameService } from '../service/game.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-startgame',
  templateUrl: './startgame.component.html',
  styleUrls: ['./startgame.component.css']
})
export class StartgameComponent implements OnInit {
  message;
  playerAName = new FormControl('');
  playerBName = new FormControl('');

  constructor(private gameDataService: GameService,private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    if(this.playerAName.value !== this.playerBName.value){
      const field = {playerAName: this.playerAName.value, playerBName: this.playerBName.value};
      this.gameDataService.startGame(field).subscribe((data) => {
        // @ts-ignore
        this.router.navigate(['/game'], {state: data}).then(nav => {
        }, err => {
        });
      });
    }else{
      this.message="Players name should not be same";
    }
    

  }

}
