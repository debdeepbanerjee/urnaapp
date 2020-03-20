import React, { Component } from "react";
import axios from "axios";
import Input from "./Input";
import TextArea from "./TextArea";
import appContext from './appContext';

export default function RegisterDoctor(){
	const {loggedIn, setLoggedIn} = React.useContext(appContext);
	const [email, setEmail] = React.useState('');
	const [password, setPassword] = React.useState('');
	const [password, setPasswordConfirmation] = React.useState('');
	const [firstName, setFirstName] = React.useState('');
	const [lastName, setLastName] = React.useState('');
	const [middleName, setMiddleName] = React.useState('');
	const [fullName, setFullName] = React.useState('');
	const [speciality, setSpeciality] = React.useState('');
	const [qualifications, setQualifications] = React.useState('');
	const [practice, setPractice] = React.useState('');
	const [specializations, setSpecializations] = React.useState('');
	const [languageSpoken, setLanguageSpoken] = React.useState('');
	const [phone, setPhone] = React.useState('');
	const [mobile, setMobile] = React.useState('');
	const [address, setAddress] = React.useState('');
	const [dob, setDob] = React.useState('');
	const [registrationErrors, setRegistrationErrors] = React.useState('');
 
	const submit = handleSubmit(event) {
	    event.preventDefault();
	    fullName = firstName + ' ' + middleName + ' '+ lastName;
	    var { email, password, passwordConfirmation,firstName,lastName,middleName,fullName,speciality,qualifications,practice,specializations,languageSpoken,phone,mobile,address,dob } = this.state;
	    let origin;

	    if (!window.location.origin) {
	      origin = window.location.protocol + "//" + window.location.hostname + 
	         (window.location.port ? ':' + window.location.port: '');
	    }
	    origin = window.location.origin;
	    axios
	      .post(
	        origin+"/rest/urna/doctors/doctor",
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
	        "practice": practice,
	        "qualifications": qualifications,
	        "secretPasscode": password,
	        "speciality": speciality,
	        "specializations": specializations
	        }
	      )
	      .then(response => {
	        if (response.data != null) {
	           alert("Profile created , you will now be logged in.");
	           
	           window.$isLoggedin = 'true';
	       	   setLoggedIn(true);

	           this.props.history.push("/UrnaLandingSecuredDoctor");
	        }
	      })
	      .catch(error => {
	        alert("Cannot create the profile ,may be duplicate username/email or mobile or the site is unavailable. Please change the username and try again.");
	        console.log("registration error", error);
	      });
	  }


    return (
      <div>
        <h1> Register as a Doctor.</h1>
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
          title={"Practice"}
          name={"practice"}
          value={practice}
          placeholder={"Enter your current practice details(eg: skin specialist)"}
          handleChange={({target}) => setPractice(target.value)}
        />
        
         <Input
          inputType={"text"}
          title={"Specializations"}
          name={"specializations"}
          value={specializations}
          placeholder={"Enter your current specialization area or areas. (comma separated eg: neurology)"}
          handleChange={({target}) => setSpecializations(target.value)}
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
export default  RegisterDoctor;