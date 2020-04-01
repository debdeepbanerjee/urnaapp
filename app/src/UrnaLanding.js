import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import PatientLogin from './PatientLogin';
import DoctorLogin from './DoctorLogin';
import RegisterDoctor from './RegisterDoctor';
import RegisterPatient from './RegisterPatient';
import UrnaLandingSecuredDoctor from './UrnaLandingSecuredDoctor';
import UrnaLandingSecuredPatient from './UrnaLandingSecuredPatient';
import appContext from './appContext';
window.$isLoggedin = false;
window.$pid="";
	

export default function UrnaLanding() {
	const {loggedIn, setLoggedIn} = React.useContext(appContext);
	
    // nav only exists for logged out users
    const nav = loggedIn ? (null) : (<nav style={{marginBottom: '10px'}}>
        <Link to="/PatientLogin">Patient Login  </Link>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <Link to="/DoctorLogin">Doctor Login  </Link>       
        &nbsp;&nbsp;&nbsp;&nbsp;
        <Link to="/RegisterDoctor">Register as a Doctor </Link>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <Link to="/RegisterPatient">Patient Registration  </Link>
    </nav>);

    return (<Router>
	    <div class="container"> 
	
	    {nav}
	    
	    {/*<button onClick={() => { setLoggedIn(true); }}>Remove Nav</button>*/}
	
	    <Route 
	    path="/PatientLogin"
	    	component = {PatientLogin}
	    exact
	    />
	    <Route 
	    path="/DoctorLogin"
	    	component = {DoctorLogin}
	    exact
	    />
	    <Route 
	    path="/RegisterDoctor"
	    	component = {RegisterDoctor}
	    exact
	    />
	    <Route 
	    path="/RegisterPatient"
	    	component = {RegisterPatient}
	    exact
	    />
	    <Route 
	    path="/UrnaLandingSecuredDoctor"
	    	component = {UrnaLandingSecuredDoctor}
	    exact
	    />
	    <Route 
	    path="/UrnaLandingSecuredPatient"
	    	component = {UrnaLandingSecuredPatient}
	    exact
	    />
	    </div>  
    </Router>);
}