import React, { Component } from "react";
import axios from "axios";
import UrnaLandingSecuredDoctor from './UrnaLandingSecuredDoctor';
import {logout} from './GlobalFunctions';
import appContext from './appContext';

const DoctorLogin = () => {
	const [email, setEmail] = React.useState('');
	const [password, setPassword] = React.useState('');
	const [loginErrors, setLoginErrors] = React.useState('');
	
	const submit = (event) => {
		let origin;

	    if (!window.location.origin) {
	      origin = window.location.protocol + "//" + window.location.hostname + 
	         (window.location.port ? ':' + window.location.port: '');
	    }
	    origin = window.location.origin;

	    axios
	      .post(
	        origin+"/rest/urna/login/doctor/email",
	            {
	            "email": email,
	           "secretPasscode": password
	            }
	      )
	      .then(response => {
	        if (response.data != null ) {
	            window.$isLoggedin = 'true';
	           this.props.history.push("/UrnaLandingSecuredDoctor");
	           // return <Redirect to='/UrnaLandingSecuredDoctor' />
	        }
	      })
	      .catch(error => {
	        alert("Cannot login.Incorrect credentials or the site may be unavailable.");
	        console.log("login error", error);
	      });
		event.preventDefault();
	};
	
    return (
    	      <div>
    	        <h1> Doctor Login.</h1>
    	        <form>
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

export default DoctorLogin;
/*
export default class DoctorLogin extends Component  {
    constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      loginErrors: ""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    const { email, password } = this.state;
    let origin;

    if (!window.location.origin) {
      origin = window.location.protocol + "//" + window.location.hostname + 
         (window.location.port ? ':' + window.location.port: '');
    }
    origin = window.location.origin;

    axios
      .post(
        origin+"/rest/urna/login/doctor/email",
            {
            "email": email,
           "secretPasscode": password
            }
      )
      .then(response => {
        if (response.data != null ) {
            window.$isLoggedin = 'true';
           this.props.history.push("/UrnaLandingSecuredDoctor");
           // return <Redirect to='/UrnaLandingSecuredDoctor' />
        }
      })
      .catch(error => {
        alert("Cannot login.Incorrect credentials or the site may be unavailable.");
        console.log("login error", error);
      });
    event.preventDefault();
  }

  render() {
    return (
      <div>
        <h1> Doctor Login.</h1>
        <form onSubmit={this.handleSubmit}>
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={this.state.email}
            onChange={this.handleChange}
            required
          />

          <input
            type="password"
            name="password"
            placeholder="Password"
            value={this.state.password}
            onChange={this.handleChange}
            required
          />

          <button type="submit">Login</button>
        </form>
      </div>
    );
  }

}*/