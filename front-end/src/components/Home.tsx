import { Link } from 'react-router-dom';
import GamesImg from '../images/games.jpg'

const Home = () => {
    return (
        <div className="w-full mx-4">
            <div className="text-center">
                <h2 className="text-2xl font-bold">Find a game to play tonight!</h2>
                <hr className="my-4" />
                <Link to="/games">
                    <img className="mx-auto" src={GamesImg} alt="games image" />
                </Link>
            </div>
        </div>
    );
};

export default Home;