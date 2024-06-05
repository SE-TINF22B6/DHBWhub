import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import { Home } from './scenes/Home';
import { Friends } from './scenes/Friends';
import { FAQ } from './scenes/FAQ';
import { Post } from './scenes/Post';
import { Contact } from './scenes/Contact';
import { CalendarPage } from './scenes/Calendar';
import { PrivacyPolicy } from './scenes/PrivacyPolicy';
import { Imprint } from './scenes/Imprint';
import { ProfilePage } from './scenes/Profile';
import { Search } from './scenes/Search';
import { Event } from './scenes/Event';
// import { TagOverview } from './scenes/Tag';
import { User } from './scenes/User';
import { PageNotFound } from './scenes/PageNotFound';
import config from "./config/config";
import {GoogleOAuthProvider} from "@react-oauth/google";
import {TermsOfService} from "./scenes/TermsOfService";

class App extends Component {
  render() {
    return (
        <GoogleOAuthProvider clientId={config.googleClientId}>
          <Router>
            <Routes>
              <Route path="/" element={<Home/>}/>
              <Route path="*" element={<PageNotFound/>}/>

              <Route path="/calendar" element={<CalendarPage/>}/>
              <Route path="/contact" element={<Contact/>}/>
              <Route path="/faq" element={<FAQ/>}/>
              <Route path="/friends" element={<Friends/>}/>
              <Route path="/event" element={<Event/>} />
              <Route path="/post" element={<Post/>}/>
              <Route path="/privacy-policy" element={<PrivacyPolicy/>}/>
              <Route path="/imprint" element={<Imprint/>}/>
              <Route path="/profile" element={<ProfilePage/>}/>
              <Route path="/search" element={<Search/>}/>
              {/*<Route path="/tag" element={<TagOverview/>}/>*/}
              <Route path="/terms-of-service" element={<TermsOfService/>}/>
              <Route path="/user" element={<User/>}/>
            </Routes>
          </Router>
        </GoogleOAuthProvider>
    );
  }
}

export default App;