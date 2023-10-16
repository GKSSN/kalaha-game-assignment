import { Board} from "./board";
import { Player } from "./player";

export interface Game {
    id: string;
    board: Board;
    playerList: [Player];
    completed: boolean;
    started: boolean;
    winner: string;
    currentIndex: number;
    lastIndex: number;
    currentPlayer: Player;
}
