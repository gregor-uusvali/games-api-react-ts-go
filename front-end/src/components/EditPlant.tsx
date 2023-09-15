import { FormEvent, useEffect, useState } from "react";
import Input from "./form/Input";
import { useOutletContext } from '../context/OutletContext';
import { PlantType } from "./Plants";

const EditPlant = () => {
  const [name, setName] = useState("");
  const [initialName, setInitialName] = useState(""); // Initialize with an empty string
  const [description, setDescription] = useState("");
  const [image, setImage] = useState("");
  const [instruction, setInstruction] = useState("");
  const [plants, setPlants] = useState<PlantType[]>([])
  const [clicked, setClicked] = useState(false)

  const { setAlertClassName } = useOutletContext();
  const { setAlertMessage } = useOutletContext();
  const { setAlertType } = useOutletContext();


  const addInfo = (str: React.SetStateAction<string>, type: React.SetStateAction<string>) => {
    setAlertClassName("")
    setAlertMessage(str)
    setAlertType(type)
    setTimeout(() => {
      setAlertClassName("hidden")
    }, 4000)
  }

  const doTheFlip = (e: { currentTarget: { querySelector: (arg0: string) => any; }; }) => {
    
    // Remove the 'do-the-flip' class from all elements with the class
    if (!clicked) {
      setName("")
      setDescription("")
      setImage("")
      setInstruction("")
      const elementsWithClass = document.querySelectorAll('.do-the-flip');
      elementsWithClass.forEach((element) => {
        element.classList.remove('do-the-flip');
      });
      // Add the 'do-the-flip' class to the target element
      const childElement = e.currentTarget.querySelector('.flip-card-inner');
      if (childElement) {
        childElement.classList.add('do-the-flip');
      }
    }
    setClicked(true);

  }

  const removeTheFlip = (e: { stopPropagation: () => void; }) => {
    setClicked(false)
    e.stopPropagation(); // Prevent the click event from propagating to parent elements
    const flipCardInnerElements = document.querySelectorAll('.flip-card-inner');
    flipCardInnerElements.forEach((element) => {
      element.classList.remove('do-the-flip');
    });
  }

  const handleAdd = (e: React.FormEvent) => {
    e.preventDefault()
    if (name !== "" || description !== "" || image !== "" || instruction !== "") {
      const currentDate = new Date(); // Get the current date
      let payload = {
        name,
        description,
        image,
        instruction,
        date: currentDate.toISOString().substring(0, 10),
      }
      const headers = new Headers()
      headers.append("Content-Type", "application/json")
      const requestOptions = {
        method: "POST",
        headers: headers,
        body: JSON.stringify(payload)
      }
      fetch("http://localhost:8080/api/v1/plants/add", requestOptions)
        .then(async response => {
          if (response.ok) {
            const data = await response.json()
            console.log(data)
            addInfo("New plant added!", "success")
            removeTheFlip(e)
            setName("")
            setDescription("")
            setImage("")
            setInstruction("")
            getPlants()
          } else {
            const errorText = await response.text();
            addInfo(errorText, "error");
          }
        })
        .catch(error => {
          console.log(error)
          addInfo("An error has occured", "error")
        })

    } else {
      addInfo("No empty fields", "error")
    }
  }

  const getPlants = () => {
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
  }

  useEffect(() => {
    getPlants()
    if (plants.length > 0) {
      setInitialName(plants[0].name); // Assuming plants is an array of objects
    }
  }, [])

  return (
    <div className="w-full mx-4">
      <div className="text-center">
        <h2 className="text-2xl font-bold">Add/Edit Plants</h2>
        <hr className="my-4" />
        {/* cards here */}
        <div className="flex flex-wrap gap-4">
          <div onClick={doTheFlip} className="flip-card w-64">
            <div className="flip-card-inner">
              <div className="flip-card-front card h-full bg-white border border-gray-200 rounded-lg shadow dark:border-gray-700 flex items-center justify-center transition duration-300 hover:bg-gray-700">
                <svg className="svg-icon w-20 h-20 text-gray-700 " aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                  <circle cx="12" cy="12" r="11" stroke="currentColor" strokeWidth="1" fill="none" />
                  <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="1" d="M12 7v10M7 12h10" />
                </svg>
              </div>
              <div className="flip-card-back">
                <form onSubmit={handleAdd} className="mx-auto">
                  <div className="mt-2">
                    <Input
                      name="name"
                      type="text"
                      title="Name"
                      className="w-4/5 shadow appearance-none border rounded py-1 px-1 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      onChange={(event: { target: { value: any; }; }) => setName(event.target.value)}
                      autoComplete="name-new" placeholder="" value={name} errorDiv={""} errorMsg={""}
                    />
                  </div>
                  <div className="-m-1">
                    <Input
                      name="description"
                      type="text"
                      title="Description"
                      className="w-4/5 shadow appearance-none border rounded py-1 px-1 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      onChange={(event: { target: { value: any; }; }) => setDescription(event.target.value)}
                      autoComplete="description-new" placeholder={""} value={description} errorDiv={""} errorMsg={""}
                    />
                  </div>
                  <div className="-m-1">
                    <Input
                      name="image"
                      type="text"
                      title="Image"
                      className="w-4/5 shadow appearance-none border rounded py-1 px-1 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      onChange={(event: { target: { value: any; }; }) => setImage(event.target.value)}
                      autoComplete="image-new" placeholder={""} value={image} errorDiv={""} errorMsg={""}
                    />
                  </div>
                  <div className="-m-1">
                    <Input
                      name="instruction"
                      type="text"
                      title="Instruction"
                      className="w-4/5 shadow appearance-none border rounded py-1 px-1 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      onChange={(event: { target: { value: any; }; }) => setInstruction(event.target.value)}
                      autoComplete="instruction-new" placeholder={""} value={instruction} errorDiv={""} errorMsg={""}
                    />
                  </div>
                  <div className="flex items-center justify-center gap-5 mt-4">
                    <input
                      className="text-sm hover:cursor-pointer bg-gray-500 hover:bg-gray-700 text-white  py-1 px-2 rounded focus:outline-none focus:shadow-outline"
                      type="button"
                      onClick={removeTheFlip}
                      value="Back"
                    />
                    <input
                      className="text-sm hover:cursor-pointer bg-blue-500 hover:bg-blue-700 text-white py-1 px-3 rounded focus:outline-none focus:shadow-outline"
                      type="submit"
                      value="Add"
                    />
                  </div>
                </form>
              </div>
            </div>
          </div>
          {plants.map((m) => (
          <div key={m.id} onClick={doTheFlip} className="flip-card w-64">
            <div className="flip-card-inner">
              <div className="flip-card-front card h-full bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-700">
                <img className="h-4/5 w-full rounded-t-lg" src={m.image} alt="plant-image" />
                <div className="px-5 py-2">
                  <h5 className="mb-1 text-xl font-bold tracking-tight text-gray-900 dark:text-white">{m.name}</h5>
                </div>
              </div>
              <div className="flip-card-back">
                <form onSubmit={handleAdd} className="mx-auto">
                  <div className="mt-2">
                    <Input
                      name="name"
                      type="text"
                      title="Name"
                      className="w-4/5 shadow appearance-none border rounded py-1 px-1 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      onChange={(event: { target: { value: any; }; }) => setName(event.target.value)}
                      autoComplete="name-new" 
                      placeholder="" 
                      value={name === "" ? m.name : name} 
                      errorDiv={""} 
                      errorMsg={""}
                    />
                  </div>
                  <div className="-m-1">
                    <Input
                      name="description"
                      type="text"
                      title="Description"
                      className="w-4/5 shadow appearance-none border rounded py-1 px-1 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      onChange={(event: { target: { value: any; }; }) => setDescription(event.target.value)}
                      autoComplete="description-new" placeholder={""} value={description === "" ? m.description : description} errorDiv={""} errorMsg={""}
                    />
                  </div>
                  <div className="-m-1">
                    <Input
                      name="image"
                      type="text"
                      title="Image"
                      className="w-4/5 shadow appearance-none border rounded py-1 px-1 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      onChange={(event: { target: { value: any; }; }) => setImage(event.target.value)}
                      autoComplete="image-new" placeholder={""} value={image === "" ? m.image : image} errorDiv={""} errorMsg={""}
                    />
                  </div>
                  <div className="-m-1">
                    <Input
                      name="instruction"
                      type="text"
                      title="Instruction"
                      className="w-4/5 shadow appearance-none border rounded py-1 px-1 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      onChange={(event: { target: { value: any; }; }) => setInstruction(event.target.value)}
                      autoComplete="instruction-new" placeholder={""} value={instruction === "" ? m.instruction : instruction} errorDiv={""} errorMsg={""}
                    />
                  </div>
                  <div className="flex items-center justify-center gap-5 mt-4">
                    <input
                      className="text-sm hover:cursor-pointer bg-gray-500 hover:bg-gray-700 text-white  py-1 px-2 rounded focus:outline-none focus:shadow-outline"
                      type="button"
                      onClick={removeTheFlip}
                      value="Back"
                    />
                    <input
                      className="text-sm hover:cursor-pointer bg-blue-500 hover:bg-blue-700 text-white py-1 px-3 rounded focus:outline-none focus:shadow-outline"
                      type="submit"
                      value="Edit"
                    />
                    <input
                      className="text-sm hover:cursor-pointer bg-red-500 hover:bg-red-700 text-white py-1 px-3 rounded focus:outline-none focus:shadow-outline"
                      type="submit"
                      value="Delete"
                    />
                  </div>
                </form>
              </div>
            </div>
          </div>
        ))}
        </div>
      </div>
    </div>
  );
};

export default EditPlant;