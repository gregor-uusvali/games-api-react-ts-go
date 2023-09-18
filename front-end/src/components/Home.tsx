import { Link, useNavigate } from 'react-router-dom';
import PlantImg from '../images/plant1.jpg'
import { useOutletContext } from '../context/OutletContext';



const Home = () => {
    const navigate = useNavigate()
    const navigateToPlants = () => {
        navigate("/plants")
    }
    const { currentUserId } = useOutletContext();
    // console.log(currentUserId)
    return (
        <div className="w-full mx-4">
            <div className="text-center">
                <h2 className="text-2xl font-bold">Find a plant to water!</h2>
                <hr className="mt-4" />
                    <div id="imageContainer">
                        <img onClick={navigateToPlants} id="myImage" className="mx-auto rotate-180" src={PlantImg} alt="games image" />
                    </div>
            </div>
        </div>
    );
};

export default Home;