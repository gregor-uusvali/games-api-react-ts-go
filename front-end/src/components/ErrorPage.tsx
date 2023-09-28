import { useRouteError } from "react-router-dom";

export default function ErrorPage() {
  const error: any = useRouteError()

  return (
    <div className="container">
      <div className="flex justify-center">
        <div className="w-1/2">
          <h1 className="text-2xl mt-3">Oops!</h1>
          <p>Sorry, an unexpected error has occurred.</p>
          <p>
            <em>{error.statusText || error.message}</em>
          </p>
        </div>
      </div>
    </div>
  )
}