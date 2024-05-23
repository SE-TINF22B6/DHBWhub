const config = {
  // apiUrl: 'http://localhost:8080/', // For test purposes only
  apiUrl: 'http://193.196.38.232:8080/',
  //apiUrl: 'http://localhost:8080/', // For test purposes only
  apiUrl: 'https://193.196.38.232:8443/',
  googleClientId: '973066251162-r60h517iddja3k756d2f6n8sng5nn24q.apps.googleusercontent.com', // Do not push this to public repository
  tooltipMessage: "Please sign up or log in to use this feature",
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Accept': 'application/json'
  }
};

export default config;