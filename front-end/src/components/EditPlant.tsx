import { FormEvent, useEffect, useState } from "react";
import Input from "./form/Input";
import { useOutletContext } from '../context/OutletContext';
import { PlantType } from "./Plants";
import ConfirmationModal from "./modal/ConfirmationModal";

const EditPlant = () => {
  const [plantId, setPlantId] = useState(0);
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [image, setImage] = useState<File | undefined>();
  const [instruction, setInstruction] = useState("");
  const [plants, setPlants] = useState<PlantType[]>([]);
  const [clicked, setClicked] = useState(false);
  const [isConfirmationOpen, setConfirmationOpen] = useState(false);
  const [activeCardId, setActiveDardId] = useState<number | null>(null)

  const { setAlertClassName } = useOutletContext();
  const { setAlertMessage } = useOutletContext();
  const { setAlertType } = useOutletContext();

  const setInitialVals = (plantId: number) => {
    for (let i = 0; i < plants.length; i++) {
      if (plants[i].id === plantId) {
        setPlantId(plantId)
        setName(plants[i].name)
        setDescription(plants[i].description)
        setImage(undefined)
        setInstruction(plants[i].instruction)
        break
      }
    }
  }

  const openConfirmation = () => {
    setConfirmationOpen(true)
  };

  const handleConfirmCancel = () => {
    setConfirmationOpen(false);
  };

  const handleConfirm = (e: any) => {
    handleDelete(e)
    setConfirmationOpen(false);
  };


  const addInfo = (str: React.SetStateAction<string>, type: React.SetStateAction<string>) => {
    setAlertClassName("")
    setAlertMessage(str)
    setAlertType(type)
    setTimeout(() => {
      setAlertClassName("hidden")
    }, 4000)
  }

  const doTheFlip = (
    e: React.MouseEvent<HTMLDivElement, MouseEvent>,
    plantId: number | null = null
  ) => {
    setActiveDardId(plantId)
    if (!clicked) {
      if (plantId !== null) {
        setInitialVals(plantId)
      } else {
        setPlantId(0)
        setName("")
        setDescription("")
        setImage(undefined)
        setInstruction("")
      }
      const elementsWithClass = document.querySelectorAll('.do-the-flip');
      elementsWithClass.forEach((element) => {
        element.classList.remove('do-the-flip');
      });
      // Add the 'do-the-flip' class to the target element
      const childElement = e.currentTarget.querySelector('.flip-card-inner');
      if (childElement) {
        childElement.classList.add('do-the-flip');
      }
    } else {
      const flipCardInnerElements = document.querySelectorAll('.flip-card-inner');
      flipCardInnerElements.forEach((element) => {
        element.classList.remove('do-the-flip');
      });
      if (plantId !== activeCardId) {
        if (plantId !== null) {
          setInitialVals(plantId)
        } else {
          setPlantId(0)
          setName("")
          setDescription("")
          setImage(undefined)
          setInstruction("")
        }

      }
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
    setActiveDardId(plantId)
  }

  const removeTheFlip = (e: { stopPropagation: () => void; }) => {
    setClicked(false)
    clearInputFile()
    e.stopPropagation(); // Prevent the click event from propagating to parent elements
    const flipCardInnerElements = document.querySelectorAll('.flip-card-inner');
    flipCardInnerElements.forEach((element) => {
      element.classList.remove('do-the-flip');
    });
  }

  const handleAdd = (e: React.FormEvent) => {
    e.preventDefault();

    if (name !== "" && description !== "" && image !== undefined && instruction !== "") {
      const currentDate = new Date(); // Get the current date
      const formData = new FormData();
      formData.append("name", name);
      formData.append("description", description);
      if (image) {
        formData.append("image", image);
      }
      formData.append("instruction", instruction);
      formData.append("date", currentDate.toISOString().substring(0, 10));

      const requestOptions = {
        method: "POST",
        body: formData,
      };

      fetch("http://localhost:8080/api/v1/plants/add", requestOptions)
        .then(async (response) => {
          if (response.ok) {
            const data = await response.json();
            console.log(data);
            addInfo("New plant added!", "success");
            removeTheFlip(e);
            setPlantId(0);
            setName("");
            setDescription("");
            setImage(undefined);
            setInstruction("");
            getPlants();
          } else {
            const errorText = await response.text();
            addInfo(errorText, "error");
          }
        })
        .catch((error) => {
          console.log(error);
          addInfo("An error has occurred", "error");
        });
    } else {
      addInfo("No empty fields", "error");
    }
  };

  const handleUpdate = (e: React.FormEvent) => {
    e.preventDefault();
    if (name !== "" && description !== "" && instruction !== "") {
      const formData = new FormData();
      const updatedName = name;
      const updatedDescription = description;
      const updatedInstruction = instruction;
      formData.append("name", updatedName);
      formData.append("description", updatedDescription);
      formData.append("instruction", updatedInstruction);
      if (image) {
        const updatedImage = image;
        formData.append("image", updatedImage);
      }

      const requestOptions = {
        method: "PUT",
        body: formData,
      };

      fetch(`http://localhost:8080/api/v1/plants/${plantId}`, requestOptions)
        .then(async (response) => {
          if (response.ok) {
            addInfo("Plant edited!", "success")
            removeTheFlip(e)
            setPlantId(0)
            setName("")
            setDescription("")
            setImage(undefined)
            setInstruction("")
            getPlants()
          } else {
            const errorText = await response.text();
            addInfo(errorText, "error");
          }
        })
        .catch((error) => {
          console.log(error)
          addInfo("An error has occured", "error")
        });
    } else {
      addInfo("No empty fields", "error")
    }
  };

  const handleDelete = (e: any) => {
    e.preventDefault();
    console.log("Deleting plant with id: ", plantId)
    const requestOptions = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    };
    fetch(`http://localhost:8080/api/v1/plants/${plantId}`, requestOptions)
      .then(async (response) => {
        if (response.ok) {
          addInfo("Plant deleted!", "success")
          removeTheFlip(e)
          setPlantId(0)
          setName("")
          setDescription("")
          setImage(undefined)
          setInstruction("")
          getPlants()
        } else {
          const errorText = await response.text();
          addInfo(errorText, "error");
        }
      })
      .catch((error) => {
        console.log(error)
        addInfo("An error has occured", "error")
      });
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
  const handleFileChange = (e: React.FormEvent<HTMLInputElement>) => {
    const target = e.target as HTMLInputElement & {
      files: FileList;
    }
    setImage(target.files[0])
    console.log('target', target.files)
  }
  const clearInputFile = () => {
    const inputs = document.querySelectorAll<HTMLInputElement>(".inputFileClass");
    inputs.forEach(input => {
      input.value = "";
    });
  };

  useEffect(() => {
    getPlants()
    if (plants.length > 0) {
    }
  }, [])


  return (
    <div className="w-full mx-4">
      <div className="text-center">
        <h2 className="text-2xl font-bold">Add/Edit Plants</h2>
        <hr className="my-4" />
        {/* cards here */}
        <div className="flex flex-wrap gap-4">
          <div onClick={(e) => doTheFlip(e)} className="flip-card w-96">
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
                  <div className="mx-8">
                    <label htmlFor="description" className="block text-gray-700 text-sm font-bold mb-1">Description</label>
                    <textarea
                      name="description"
                      title="Description"
                      id="message"
                      rows={3}
                      className="block p-2.5 w-full text-gray-900 bg-gray-50 rounded-lg shadow appearance-none mb-3"
                      onChange={(event) => setDescription(event.target.value)}
                      autoComplete="description-new"
                      placeholder={""}
                      value={description}
                    />
                  </div>
                  <div className="mx-8">
                    <label htmlFor="instruction" className="block text-gray-700 text-sm font-bold mb-1">Instruction</label>
                    <textarea
                      name="instruction"
                      title="Instruction"
                      id="message"
                      rows={3}
                      className="block p-2.5 w-full text-gray-900 bg-gray-50 rounded-lg shadow appearance-none mb-3"
                      onChange={(event) => setInstruction(event.target.value)}
                      autoComplete="instruction-new"
                      placeholder={""}
                      value={instruction}
                    />
                  </div>
                  <div className="-m-1">
                    <input className="inputFileClass" type="file" name="image" onChange={handleFileChange} />
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
            <div key={m.id} onClick={(e) => doTheFlip(e, m.id)} className="flip-card w-96">
              <div className="flip-card-inner">
                <div className="flip-card-front card h-full bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-700">
                  <img className="h-4/5 w-full rounded-t-lg" src={m.image} alt="plant-image" />
                  <div className="px-5 py-2">
                    <h5 className="mb-1 text-xl font-bold tracking-tight text-gray-900 dark:text-white">{m.name}</h5>
                  </div>
                </div>
                <div className="flip-card-back">
                  <form onSubmit={handleUpdate} className="mx-auto">
                    <div className="mt-2">
                      <Input
                        name="name"
                        type="text"
                        title="Name"
                        className="w-4/5 shadow appearance-none border rounded py-1 px-1 text-gray-700 leading-tight focus:outline focus:shadow-outline"
                        onChange={(event: { target: { value: any; }; }) => setName(event.target.value)}
                        autoComplete="name-new"
                        placeholder=""
                        value={name}
                        errorDiv={""}
                        errorMsg={""}
                      />
                    </div>
                    <div className="mx-8">
                      <label htmlFor="description" className="block text-gray-700 text-sm font-bold mb-1">Description</label>
                      <textarea
                        name="description"
                        title="Description"
                        id="message"
                        rows={3}
                        className="block p-2.5 w-full text-gray-900 bg-gray-50 rounded-lg shadow appearance-none mb-3"
                        onChange={(event) => setDescription(event.target.value)}
                        autoComplete="description-new"
                        placeholder={""}
                        value={description}
                      />
                    </div>
                    <div className="mx-8">
                      <label htmlFor="instruction" className="block text-gray-700 text-sm font-bold mb-1">Instruction</label>
                      <textarea
                        name="instruction"
                        title="Instruction"
                        id="message"
                        rows={3}
                        className="block p-2.5 w-full text-gray-900 bg-gray-50 rounded-lg shadow appearance-none mb-3"
                        onChange={(event) => setInstruction(event.target.value)}
                        autoComplete="instruction-new"
                        placeholder={""}
                        value={instruction}
                      />
                    </div>
                    <div className="-m-1">
                      <input className="inputFileClass" type="file" name="image" onChange={handleFileChange} />
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
                        type="button"
                        value="Edit"
                        onClick={handleUpdate}
                      />
                      <input
                        className="text-sm hover:cursor-pointer bg-red-500 hover:bg-red-700 text-white py-1 px-3 rounded focus:outline-none focus:shadow-outline"
                        type="button"
                        value="Delete"
                        onClick={openConfirmation}
                      />
                    </div>
                    <div>
                      <ConfirmationModal
                        isOpen={isConfirmationOpen}
                        onConfirm={(e: any) => handleConfirm(e)}
                        onCancel={handleConfirmCancel}
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