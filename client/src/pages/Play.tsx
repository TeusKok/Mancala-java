import { useMancalaGame } from "../contexts/MancalaGameContext";
import { isGameState } from "../types";
import { MancalaPits } from "../components/MancalaPits";
import { playPit, startGame } from "../services/api";


export const Play = () => {
    const { gameState, setGameState } = useMancalaGame();
    const playerOne = gameState!.players[0];
    const playerTwo = gameState!.players[1];
    

     const onClick = async(index: number ) => {
        const result = await playPit(index);

        if (isGameState(result)) {
            setGameState(result);
        } else {
            console.log(`${result.statusCode} ${result.statusText}`);
        }

    }
    const onSubmit = async () => {
        const result = await startGame(playerOne.name, playerTwo.name);

        if (isGameState(result)) {
            setGameState(result);
        } else {
            console.log(`${result.statusCode} ${result.statusText}`);
        }
    }
    
    return (
        <div className="h-full ">
            {gameState!.gameStatus.endOfGame? (  
            <div className="flex justify-center">
                <div className ="flex justify-center items-center h-[10%]">
                The winner is: {gameState!.gameStatus.winner}    
                <button className = "m-4 rounded-full w-20 bg-orange-200" onClick = {onSubmit}>Revenge</button>
                </div>
            </div>
            ) 
            : 
            <div className="h-full ">
                
                <div className ="flex justify-center items-center h-[10%]">
                    The turn is for: {playerOne.hasTurn? playerOne.name : playerTwo.name}
                </div>
                <div className = "m-2 h-[88%]   flex items-center justify-center">
                    <div className = " grid shadow-[-7px_12px_10px_5px_rgba(20,20,0,0.4)] rounded-lg grid-rows-1 grid-cols-1 w-11/12 h-fit bg-[url(/natural-wooden-background.jpg)]">
                        <MancalaPits whenClicked={onClick} players = {gameState!.players}></MancalaPits>                
                    </div>
                </div>
            </div>
        
            }
        </div>
    )
};