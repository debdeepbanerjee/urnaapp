import React, { Component } from "react";
import axios from "axios";
import UrnaLanding from './UrnaLanding';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useHistory,
} from "react-router-dom";
import appContext from './appContext';

export function useLogout() {
    let history = useHistory();
    const {setLoggedIn} = React.useContext(appContext);

    // return wrapper function for hook consumer
    return () => logout(history, setLoggedIn);
}

export function logout(history, setLoggedIn) {
     let origin;

    if (!window.location.origin) {
      origin = window.location.protocol + "//" + window.location.hostname + 
         (window.location.port ? ':' + window.location.port: '');
    }
    origin = window.location.origin;

      return axios
      .get(
        origin+"/rest/urna/logout/logout")
      .then(response => {
        if (response.data != null ) {
        	setLoggedIn(false);
            window.$isLoggedin = 'false';
            history.push('/UrnaLanding');
        }
      })
      .catch(error => {
        console.log("login error", error);
      });
}