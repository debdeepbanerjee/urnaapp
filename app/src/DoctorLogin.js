import React, { Component } from "react";
import axios from "axios";
import UrnaLandingSecuredDoctor from './UrnaLandingSecuredDoctor';

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

    axios
      .post(
        "http://localhost:8080/rest/urna/login/doctor/email",
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

}