import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import ErrorPage from './components/ErrorPage';
import Home from './components/Home';
import Games from './components/Games';
import Ganres from './components/Ganres';
import EditGame from './components/EditGame';
import ManageCatalogue from './components/ManageCatalogue';
import GraphQL from './components/GraphQL';
import Login from './components/Login';
import Game from './components/Game';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {index: true, element: <Home />},
      {
        path: "/games",
        element: <Games />,
      },
      {
        path: "/games/:id",
        element: <Game />,
      },
      {
        path: "/ganres",
        element: <Ganres />,
      },
      {
        path: "/admin/game/0",
        element: <EditGame />,
      },
      {
        path: "/manage-catalogue",
        element: <ManageCatalogue />,
      },
      {
        path: "/graphql",
        element: <GraphQL />,
      },
      {
        path: "/login",
        element: <Login />,
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

