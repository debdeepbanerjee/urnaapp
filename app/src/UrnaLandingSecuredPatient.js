import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import EditProfile from './EditProfile';
import SubmitConsultation from './SubmitConsultation';
import {logout} from './GlobalFunctions';


export default function UrnaLandingSecuredPatient() {
    
    return (
     <Router>
     <div class="container">
        <nav>
        <Link to="/EditProfile">Edit Profile </Link>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <Link to="/SubmitConsultation">Submit a Consultation Request </Link>       
        &nbsp;&nbsp;&nbsp;&nbsp;
        

        </nav>
        <a href="#" onClick={logout();this.props.history.push("/UrnaLanding");}>Logout</a>

        <br />
        <Route 
        path="/EditProfile"
        component = {EditProfile}
        exact
        />
        <Route 
        path="/SubmitConsultation"
        component = {SubmitConsultation}
        exact
        />
      
    </div>  
    </Router>    
    )
}