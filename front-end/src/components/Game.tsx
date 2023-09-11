import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { GameType } from "./Games";

const Game = () => {
    const [game, setGame] = useState<GameType| null>(null)
    let { id } = useParams()

    useEffect(() => {
        let game = {
            id: 1,
            title: "Super Mario Bros",
            release_date: "13-09-1985",
            developer: "Nintendo",
            rating: 94.5,
            description: "Some long description.",
        }
        setGame(game)
    }, [id])

    return (
      <div className="w-full mx-4">
        <div className="">
          <h2 className="text-2xl font-bold">Game: {game !== null && game.title}</h2>
          <small><em>Released in {game?.release_date}, Developed by {game?.developer}</em></small>
          <hr className="my-4" />
          <p>{game?.description}</p>
        </div>
      </div>
    );
  };
  
  export default Game;