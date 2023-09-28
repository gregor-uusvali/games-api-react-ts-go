import { ChangeEvent, useState } from "react";
import Input from "./form/Input";
// import { useOutlet, useOutletContext } from "react-router-dom";
import { useOutletContext } from '../context/OutletContext'; // Import the useOutletContext from your OutletContext file
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";


const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const { setCurrentUserId } = useOutletContext();
  const { setFirstName } = useOutletContext();
  const { setLastName } = useOutletContext();
  const { setIsAuthenticated } = useOutletContext();
  const { setSessionToken } = useOutletContext();
  const { setAlertClassName } = useOutletContext();
  const { setAlertMessage } = useOutletContext();
  const { setAlertType } = useOutletContext();
  const { setLastWatered } = useOutletContext();
  const { setDaysToWater } = useOutletContext();

  const navigate = useNavigate()

  const addInfo = (str: React.SetStateAction<string>, type: React.SetStateAction<string>) => {
    setAlertClassName("alert-banner")
    setAlertMessage(str)
    setAlertType(type)
    setTimeout(() => {
      setAlertClassName("hidden")
    }, 4000)
  }

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    if (email !== "" || password !== "") {
      let payload = {
        email,
        password
      }
      const headers = new Headers();
      headers.append("Content-Type", "application/json")
      const requestOptions = {
        method: "POST",
        headers: headers,
        body: JSON.stringify(payload)
      }
      fetch("http://localhost:8080/api/v1/login", requestOptions)
        .then(async (response) => {
          if (response.ok) {
            const data = await response.json();
            console.log(data)
            Cookies.set("session_token", data.sessionToken)
            const userID: number = parseInt(data.userId)
            setCurrentUserId(userID)
            setSessionToken(data.sessionToken)
            setIsAuthenticated(true)
            setFirstName(data.firstName)
            setLastName(data.lastName)
            setLastWatered(data.lastWatered)
            setDaysToWater(data.daysToWater)
            navigate("/");
          } else {
            const errorText = await response.text();
            addInfo(errorText, "error");
          }
        })

        .catch(error => {
          console.log(error)
          addInfo("An error occurred", "error")
        })
    } else {
      addInfo("No empty fields", "error")
    }
  }

  return (
    <div className="w-full mx-4">
      <div className="text-center">
        <h2 className="text-2xl font-bold">Login</h2>
        <hr className="my-4" />
        <form onSubmit={handleSubmit} className="mx-auto w-80 bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
          <div className="mb-4">
            <Input
              name="email"
              type="email"
              title="Email Address"
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              onChange={(event) => setEmail(event.target.value)}
              autoComplete="email-new" placeholder="" value={email} errorDiv={""} errorMsg={""}
            />
          </div>
          <div className="mb-6">
            <Input
              name="password"
              type="password"
              title="Password"
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              onChange={(event) => setPassword(event.target.value)}
              autoComplete="password-new" placeholder={""} value={password} errorDiv={""} errorMsg={""}
            />
            {/* <p className="text-red-500 text-xs italic">Please choose a password.</p> */}
          </div>
          <div className="flex items-center justify-center">
            <input
              className="hover:cursor-pointer bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              type="submit"
              value="Login"
            />
          </div>
          <p className="mt-4 text-sm font-light text-black-500 dark:text-black-400">
            Don’t have an account yet? <a href="/register" className="font-bold text-primary-600 hover:underline dark:text-primary-500">Sign up</a>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Login;