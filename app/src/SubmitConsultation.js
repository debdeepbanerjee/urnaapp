import React, { Component } from "react";
import axios from "axios";
import Input from "./Input";
import TextArea from "./TextArea";


export default class RegisterPatient extends Component {
  constructor(props) {
    super(props);

    this.state = {
     	consultationFor : "",
	 	speciality: "",
		healthIssue: "",
	    durationOfHealthIssue: "",
	    additionalQuery: "",
        consultationErrors: ""
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
    const { consultationFor, speciality, healthIssue,durationOfHealthIssue,additionalQuery } = this.state;

    axios
      .post(
        "http://localhost:8080/rest/urna/consultation/consultation",
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
        <h1> Submit yor consultation.</h1>
        <form onSubmit={this.handleSubmit}>
         
        
        
        <Input
          inputType={"text"}
          title={"Consultation For"}
          name={"consultationFor"}
          value={this.state.consultationFor}
          placeholder={"Consultation For(eg:self,spouse,etc)"}
          handleChange={this.handleChange}
        required
        />
        
        <Input
          inputType={"text"}
          title={"Speciality of doctor requested"}
          name={"speciality"}
          value={this.state.speciality}
          placeholder={"Speciality of doctor requested(leave blank if not sure)"}
          handleChange={this.handleChange}
        required
        />
        
        <TextArea
          title={"Health issue}
          rows={10}
          value={this.state.healthIssue}
          name={"healthIssue"}
          handleChange={this.handleTextArea}
          placeholder={"Health issue"}
         required
        />
        
         <Input
          inputType={"text"}
          title={"Duration Of Issue"}
          name={"durationOfHealthIssue"}
          value={this.state.durationOfHealthIssue}
          placeholder={"Enter the Duration Of Issue in (days or months or years)"}
          handleChange={this.handleChange}
        required
        />

          
        <TextArea
          title={"Additional query"}
          rows={10}
          value={this.state.additionalQuery}
          name={"additionalQuery"}
          handleChange={this.handleTextArea}
          placeholder={"Enter any other Additional query"}
         
        />
        

          <button type="submit">Submit yor consultation request</button>
        <br />
        </form>
      </div>
    );
  }
}