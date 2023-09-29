import { Link, useNavigate } from 'react-router-dom';
import PlantImg from '../images/plant1.jpg'
import { useOutletContext } from '../context/OutletContext';
import { FormEvent, useEffect, useState } from 'react';
import Cookies from 'js-cookie';



const Home = () => {
  const [lastWatered, setLastWatered] = useState<Date>(new Date)
  const [daysToWater, setDaysToWater] = useState<number>(0)
  const [nextDateToWater, setNextDateToWater] = useState<Date>(new Date)
  const [daysLeftToWater, setDaysLeftToWater] = useState<number>(0)

  const { currentUserId } = useOutletContext();
  // const { lastWatered } = useOutletContext();
  // const { daysToWater } = useOutletContext();
  // const { nextDateToWater } = useOutletContext();
  // const { daysLeftToWater } = useOutletContext();
  const { isAuthenticated } = useOutletContext();


  const calculateProcent = () => {
    let res = Math.round((daysLeftToWater / daysToWater) * 100)
    return res.toString()
  }
  const navigate = useNavigate()

  const navigateToPlants = () => {
    navigate("/plants")
  }

  const convertDateStrtoDate = (lastWatered: string, daysToWater: number) => {
    setDaysToWater(daysToWater)
    const newLastWateredDate = new Date(lastWatered)
    setLastWatered(newLastWateredDate)
    const newNextWateredDate = new Date(lastWatered)
    newNextWateredDate.setDate(newLastWateredDate.getDate() + daysToWater)
    setNextDateToWater(newNextWateredDate)
    let days = (newNextWateredDate.getTime() - (new Date).getTime()) / (1000 * 60 * 60 * 24)
    setDaysLeftToWater(days)
  }

  const fetchUserWateredByCookie = () => {
    const cookie = Cookies.get("session_token")
    if (cookie) {
      fetch(`http://localhost:8080/api/v1/profile/0?sessionToken=${cookie}`, {
        method: "GET",
        credentials: "include"
      })
        .then(async (response) => {
          if (response.ok) {
            const data = await response.json();
            convertDateStrtoDate(data.lastWatered, data.daysToWater)
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }


  const handleUpdateWatering = (e: FormEvent<HTMLFormElement>): void => {
    e.preventDefault()
    fetch(`http://localhost:8080/api/v1/updateWatered/${currentUserId}`, {
      method: "PUT",
    })
      .then(async resp => {
        if (resp.ok) {
          let data = await resp.json()
          convertDateStrtoDate(data, daysToWater)
        }
      })
  }


  useEffect(() => {
    fetchUserWateredByCookie()
  }, [])
  return (
    <div className="w-full mx-4">
      <div className="text-center">
        <h2 className="text-2xl font-bold">Find a plant to water!</h2>
        <hr className="mt-4" />
        <div id="imageContainer">
          <img onClick={navigateToPlants} id="myImage" className="mx-auto rotate-180" src={PlantImg} alt="games image" />
        </div>
        {isAuthenticated && lastWatered && <>
          <div>Currently time to next water is set to 10 days</div>
          <div>Last time watered {lastWatered.toString().split("GMT")[0]}</div>
          <div>Next time to water {nextDateToWater.toString().split("GMT")[0]}</div>
          <div>Days left to water  {Math.round(daysLeftToWater * 10) / 10}</div>
          <div className="mx-auto relative w-full sm:w-1/2 bg-gray-200 rounded">
            <div style={{ width: `${calculateProcent()}%` }} className="absolute top-0 h-4 rounded shim-blue"></div>
          </div>
          <form onSubmit={handleUpdateWatering} className="mx-auto mt-4">
            <input
              className="hover:cursor-pointer bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              type="submit"
              value="Watered"
            />
          </form>
        </>}
      </div>
    </div>
  );
};

export default Home;