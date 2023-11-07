
import classNames from "classnames";
import { Player } from "../types";


type Props = {
    whenClicked: Function,
    players: Player[]
}
export const MancalaPits = (props: Props) => {
    const {whenClicked, players} = props;
    const playerOnePits = players[0].pits;
    let playerTwoPits = players[1].pits;
    const kalahaPlayerOne = playerOnePits[6];
    const kalahaPlayerTwo = playerTwoPits[6];
    
    const pitStyle = "rounded-full  text-2xl bg-orange-200 shadow-[inset_-7px_12px_5px_0px_rgba(20,20,0,0.4)] "
    

    return (
        <div className = "m-3 grid grid-rows-4 grid-cols-8 gap-4 h-96 content-stretch ">
            
            
                <button key={kalahaPlayerTwo.index} onClick={() => props.whenClicked(kalahaPlayerTwo.index)} className ={classNames("row-span-4 shadow  "+pitStyle)}>
                    {kalahaPlayerTwo.nrOfStones}
                </button>
            
            {playerTwoPits.slice(0,-1).map((pit) => (
                    
                   
                        <button key={pit.index} onClick={() => props.whenClicked(pit.index)} className ={classNames("row-span-2 "+pitStyle)}>
                            {pit.nrOfStones}
                        </button>
                  
                
            )).reverse()
            }
            
            
                <button key={kalahaPlayerOne.index} onClick={() => props.whenClicked(kalahaPlayerOne.index)} className ={classNames("row-span-4 "+pitStyle)}>
                    {kalahaPlayerOne.nrOfStones}
                </button>
            
            
            {playerOnePits.slice(0,-1).map((pit) => (
                
               
                    <button key={pit.index} onClick={() => whenClicked(pit.index)} className ={classNames("row-span-2 "+pitStyle)}>
                        {pit.nrOfStones}
                    </button>
                
            
        ))
        }   
        </div>
    )
}
