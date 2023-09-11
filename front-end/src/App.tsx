import React, { useState } from 'react';
import './styles/tailwind.css'; // Import Tailwind CSS
import Home from "./components/Home"
import { Link, Outlet, useNavigate } from 'react-router-dom';
import { OutletContext } from './context/OutletContext';
import Alert from './components/Alert';

function App() {
  const [jwtToken, setJwtToken] = useState("");
  const [alertMessage, setAlertMessage] = useState("")
  const [alertClassName, setAlertClassName] = useState("hidden")
  const [alertType, setAlertType] = useState("")

  const navigate = useNavigate()


  const logOut = () => {
    setJwtToken("")
    navigate("/login")
  }

  return (
    <OutletContext.Provider value={{
      jwtToken,
      setJwtToken,
      alertMessage,
      setAlertMessage,
      alertClassName,
      setAlertClassName,
      alertType,
      setAlertType
    }}>
      <div className="container mx-auto">
        <div className="flex justify-between mt-3 mb-3">
          <div className="flex-grow">
            <h1 className='text-3xl font-bold'>Go play a game 🚀</h1>
          </div>
          <div className="flex text-end items-center">
            {jwtToken === ""
              ? <Link to="/login">
                <span className="bg-green-500 text-white py-1 px-3 rounded-full">Login</span>
              </Link>
              : <a href="#!" onClick={logOut}><span className="bg-red-500 text-white py-1 px-3 rounded-full">Logout</span></a>
            }
          </div>
        </div>
        <hr className="my-4" />
        <div className="flex">
          <div className="w-1/5">
            <nav>
              <div className="border rounded divide-y divide-solid">
                <Link to="/" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">Home</Link>
                <Link to="/games" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">Games</Link>
                <Link to="/ganres" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">Ganres</Link>
                {jwtToken !== "" &&
                  <>
                    <Link to="/admin/game/0" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">Add Game</Link>
                    <Link to="/manage-catalogue" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">Manage Games</Link>
                    <Link to="/graphql" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">GraphQL</Link>
                  </>
                }
              </div>
            </nav>
          </div>
          <div className='w-full'>
            <Alert type={alertType} message={alertMessage} className={alertClassName} />
            <div className="w-4/5 flex justify-center">
              <Outlet />
            </div>
          </div>
        </div>
      </div>
    </OutletContext.Provider>
  );
}

export default App;