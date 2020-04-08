import React from 'react';
import { Container } from '@material-ui/core';
import UrnaLanding from './UrnaLanding';
import appContext from './appContext';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {
	const [loggedIn, setLoggedIn] = React.useState(false); // this.state = { loggedIn: false }
	const {Provider} = appContext;
	
	return (<Provider value={{loggedIn, setLoggedIn}}>
		<Container>
			<UrnaLanding/>
		</Container>
	</Provider>);
}

export default App;
