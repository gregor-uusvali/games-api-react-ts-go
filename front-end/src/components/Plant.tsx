import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { PlantType } from "./Plants";

const Plant = () => {
    const [plant, setPlant] = useState<PlantType| null>(null)
    let { id } = useParams()

    const fetchPlant = () => {
      const headers = new Headers()
      headers.append("Content-Type", "application/json")
      const requestOptions = {
        method: "GET",
        headers: headers
      }
      fetch(`http://localhost:8080/api/v1/plant/${id}/`, requestOptions)
        .then((response) => response.json()
        .then(data => {
          console.log(data)
        })
        .catch(error => {
          console.log(error)
        })
        )

    }

    useEffect(() => {
      fetchPlant()
        let plant = {
            id: 1,
            name: "Schlumbergera",
            description: "Schlumbergera is a small genus of cacti with six to nine species found in the coastal mountains of south-eastern Brazil. These plants grow on trees or rocks in habitats that are generally shady with high humidity, and can be quite different in appearance from their desert-dwelling cousins.",
            image: "https://juhendaja.ee/wp-content/uploads/2014/10/120.jpg",
            instruction: "Mist your plant a few times a week, or place on a pebble-filled tray of water. Feed monthly in spring or summer with a general fertiliser. Schlumbergera don't need pruning, but the stems can get leggy or too long. Make the plant more bushy by removing the tips after the plant has flowered.",
            date: "2023-09-12",
        }
        setPlant(plant)
    }, [id])

    return (
      <div className="w-full flex gap-4">
        <img className="w-1/5" src={plant?.image} alt="plant image" />
        <div className="w-4/5">
          <h2 className="text-2xl font-bold">Plant: {plant !== null && plant.name}</h2>
          <small><em>Added on {plant?.date.toString()}</em></small>
          <hr className="my-4" />
          <p>{plant?.description}</p>
        </div>
      </div>
    );
  };
  
  export default Plant;