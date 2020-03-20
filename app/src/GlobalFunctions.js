import React, { Component } from "react";
import axios from "axios";
import UrnaLanding from './UrnaLanding';
import appContext from './appContext';

export function logout(history) {
	const {loggedIn, setLoggedIn} = React.useContext(appContext);
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