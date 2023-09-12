import { ChangeEvent, useState } from "react";
import Input from "./form/Input";
// import { useOutlet, useOutletContext } from "react-router-dom";
import { useOutletContext } from '../context/OutletContext'; // Import the useOutletContext from your OutletContext file
import { useNavigate } from "react-router-dom";


const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const { setJwtToken } = useOutletContext();
  const { setAlertClassName } = useOutletContext();
  const { setAlertMessage } = useOutletContext();
  const { setAlertType } = useOutletContext();

  const navigate = useNavigate()

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    console.log("email/pass", email, password);
    if (email === "test@test.com") {
      setJwtToken("abc");
      setAlertClassName("hidden")
      setAlertMessage("")
      navigate("/")
    } else {
      setAlertClassName("")
      setAlertMessage("Invalid credentials!")
      setAlertType("error")
      setTimeout(() => {
        setAlertClassName("hidden")
      }, 4000)
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
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
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