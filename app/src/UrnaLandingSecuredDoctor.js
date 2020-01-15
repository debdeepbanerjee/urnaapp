import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import EditProfileDoctor from './EditProfileDoctor';
import RespondToConsultation from './RespondToConsultation';


export default function UrnaLandingSecuredDoctor() {
    
    return (
    <Router>
     <div class="container">
        <nav>
        <Link to="/EditProfileDoctor">Edit Profile </Link>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <Link to="/RespondToConsultation">Respond To Consultation </Link>       
        &nbsp;&nbsp;&nbsp;&nbsp;
      
        </nav>
        <br />
        <Route 
        path="/EditProfileDoctor"
        component = {EditProfileDoctor}
        exact
        />
        <Route 
        path="/RespondToConsultation"
        component = {RespondToConsultation}
        exact
        />
      
    </div>  
    </Router>    
    )
}