import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { PlantType } from "./Plants";

const Plant = () => {
  const [plant, setPlant] = useState<PlantType | null>(null)
  let { id } = useParams()

  const fetchPlant = () => {
    const headers = new Headers()
    headers.append("Content-Type", "application/json")
    const requestOptions = {
      method: "GET",
      headers: headers
    }
    fetch(`http://localhost:8080/api/v1/plants/${id}`, requestOptions)
      .then((response) => response.json()
        .then(data => {
          let plant = {
            id: data.id,
            name: data.name,
            description: data.description,
            image: data.image,
            instruction: data.instruction,
            date: data.date,
          }
          setPlant(plant)
        })
        .catch(error => {
          console.log(error)
        })
      )

  }

  useEffect(() => {
    fetchPlant()
  }, [id])

  return (
    <div className="w-full flex gap-4">
      <div className="w-52">
        <img
          className="w-200 h-200 object-contain"
          src={plant?.image}
          alt="plant image"
        />
      </div>
      <div className="w-4/5">
        <h2 className="text-2xl font-bold">Plant: {plant !== null && plant.name}</h2>
        <small><em>Added on {plant?.date.toString()}</em></small>
        <hr className="my-4" />
        <h3 className="text-xl font-bold">Description</h3>
        <p>{plant?.description}</p>
        <hr className="my-4" />
        <h3 className="text-xl font-bold">Instruction</h3>
        <p>{plant?.instruction}</p>
      </div>
    </div>
  );
};


export default Plant;