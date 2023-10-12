import React, { Component } from 'react';

import './App.css';
import { Home } from './scenes/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

class App extends Component {
  public render(): JSX.Element {
    return (
        <Router>
          <Routes>
            <Route path='/' element={<Home/>} />
          </Routes>
        </Router>
    );
  }
}

export default App;