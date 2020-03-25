import React, { Component } from "react";
import axios from "axios";
import UrnaLandingSecuredPatient from './UrnaLandingSecuredPatient';
import {logout} from './GlobalFunctions';
import appContext from './appContext';
import { useHistory } from "react-router-dom";

const PatientLogin = () => {
	const [email, setEmail] = React.useState('');
	const [password, setPassword] = React.useState('');
	const [loginErrors, setLoginErrors] = React.useState('');
	const {loggedIn, setLoggedIn} = React.useContext(appContext);
	const history = useHistory();

	const submit = async (event) => {
		event.preventDefault();
		
		let origin;

	    if (!window.location.origin) {
	      origin = window.location.protocol + "//" + window.location.hostname + 
	         (window.location.port ? ':' + window.location.port: '');
	    }
	    origin = window.location.origin;
	    
	    try {
		    const {data} = await axios.post(
		    		origin+"/rest/urna/login/patient/email",
		            {
		    			"email": email,
		    			"secretPasscode": password
		            }
		      ).then();
		    
		    if (data != null ) {
	        	setLoggedIn(true);
	        	history.push("/UrnaLandingSecuredDoctor");
	           // return <Redirect to='/UrnaLandingSecuredDoctor' />
	        }
	        else {
	        	throw new Error('login failed');
	        }
	    } catch (error) {
	        alert("Cannot login.Incorrect credentials or the site may be unavailable.");
	        console.log("login error", error);
	    }
	};
	
    return (
    	      <div>
    	        <h1> Doctor Login.</h1>
    	        <form onSubmit={submit}>
    	          <input
    	            type="email"
    	            name="email"
    	            placeholder="Email"
    	            value={email}
    	            onChange={({target}) => setEmail(target.value)}
    	            required
    	          />

    	          <input
    	            type="password"
    	            name="password"
    	            placeholder="Password"
    	            value={password}
    	            onChange={({target}) => setPassword(target.value)}
    	            required
    	          />

    	          <button type="submit">Login</button>
    	        </form>
    	      </div>
    	    );
};

export default PatientLogin;
