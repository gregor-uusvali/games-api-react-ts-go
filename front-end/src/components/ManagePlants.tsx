import { useEffect } from "react";
import { useOutletContext } from '../context/OutletContext';
import { useNavigate } from "react-router-dom";

const ManagePlants = () => {
  const { isAuthenticated } = useOutletContext();

  const navigate = useNavigate()
  useEffect(() => {
    console.log(isAuthenticated)
    if (!isAuthenticated){
      navigate("/")
    }
  })
  return (
      <div className="w-full mx-4">
        <div className="text-center">
          <h2 className="text-2xl font-bold">Manage Plants</h2>
          <hr className="my-4" />
        </div>
      </div>
    );
  };
  
  export default ManagePlants;