import { useState } from "react";
import { useMancalaGame } from "../contexts/MancalaGameContext";
import { startGame } from "../services/api";
import { isGameState } from "../types";
import { FloatingInput } from "../components/FloatingInput";
import classNames from "classnames";
import { useNavigate } from "react-router-dom";

export const Start = () => {
    const { setGameState } = useMancalaGame();
    const navigate = useNavigate();

    const [player1, setPlayer1] = useState("");
    const [player2, setPlayer2] = useState("");
    const [alert, setAlert] = useState<string | null>(null);
    const valid = player1 !== "" && player2 !== "" && player1 !== player2;

    const onSubmit = async () => {
        const result = await startGame(player1, player2);

        if (isGameState(result)) {
            setGameState(result);
            navigate("/play");
        } else {
            setAlert(`${result.statusCode} ${result.statusText}`);
        }
    }

    return (
        <div className="`relative h-full w-screen bg-cover bg-center bg-no-repeat p-12 text-center bg-mancala">
            <div className="absolute2 bottom-0 left-0 right-0 top-0 h-full w-full overflow-hidden bg-fixed bg-black/60">
                <div className="flex h-full items-center justify-center">
                    <div className="text-white">
                        <h2 className="mb-4 text-4xl font-semibold">Welcome to Mancala</h2>
                        <h4 className="mb-6 text-xl font-semibold">Enter the players name to start</h4>

                        {alert &&
                            <div className="flex justify-center items-center m-1 font-medium py-1 px-2 rounded-md text-red-700 bg-red-100 border border-red-300 ">
                                <div slot="avatar">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" className="feather feather-alert-octagon w-5 h-5 mx-2">
                                        <polygon points="7.86 2 16.14 2 22 7.86 22 16.14 16.14 22 7.86 22 2 16.14 2 7.86 7.86 2"></polygon>
                                        <line x1="12" y1="8" x2="12" y2="12"></line>
                                        <line x1="12" y1="16" x2="12.01" y2="16"></line>
                                    </svg>
                                </div>
                                <div className="text-xl font-normal  max-w-full flex-initial">{alert}</div>
                                <div className="flex flex-auto flex-row-reverse" onClick={() => setAlert(null)}>
                                    <div>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" className="feather feather-x cursor-pointer hover:text-red-400 rounded-full w-5 h-5 ml-2">
                                            <line x1="18" y1="6" x2="6" y2="18"></line>
                                            <line x1="6" y1="6" x2="18" y2="18"></line>
                                        </svg>
                                    </div>
                                </div>
                            </div>}
                        <form>
                            <ol>
                                <li className="mt-4">
                                    <FloatingInput
                                        id="player1"
                                        label="Name of player 1"
                                        value={player1}
                                        onChange={e => setPlayer1(e.target.value)}
                                        hasError={player1 !== "" && player2 !== "" && player1 === player2}
                                    />
                                </li>
                                <li className="mt-4">
                                    <FloatingInput
                                        id="player2"
                                        label="Name of player 2"
                                        value={player2}
                                        onChange={e => setPlayer2(e.target.value)}
                                        hasError={player1 !== "" && player2 !== "" && player1 === player2}
                                    />
                                </li>
                                <li>
                                    {player1 && player2 && player1 === player2 && <p className="mt-2 text-sm text-red-400">
                                        <span className="font-medium">Names must be unique</span>
                                    </p>}
                                </li>
                                <li className="mt-4">
                                    <button
                                        type="button"
                                        className={classNames(
                                            "rounded border-2",
                                            "border-neutral-50",
                                            "px-7",
                                            "pb-[8px]",
                                            "pt-[10px]",
                                            "text-sm",
                                            "font-medium",
                                            "uppercase",
                                            "leading-normal",
                                            "text-neutral-50",
                                            "transition duration-150",
                                            "ease-in-out",
                                            "hover:border-neutral-100",
                                            "hover:text-neutral-100",
                                            "focus:border-neutral-100",
                                            "focus:text-neutral-100",
                                            "focus:outline-none",
                                            "focus:ring-0",
                                            "active:border-neutral-200",
                                            "active:text-neutral-200",
                                            "hover:bg-neutral-100",
                                            "hover:bg-opacity-10",
                                            { "cursor-not-allowed opacity-30": !valid }
                                        )}
                                        data-te-ripple-init
                                        data-te-ripple-color="light"
                                        onClick={() => onSubmit()}
                                    >
                                        Play
                                    </button>
                                </li>
                            </ol>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};