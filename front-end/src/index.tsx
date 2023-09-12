import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import ErrorPage from './components/ErrorPage';
import Home from './components/Home';
import Plants from './components/Plants';
import Family from './components/Family';
import EditPlant from './components/EditPlant';
import ManagePlants from './components/ManagePlants';
import GraphQL from './components/GraphQL';
import Login from './components/Login';
import Plant from './components/Plant';
import Register from './components/Register';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {index: true, element: <Home />},
      {
        path: "/plants",
        element: <Plants />,
      },
      {
        path: "/plants/:id",
        element: <Plant />,
      },
      {
        path: "/family",
        element: <Family />,
      },
      {
        path: "/admin/plant/0",
        element: <EditPlant />,
      },
      {
        path: "/manage-plants",
        element: <ManagePlants />,
      },
      {
        path: "/graphql",
        element: <GraphQL />,
      },
      {
        path: "/login",
        element: <Login />,
      },
      {
        path: "/register",
        element: <Register />,
      },
    ]
  }
])

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

