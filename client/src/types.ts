export type GameState = {
    players: [Player, Player]; // a player array contains exactly two Players
    gameStatus: {
        endOfGame: boolean;
        winner: string;
    };
}

export type Player = {
    name: string;
    pits: Pit[];
    type: "player1" | "player2"; // only "player1" and "player2" are valid options for this string
    hasTurn: boolean;
}

export type Pit = {
    index: number;
    nrOfStones: number;
}

export function isGameState(gameState: unknown): gameState is GameState {
    return (gameState as GameState).players !== undefined;
}
