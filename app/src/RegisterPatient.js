import React, { Component } from "react";
import axios from "axios";
import Input from "./Input";
import TextArea from "./TextArea";
import appContext from './appContext';
import { useHistory } from "react-router-dom";


const RegisterPatient = () => {
	const {loggedIn, setLoggedIn} = React.useContext(appContext);
	const [email, setEmail] = React.useState('');
	const [password, setPassword] = React.useState('');
	const [passwordConfirmation, setPasswordConfirmation] = React.useState('');
	const [firstName, setFirstName] = React.useState('');
	const [lastName, setLastName] = React.useState('');
	const [middleName, setMiddleName] = React.useState('');
	var [fullName, setFullName] = React.useState('');
	const [qualifications, setQualifications] = React.useState('');
	const [languageSpoken, setLanguageSpoken] = React.useState('');
	const [phone, setPhone] = React.useState('');
	const [mobile, setMobile] = React.useState('');
	const [address, setAddress] = React.useState('');
	const [dob, setDob] = React.useState('');
	const [registrationErrors, setRegistrationErrors] = React.useState('');
	const history = useHistory();



	const submit = async (event) => {
	    fullName = firstName + ' ' + middleName + ' '+ lastName;
	    let origin;

	    if (!window.location.origin) {
	      origin = window.location.protocol + "//" + window.location.hostname + 
	         (window.location.port ? ':' + window.location.port: '');
	    }
	    origin = window.location.origin;
	    
    axios
      .post(
    	origin+"/rest/urna/patients/patient",
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
        if (response.data != null) {
            alert("Profile created , you will now be logged in.");
           window.$isLoggedin = 'true';
           this.props.history.push("/UrnaLandingSecuredPatient");
        }
      })
      .catch(error => {
        console.log("registration error", error);
      });
    event.preventDefault();
  }

  
    return (
      <div>
        <h1> Patient Registration.</h1>
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

          <input
          type="password"
          name="passwordConfirmation"
          placeholder="Password confirmation"
          value={passwordConfirmation}
          onChange={({target}) => setPasswordConfirmation(target.value)}
          required
         />
        
          <Input
          inputType={"text"}
          title={"First Name"}
          name={"firstName"}
          value={firstName}
          placeholder={"Enter your first name"}
          handleChange={({target}) => setFirstName(target.value)}
        required
        />
        
         <Input
          inputType={"text"}
          title={"Middle Name"}
          name={"middleName"}
          value={middleName}
          placeholder={"Enter your middle name"}
          handleChange={({target}) => setMiddleName(target.value)}
        />
        
         <Input
          inputType={"text"}
          title={"Last Name"}
          name={"lastName"}
          value={lastName}
          placeholder={"Enter your last name"}
          handleChange={({target}) => setLastName(target.value)}
        required
        />

          <Input
          inputType={"text"}
          title={"Qualifications"}
          name={"qualifications"}
          value={qualifications}
          placeholder={"Enter your qualifications"}
          handleChange={({target}) => setQualifications(target.value)}
        required
        />
        
          <Input
          inputType={"text"}
          title={"Languages Spoken"}
          name={"languageSpoken"}
          value={languageSpoken}
          placeholder={"Languages spoken (separated by commas)"}
          handleChange={({target}) => setLanguageSpoken(target.value)}
        />
        
         <Input
          inputType={"number"}
          title={"Phone"}
          name={"phone"}
          value={phone}
          placeholder={"Work Phone"}
          handleChange={({target}) => setPhone(target.value)}
        />
        
          <Input
          inputType={"number"}
          title={"Mobile"}
          name={"mobile"}
          value={mobile}
          placeholder={"Cell Phone"}
          handleChange={({target}) => setMobile(target.value)}
        required
        />
        
        <TextArea
          title={"Address"}
          rows={10}
          value={address}
          name={"address"}
          handleChange={({target}) => setAddress(target.value)}
          placeholder={"Enter your address with city and pincode"}
        required
        />
        
        <Input
          inputType={"text"}
          title={"Date of birth"}
          name={"dob"}
          value={dob}
          placeholder={"Date of birth(dd/mm/yyyy)"}
          handleChange={({target}) => setDob(target.value)}
        required
        />
          <button type="submit">Register</button>
        <br />
        </form>
      </div>
    );
  
};
export default  RegisterPatient;