import React, { useEffect, useState } from 'react';
import './styles/tailwind.css'; // Import Tailwind CSS
import { Link, Outlet, useNavigate } from 'react-router-dom';
import { OutletContext } from './context/OutletContext';
import Alert from './components/Alert';
import Cookies from "js-cookie";

function App() {
  const [jwtToken, setJwtToken] = useState("");
  const [sessionToken, setSessionToken] = useState("")
  const [alertMessage, setAlertMessage] = useState("")
  const [alertClassName, setAlertClassName] = useState("hidden")
  const [alertType, setAlertType] = useState("")
  const [currentUserId, setCurrentUserId] = useState<number>(0); // Initialize with your initial value
  const [isAuthenticated, setIsAuthenticated] = useState(false)

  const navigate = useNavigate()


  const logOut = () => {
    setJwtToken("")
    setSessionToken("")
    Cookies.remove("session_token");
    navigate("/login")
  }

  const fetchUserByCookie = () => {

  }

  const isLoggedIn = () => {
    const cookie = Cookies.get("session_token");
    if (cookie) {
      setSessionToken(cookie)
      setIsAuthenticated(true)
      fetch(`http://localhost:8080/api/v1/profile/0?sessionToken=${cookie}`, {
        method: "GET",
        credentials: "include"
      })
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      console.log("no cookies")
    }
  }

  useEffect(() => {

    isLoggedIn()
  }, [])

  return (
    <OutletContext.Provider value={{
      isAuthenticated,
      setIsAuthenticated,
      sessionToken,
      setSessionToken,
      jwtToken,
      setJwtToken,
      alertMessage,
      setAlertMessage,
      alertClassName,
      setAlertClassName,
      alertType,
      setAlertType,
      currentUserId,
      setCurrentUserId,
    }}>
      <div className="container mx-auto">
        <div className="flex justify-between mt-3 mb-3">
          <div className="flex-grow">
            <h1 className='w-fit text-3xl font-bold'> <Link to="/">Water a plant 🪴</Link></h1>

          </div>
          <div className="flex text-end items-center">
            {sessionToken === ""
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
                <Link to="/plants" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">Plants</Link>
                <Link to="/family" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">Family</Link>
                {sessionToken !== "" &&
                  <>
                    <Link to="/admin/plant/0" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">Add/Edit Plants</Link>
                    <Link to="/manage-plants" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">Manage Plants</Link>
                    <Link to="/graphql" className="block px-4 py-2 text-gray-800 hover:bg-gray-200">GraphQL</Link>
                  </>
                }
              </div>
            </nav>
          </div>
          <div className='w-full mx-4'>
            <Alert type={alertType} message={alertMessage} className={alertClassName} />
            <div className="mx-auto w-full flex justify-center">
              <Outlet />
            </div>
          </div>
        </div>
      </div>
    </OutletContext.Provider>
  );
}

export default App;