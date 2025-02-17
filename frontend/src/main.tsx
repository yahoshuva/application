import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './index.scss'
import { Feed } from './features/feed/pages/Feed';
import { Login } from './features/authentication/pages/Login/Login';
import ResetPassword from './features/authentication/pages/ResetPassword/ResetPassword';
import {VerifyEmail} from './features/authentication/pages/VerifyEmail/VerifyEmail';
import { Signup } from './features/authentication/pages/Signup/Signup';
import { AuthenticationContextProvider } from './features/authentication/contexts/AuthenticationContextProvider';


const router = createBrowserRouter([

  {
    element: <AuthenticationContextProvider />,
    children:[
      {
        path:"/",
        element:<Feed />,
      },
      
      {
        path:"/login",
        element:<Login />,
      },
      
      {
        path:"/signup",
        element:<Signup />,
      },
      {
        path:"/request-password-reset",
        element:<ResetPassword />,
      },
      {
        path:"/verify-email",
        element:<VerifyEmail />,
      },

    ]
  },




]

);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
   <RouterProvider router={router} />
  </StrictMode>,
)
