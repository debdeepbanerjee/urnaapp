import React, { Component } from "react";
import axios from "axios";
import Input from "./Input";
import TextArea from "./TextArea";


export default class RegisterPatient extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      password_confirmation: "",
      firstName: "",
      lastName: "",
      middleName: "",
      fullName: "",
      qualifications: "",
      languageSpoken: "",
      phone: "",
      mobile: "",
      address: "",
      dob: "",    
      registrationErrors: ""
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
    fullName = firstName + ' ' + middleName + ' '+ lastName;
    const { email, password, password_confirmation,firstName,lastName,middleName,fullName,qualifications,languageSpoken,phone,mobile,address,dob } = this.state;

    axios
      .post(
        "http://localhost:8080/rest/urna/patients/patient",
       {
        "address": address,
        "dob": dob,
        "email": email,
        "firstName": firstName,
        "fullName": fullName,
        "languageSpoken": languageSpoken,
        "lastName": lastName,
        "middleName": middleName,
        "mobile": mobile,
        "phone": phone,
        "qualifications": qualifications,
        "secretPasscode": password
        }
      )
      .then(response => {
        if (response.data.status === "created") {
          this.props.handleSuccessfulAuth(response.data);
        }
      })
      .catch(error => {
        console.log("registration error", error);
      });
    event.preventDefault();
  }

  render() {
    return (
      <div>
        <h1> Patient Registration.</h1>
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

          <input
            type="password"
            name="password_confirmation"
            placeholder="Password confirmation"
            value={this.state.password_confirmation}
            onChange={this.handleChange}
            required
          />
        
        <Input
          inputType={"text"}
          title={"First Name"}
          name={"firstName"}
          value={this.state.firstName}
          placeholder={"Enter your first name"}
          handleChange={this.handleChange}
        required
        />
        
         <Input
          inputType={"text"}
          title={"Middle Name"}
          name={"middleName"}
          value={this.state.middleName}
          placeholder={"Enter your middle name"}
          handleChange={this.handleChange}
        />
        
         <Input
          inputType={"text"}
          title={"Last Name"}
          name={"lastName"}
          value={this.state.lastName}
          placeholder={"Enter your last name"}
          handleChange={this.handleChange}
        required
        />

          <Input
          inputType={"text"}
          title={"Qualifications"}
          name={"qualifications"}
          value={this.state.qualifications}
          placeholder={"Enter your qualifications"}
          handleChange={this.handleChange}
        />
        
          <Input
          inputType={"text"}
          title={"Languages Spoken"}
          name={"languageSpoken"}
          value={this.state.languageSpoken}
          placeholder={"Languages spoken (separated by commas)"}
          handleChange={this.handleChange}
        />
        
         <Input
          inputType={"number"}
          title={"Phone"}
          name={"phone"}
          value={this.state.phone}
          placeholder={"Work Phone"}
          handleChange={this.handleChange}
        />
        
          <Input
          inputType={"number"}
          title={"Mobile"}
          name={"mobile"}
          value={this.state.mobile}
          placeholder={"Cell Phone"}
          handleChange={this.handleChange}
        required
        />
        
        <TextArea
          title={"Address"}
          rows={10}
          value={this.state.address}
          name={"address"}
          handleChange={this.handleTextArea}
          placeholder={"Enter your address with city and pincode"}
         required
        />
        
        <Input
          inputType={"text"}
          title={"Date of birth"}
          name={"dob"}
          value={this.state.dob}
          placeholder={"Date of birth(dd/mm/yyyy)"}
          handleChange={this.handleChange}
        required
        />
        

          <button type="submit">Register</button>
        <br />
        </form>
      </div>
    );
  }
}