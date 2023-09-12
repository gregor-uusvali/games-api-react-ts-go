import { Link } from 'react-router-dom';
import PlantImg from '../images/plant1.jpg'

const Home = () => {
    return (
        <div className="w-full mx-4">
            <div className="text-center">
                <h2 className="text-2xl font-bold">Find a plant to water!</h2>
                <hr className="mt-4" />
                <Link to="/plants">
                    <img className="mx-auto rotate-180" src={PlantImg} alt="games image" />
                </Link>
            </div>
        </div>
    );
};

export default Home;