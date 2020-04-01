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

        try {
            const {data} = await axios.post("/rest/urna/login/patient/email",
                {
                    email,
                    secretPasscode: password
                }
            );

            if (data !== null) {
                setLoggedIn(true);
                window.$pid= response.data.id;
                history.push("/UrnaLandingSecuredPatient");
            }
            else {
                alert("Cannot login.Incorrect credentials or the site may be unavailable.");
                console.error("login error");
            }
        } catch (error) {
            alert("PatientLogin.js error occurred: " + JSON.stringify(error));
            console.error("login error", error);
        }
	};

    return (
    	      <div>
    	        <h1> Patient Login.</h1>
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
