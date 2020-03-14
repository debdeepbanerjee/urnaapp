import React from 'react';
import UrnaLanding from './UrnaLanding';
import appContext from './appContext';

const App = () => {
	const [loggedIn, setLoggedIn] = React.useState(false); // this.state = { loggedIn: false }
	const {Provider} = appContext;
	
	return (<Provider value={{loggedIn, setLoggedIn}}>
		<UrnaLanding/>
	</Provider>);
}

export default App;
