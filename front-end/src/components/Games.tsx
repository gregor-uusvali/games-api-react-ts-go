import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export interface GameType {
    id: number,
    title: string,
    release_date: string,
    developer: string,
    rating: number,
    description: string,
}

const Games = () => {
    const [games, setGames] = useState<GameType[]>([])

    useEffect(() => {
        let gamesList: GameType[] = [
            {
                id: 1,
                title: "Super Mario Bros",
                release_date: "13-09-1985",
                developer: "Nintendo",
                rating: 94.5,
                description: "Some long description",
            },
            {
                id: 2,
                title: "God of War",
                release_date: "22-03-2005",
                developer: "Santa Monica Studios",
                rating: 92.5,
                description: "Some long description",
            },
        ]

        setGames(gamesList)
    }, [])

    return (
        <div className="w-full mx-4">
            <div className="text-center">
                <h2 className="text-2xl font-bold">Games</h2>
                <hr className="my-4" />
                <div className="overflow-x-auto">
                    <table className="min-w-full table-auto border-collapse border border-gray-300">
                        <thead>
                            <tr className="bg-gray-200">
                                <th className="py-2 px-4">Game</th>
                                <th className="py-2 px-4">Release Date</th>
                                <th className="py-2 px-4">Rating</th>
                            </tr>
                        </thead>
                        <tbody>
                            {games.map((m) => (
                                <tr key={m.id} className="hover:bg-gray-100">
                                    <td className="py-2 px-4">
                                        <Link to={`/games/${m.id}`} className="text-blue-500">
                                            {m.title}
                                        </Link>
                                    </td>
                                    <td className="py-2 px-4">{m.release_date}</td>
                                    <td className="py-2 px-4">{m.rating}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Games;