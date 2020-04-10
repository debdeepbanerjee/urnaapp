import React, {Component} from "react";
import axios from "axios";
import {
    TextField,
    Button,
    Grid,
    Box,
    FormControl,
    FormLabel,
    RadioGroup,
    FormControlLabel,
    Radio
} from '@material-ui/core';
import {makeStyles} from '@material-ui/core/styles';
import Input from "./Input";
import TextArea from "./TextArea";
import appContext from './appContext';
import {useHistory} from "react-router-dom";

const useStyles = makeStyles((theme) => ({
    root: {
        '& .MuiTextField-root': {
            width: '100%',
        }
    },
}));

const RegisterPatient = () => {
    const classes = useStyles();
    const {loggedIn, setLoggedIn} = React.useContext(appContext);
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [passwordConfirmation, setPasswordConfirmation] = React.useState('');
    const [firstName, setFirstName] = React.useState('');
    const [lastName, setLastName] = React.useState('');
    const [middleName, setMiddleName] = React.useState('');
    const [gender, setGender] = React.useState('');
    const [qualifications, setQualifications] = React.useState('');
    const [languageSpoken, setLanguageSpoken] = React.useState('');
    const [phone, setPhone] = React.useState('');
    const [mobile, setMobile] = React.useState('');
    const [address, setAddress] = React.useState('');
    const [dob, setDob] = React.useState('');
    const [height, setHeight] = React.useState('');
    const [weight, setWeight] = React.useState('');
    const [registrationErrors, setRegistrationErrors] = React.useState('');
    const history = useHistory();


    const submit = async (event) => {
        const fullName = firstName + ' ' + middleName + ' ' + lastName;
        let origin;

        if (!window.location.origin) {
            origin = window.location.protocol + "//" + window.location.hostname +
                (window.location.port ? ':' + window.location.port : '');
        }
        origin = window.location.origin;

        axios
            .post(
                origin + "/rest/urna/patients/patient",
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
                    "gender": gender,
                    "height": height,
                    "weight": weight
                }
            )
            .then(response => {
                if (response.data !== null) {
                    alert("Profile created , you will now be logged in.");
                    setLoggedIn(true);
                    window.$pid = response.data.id;
                    history.push("/UrnaLandingSecuredPatient");
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
            <form onSubmit={submit} className={classes.root}>

                <Grid container spacing={3}>
                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type="email"
                            id="email"
                            name="email"
                            label="Email"
                            value={email}
                            onChange={({target}) => setEmail(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type="password"
                            id="password"
                            name="password"
                            label="Password"
                            value={password}
                            onChange={({target}) => setPassword(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type="password"
                            id="passwordConfirmation"
                            name="passwordConfirmation"
                            label="Password confirmation"
                            value={passwordConfirmation}
                            onChange={({target}) => setPasswordConfirmation(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type={"text"}
                            label={"First Name"}
                            name={"firstName"}
                            value={firstName}
                            onChange={({target}) => setFirstName(target.value)}
                            required
                        /> </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type={"text"}
                            label={"Middle Name"}
                            name={"middleName"}
                            value={middleName}
                            onChange={({target}) => setMiddleName(target.value)}
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type={"text"}
                            label={"Last Name"}
                            name={"lastName"}
                            value={lastName}
                            onChange={({target}) => setLastName(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type={"text"}
                            label={"Height in feet and inches (eg 5ft10in)"}
                            name={"height"}
                            value={height}
                            placeholder={"Enter your height"}
                            onChange={({target}) => setHeight(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type={"text"}
                            label={"Weight (kgs.)"}
                            name={"weight"}
                            value={weight}
                            onChange={({target}) => setWeight(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type={"text"}
                            label={"Qualifications"}
                            name={"qualifications"}
                            value={qualifications}
                            onChange={({target}) => setQualifications(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type={"text"}
                            label={"Languages Spoken"}
                            name={"languageSpoken"}
                            value={languageSpoken}
                            onChange={({target}) => setLanguageSpoken(target.value)}
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type={"number"}
                            label={"Work Phone"}
                            name={"phone"}
                            value={phone}
                            onChange={({target}) => setPhone(target.value)}
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            type={"number"}
                            label={"Mobile Phone"}
                            name={"mobile"}
                            value={mobile}
                            onChange={({target}) => setMobile(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <FormControl component="fieldset">
                            <FormLabel component="legend">Gender</FormLabel>
                            <RadioGroup aria-label="gender" name="gender1" value={gender}
                                        onChange={({target}) => setGender(target.value)}>
                                <FormControlLabel value="Female" control={<Radio/>} label="Female"/>
                                <FormControlLabel value="Male" control={<Radio/>} label="Male"/>
                                <FormControlLabel value="Other" control={<Radio/>} label="Other"/>
                            </RadioGroup>
                        </FormControl>
                    </Grid>

                    <Grid item xs={12} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            multiline
                            rows="4"
                            label={"Address"}
                            value={address}
                            name={"address"}
                            id="address"
                            onChange={({target}) => setAddress(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={6} sm={6} md={4}>
                        <TextField
                            variant="outlined"
                            label="Date of Birth"
                            placeholder="DD/MM/YYYY"
                            id="dob"
                            name="dob"
                            value={dob}
                            onChange={({target}) => setDob(target.value)}
                            required
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <Box display="flex" justifyContent="center">
                            <Button variant="contained" color="primary" type="submit">Register</Button>
                        </Box>
                    </Grid>
                </Grid>
            </form>
        </div>
    );

};
export default RegisterPatient;