import React from 'react';
import {useLocation, useNavigate} from 'react-router';
import logo from './logo.svg';
import {NavBar} from "./components/NavBar";

interface Props {
}

export const Home = ({ }: Props) => {

  // Access the navigate function to programmatically navigate
  const navigate = useNavigate();
  // Access the location object to get the current URL location
  const location = useLocation();

  return (
      <div className='homepage'>
          <NavBar/>
      </div>
  );
};
