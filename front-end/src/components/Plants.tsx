import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export interface PlantType {
  id: number,
  name: string,
  description: string,
  image: string,
  instruction: string,
  date: string,
}

const Plants = () => {
  const [plants, setPlants] = useState<PlantType[]>([])

  useEffect(() => {
    const headers = new Headers()
    headers.append("Content-Type", "application/json")

    const requestOptions = {
      method: "GET",
      headers: headers,
    }
    fetch(`http://localhost:8080/api/v1/plants`, requestOptions)
      .then((response) => response.json())
      .then((data) => {
        setPlants(data)
      })
      .catch(err => {
        console.log(err)
      })
  }, [])

  return (
    <div className="w-full mx-4">
      <div className="text-center">
        <h2 className="text-2xl font-bold">Plants</h2>
        <hr className="my-4" />
        <div className="overflow-x-auto">
          <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
            <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
              <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                  <th scope="col" className="px-6 py-3">
                    Plant
                  </th>
                  <th scope="col" className="px-6 py-3">
                    Date
                  </th>
                  <th scope="col" className="px-6 py-3 text-center">
                    Image
                  </th>
                </tr>
              </thead>
              <tbody>
                {plants.map((m) => (
                  <tr key={m.id} className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                    <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                      <Link to={`/plants/${m.id}`} className="text-blue-500">
                        {m.name}
                      </Link>
                    </th>
                    <td className="px-6 py-4">
                      {m.date.toString()}
                    </td>
                    <td className="px-6 py-4">
                      <img className=" h-20 mx-auto object-contain" src={m.image} alt="plant image" />
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

        </div>
      </div>
    </div>
  );
};

export default Plants;