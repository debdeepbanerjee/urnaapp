import React, { Component } from "react";
import axios from "axios";
import Input from "./Input";
import TextArea from "./TextArea";


export default class EditProfile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      password_confirmation: "",
      firstName: "",
      lastName: "",
      gender: "",
      middleName: "",
      fullName: "",
      qualifications: "",
      languageSpoken: "",
      phone: "",
      mobile: "",
      address: "",
      dob: "",    
      height : "",
      weight : "",
      registrationErrors: ""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.getProfile = this.getProfile.bind(this);

  }

  getProfile() {
	  axios
      .get(
        "/rest/urna/patients/patient/"+window.$pid       
      )
      .then(response => {
        if (response.data != null) {
        	this.state.isLoading = false;
        	this.state.email = response.data.email;
        	this.state.firstName= response.data.firstName;
        	this.state.lastName= response.data.lastName;
        	this.state.gender= response.data.gender;
        	this.state.middleName= response.data.middleName;
        	this.state.qualifications= response.data.speciality;
        	this.state.languageSpoken= response.data.languageSpoken;
        	this.state.phone= response.data.phone;
        	this.state.mobile= response.data.mobile;
        	this.state.address= response.data.address;
        	this.state.dob= response.data.dob; 
        	this.state.height = response.data.height;
        	this.state.weight = response.data.weight;
        }
      })
      .catch(error => {
        console.log("registration error", error);
      });
  }
  
  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    fullName = firstName + ' ' + middleName + ' '+ lastName;
    const { email, password, password_confirmation,firstName,lastName,gender,middleName,fullName,qualifications,languageSpoken,phone,mobile,address,dob,height,weight } = this.state;

    axios
      .put(
        "/rest/urna/patients/patient",
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
        "secretPasscode": password,
        "gender" : gender,
        "height": height,
        "weight": weight
        }
      )
      .then(response => {
    	  if (response.data != null) {
          	alert("Profile updated sucessfully.");
          }
      })
      .catch(error => {
        console.log("registration error", error);
      });
    event.preventDefault();
  }

  componentDidMount() {
      this.getProfile();
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
        title={"Height in feet and inches (eg 5ft10in)"}
        name={"height"}
        value={this.state.height}
        placeholder={"Enter your last name"}
        handleChange={this.handleChange}
        required
        />
	     <Input
	      inputType={"text"}
	      title={"Weight in kgs"}
	      name={"weight"}
	      value={this.state.weight}
	      placeholder={"Enter your weight"}
	      handleChange={this.handleChange}
	      required
	     />
     
        <label>Gender
        <select value={this.state.gender} onChange={this.handleChange}>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
        </select></label>

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
          handleChange={this.handleChange}
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
        

          <button type="submit">Update</button>
        <br />
        </form>
      </div>
    );
  }
}