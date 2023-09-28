import { Link, useNavigate } from 'react-router-dom';
import PlantImg from '../images/plant1.jpg'
import { useOutletContext } from '../context/OutletContext';
import { FormEvent, useEffect, useState } from 'react';



const Home = () => {
  const { lastWatered } = useOutletContext();
  const { daysToWater } = useOutletContext();
  const { nextDateToWater } = useOutletContext();
  const { daysLeftToWater } = useOutletContext();

  const calculateProcent = () => {
    let res = Math.round((daysLeftToWater / daysToWater) * 100)
    return res.toString()
  }
  const navigate = useNavigate()

  const navigateToPlants = () => {
    navigate("/plants")
  }
  function handleUpdateWatering(event: FormEvent<HTMLFormElement>): void {
    throw new Error('Function not implemented.');
  }

  return (
    <div className="w-full mx-4">
      <div className="text-center">
        <h2 className="text-2xl font-bold">Find a plant to water!</h2>
        <hr className="mt-4" />
        <div id="imageContainer">
          <img onClick={navigateToPlants} id="myImage" className="mx-auto rotate-180" src={PlantImg} alt="games image" />
        </div>
        <div>Currently time to next water is set to 10 days</div>
        <div>Last time watered {lastWatered.toDateString()}</div>
        <div>Next time to water {nextDateToWater.toDateString()}</div>
        <div>Days left to water {Math.round(daysLeftToWater * 10) /10}</div>
        <div className="mx-auto relative w-full sm:w-1/2 bg-gray-200 rounded">
          <div style={{width: `${calculateProcent()}%`}} className="absolute top-0 h-4 rounded shim-blue"></div>
        </div>
        <form onSubmit={handleUpdateWatering} className="mx-auto mt-4">
        <input
              className="hover:cursor-pointer bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              type="submit"
              value="Watered"
            />
        </form>
      </div>
    </div>
  );
};

export default Home;