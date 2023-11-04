import React, { Component } from 'react';

import './App.css';
import {Home} from './scenes/Home';
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import {Friends} from "./scenes/Friends";
import {FAQ} from "./scenes/FAQ";
import {Post} from "./scenes/Post";
import {Contact} from "./scenes/Contact";
import {Calendar} from "./scenes/Calendar";
import {PrivacyPolicy} from "./scenes/PrivacyPolicy";

class App extends Component {

  public render(): JSX.Element {
    return (
        <Router>
          <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/friends' element={<Friends/>}/>
            <Route path='/faq' element={<FAQ/>}/>
            <Route path='/post' element={<Post/>}/>
            <Route path='/contact' element={<Contact/>}/>
            <Route path='/calendar' element={<Calendar/>}/>
            <Route path='/privacy-policy' element={<PrivacyPolicy/>}/>
          </Routes>
        </Router>
    );
  }
}

export default App;