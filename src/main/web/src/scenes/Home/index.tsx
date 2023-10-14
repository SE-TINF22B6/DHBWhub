import React from 'react';
import {NavBar} from "./components/NavBar";
// import {useLocation, useNavigate} from 'react-router';

  export const Home = () => {

  // Access the navigate function to programmatically navigate
  // const navigate = useNavigate();
  // Access the location object to get the current URL location
  // const location = useLocation();

  return (
      <div className='homepage'>
          <NavBar/>
      </div>
  );
};
