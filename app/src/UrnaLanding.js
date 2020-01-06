import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import PatientLogin from './PatientLogin';
import DoctorLogin from './DoctorLogin';


export default function UrnaLanding() {
    
    return (
    <Router>
     <div class="container">
        <nav>
        <Link to="/PatientLogin">Patient Login  </Link>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <Link to="/DoctorLogin">Doctor Login  </Link>
        </nav>
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
    </div>  
    </Router>    
    )
}