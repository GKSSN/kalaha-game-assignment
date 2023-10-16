import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StartgameComponent } from './startgame/startgame.component';
import { PlaygameComponent } from './playgame/playgame.component';

const routes: Routes = [
  {path: 'home', component: StartgameComponent},
  {path: 'game', component: PlaygameComponent},
  {path: '', redirectTo:'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
