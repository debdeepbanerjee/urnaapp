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
window.$isLoggedin = 'false'
export default function UrnaLanding() {
     if(window.$isLoggedin == 'false') {
    return (
    <Router>
     <div class="container"> 
        
        <nav>
        <Link to="/PatientLogin">Patient Login  </Link>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <Link to="/DoctorLogin">Doctor Login  </Link>       
        &nbsp;&nbsp;&nbsp;&nbsp;
        <Link to="/RegisterDoctor">Register as a Doctor </Link>
         &nbsp;&nbsp;&nbsp;&nbsp;
        <Link to="/RegisterPatient">Patient Registration  </Link>
        </nav>
        <br />
       
        
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
    </div>  
    </Router>    
    )
     } else { return (
         
          <Router>
     <div class="container"> 
       
        
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
    </div>  
    </Router>    
     )
    }
}