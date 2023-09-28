import { useEffect, useState } from "react";
import { useOutletContext } from '../context/OutletContext';
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";

const ManagePlants = () => {
  const [hasCookie, setHasCookie] = useState(false)

  const navigate = useNavigate()
  useEffect(() => {
    const cookie = Cookies.get("session_token")
    if (cookie === undefined) {
      navigate("/")
    } else {
      setHasCookie(true)
    }
  })
  return (
    <>
      {hasCookie && <div className="w-full mx-4">
        <div className="text-center">
          <h2 className="text-2xl font-bold">Manage Plants</h2>
          <hr className="my-4" />
        </div>
      </div>
      }
    </>
  );
};

export default ManagePlants;