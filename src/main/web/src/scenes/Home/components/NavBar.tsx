import React from 'react';
import {useLocation, useNavigate} from 'react-router';
import {Logo} from "./Logo";

interface Props {
}

export const NavBar = ({ }: Props) => {

  // Access the navigate function to programmatically navigate
  const navigate = useNavigate();
  // Access the location object to get the current URL location
  const location = useLocation();

  return (
      <div className='nav-bar'>
        <Logo/>
      </div>
  );
};
