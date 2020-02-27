import axios from "axios";

export function logout() {
     let origin;

    if (!window.location.origin) {
      origin = window.location.protocol + "//" + window.location.hostname + 
         (window.location.port ? ':' + window.location.port: '');
    }
    origin = window.location.origin;

      axios
      .get(
        origin+"/rest/urna/logout/logout")
      .then(response => {
        if (response.data != null ) {
            window.$isLoggedin = 'false';
        }
      })
      .catch(error => {
        console.log("login error", error);
      });
}