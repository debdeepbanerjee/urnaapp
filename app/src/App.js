import React from 'react';
import { Container } from '@material-ui/core';
import { ThemeProvider, createMuiTheme } from '@material-ui/core/styles';
import {blue} from '@material-ui/core/colors';
import UrnaLanding from './UrnaLanding';
import appContext from './appContext';
import 'bootstrap/dist/css/bootstrap.min.css';

const containerTheme = createMuiTheme({
    palette: {
        primary: {
        	main: '#f2f7fa'
		},
        secondary: {
            main: '#6c7a80'
        },
    },
	background1: 'linear-gradient(to bottom, rgba(0,28,51,0.6) 0%,rgba(9,24,36,0.4) 60%,rgba(15,21,26,0.4) 100%)',
	background2: 'linear-gradient(to bottom, rgba(0,102,139,1) 0%,rgba(0,150,204,1) 50%,rgba(43,164,171,1) 100%)',
    appBarHeight: '56px',
	status: {
        danger: 'orange',
    },
});

const App = () => {
	const [loggedIn, setLoggedIn] = React.useState(false); // this.state = { loggedIn: false }
	const {Provider} = appContext;
	
	return (<Provider value={{loggedIn, setLoggedIn}}>
		<Container>
			<ThemeProvider theme={containerTheme}>
				<UrnaLanding/>
			</ThemeProvider>
		</Container>
	</Provider>);
}

export default App;
