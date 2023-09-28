import { useState } from "react";
import Input from "./form/Input";
import { useOutletContext } from '../context/OutletContext';
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [email, setEmail] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("")

  const { setJwtToken } = useOutletContext();
  const { setAlertClassName } = useOutletContext();
  const { setAlertMessage } = useOutletContext();
  const { setAlertType } = useOutletContext();

  const navigate = useNavigate()

  const addInfo = (str: React.SetStateAction<string>, type: React.SetStateAction<string>) => {
    setAlertClassName("")
    setAlertMessage(str)
    setAlertType(type)
    setTimeout(() => {
      setAlertClassName("hidden")
    }, 4000)
  }

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    if (password === confirmPassword) {
      let payload = {
        email,
        firstName,
        lastName,
        password,
        confirmPassword
      }
      const headers = new Headers();
      headers.append("Content-Type", "application/json")
      const requestOptions = {
        method: "POST",
        headers: headers,
        body: JSON.stringify(payload)
      }
      fetch("http://localhost:8080/api/v1/register", requestOptions)
        .then(async (response) => {
          if (response.ok) {
            const data = await response.json();
            addInfo("Succesfully registered", "success")
            navigate("/login");
          } else {
            const errorText = await response.text();
            if (errorText === "Email is already taken") {
              // Handle the specific email taken error
              addInfo(errorText, "error");
            } else {
              // Handle other error cases
              addInfo("An error occurred", "error");
            }
          }
        })
        .catch((error) => {
          console.log(error)
          addInfo("An error occurred", "error")
        });
    } else {
      addInfo("Passwords must match!", "error")
    }
  }

  return (
    <div className="w-full mx-4">
      <div className="text-center">
        <h2 className="text-2xl font-bold">Register</h2>
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
              name="firstName"
              type="text"
              title="First Name"
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              onChange={(event) => setFirstName(event.target.value)}
              autoComplete="firstname-new" placeholder={""} value={firstName} errorDiv={""} errorMsg={""}
            />
          </div>
          <div className="mb-6">
            <Input
              name="lastName"
              type="text"
              title="Last Name"
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              onChange={(event) => setLastName(event.target.value)}
              autoComplete="lastname-new" placeholder={""} value={lastName} errorDiv={""} errorMsg={""}
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
          </div>
          <div className="mb-6">
            <Input
              name="confirmPassword"
              type="password"
              title="Confirm Password"
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              onChange={(event) => setConfirmPassword(event.target.value)}
              autoComplete="confirmpassword-new" placeholder={""} value={confirmPassword} errorDiv={""} errorMsg={""}
            />
          </div>
          <div className="flex items-center justify-center">
            <input
              className="hover:cursor-pointer bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
              type="submit"
              value="Register"
            />
          </div>
          <p className="mt-4 text-sm font-light text-black-500 dark:text-black-400">
            Already have an account? <a href="/login" className="font-bold text-primary-600 hover:underline dark:text-primary-500">Log in</a>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Register;