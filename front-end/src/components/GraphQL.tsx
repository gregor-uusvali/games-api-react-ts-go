import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Cookie from 'js-cookie'

const GraphQL = () => {
  const [hasCookie, setHasCookie] = useState(false)

  const navigate = useNavigate()

  useEffect(() => {
    const cookie = Cookie.get("session_token")
    if (cookie === undefined) {
      navigate("/")
    } else {
      setHasCookie(true)
    }
  }, [])

  return (
    <>
      {hasCookie && <div className="w-full mx-4">
        <div className="text-center">
          <h2 className="text-2xl font-bold">GraphQL</h2>
          <hr className="my-4" />
        </div>
      </div>
      }
    </>
  );
};

export default GraphQL;