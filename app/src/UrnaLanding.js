import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link as RouteLink
} from "react-router-dom";
import {
    AppBar,
    Toolbar,
    Link,
    IconButton,
    Button,
    Typography,
    Grid,
    Box,
    Hidden,
    makeStyles,
} from '@material-ui/core';
import {
    AddCircleOutlined as AddIcon,
    AddToQueue as AddAppointment,
    Today as Calendar,
} from '@material-ui/icons';
import {ThemeProvider, createMuiTheme} from '@material-ui/core/styles';
import PatientLogin from './PatientLogin';
import DoctorLogin from './DoctorLogin';
import RegisterDoctor from './RegisterDoctor';
import RegisterPatient from './RegisterPatient';
import UrnaLandingSecuredDoctor from './UrnaLandingSecuredDoctor';
import UrnaLandingSecuredPatient from './UrnaLandingSecuredPatient';
import appContext from './appContext';

window.$isLoggedin = false;
window.$pid = "";

const bgStyle = (background) => ({
    background,
    opacity: 0.75,
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    position: 'absolute',
});
const homeTheme = createMuiTheme({
    palette: {
        primary: {
            main: '#ffffff',
            contrastText: 'rgba(0,150,204,1)',
        },
        secondary: {
            main: '#099bff'
        },
    },
    background1: 'linear-gradient(to bottom, rgba(0,28,51,0.6) 0%,rgba(9,24,36,0.4) 60%,rgba(15,21,26,0.4) 100%)',
    background2: 'linear-gradient(to bottom, rgb(0, 139, 94) 0%,rgba(0,150,204,1) 50%,rgb(43, 53, 171) 100%)',
    status: {
        danger: 'orange',
    },
    typography: {
        fontFamily: 'Gill Sans, sans-serif',
    },
});
const useHomeStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        backgroundImage: 'url(/home_bg.jpg)',
        backgroundSize: 'cover',
        backgroundPosition: '50% 10%',
    },
    left: {
        '& .bg': bgStyle(theme.background1),
        display: 'flex',
        position: 'relative',
    },
    right: {
        '& .bg': bgStyle(theme.background2),
        display: 'flex',
        position: 'relative',
    },
    italic: {
        fontFamily: 'Palatino, URW Palladio L, serif',
        fontStyle: 'italic',
        fontSize: '2rem',
    },
    separator: {
        margin: '20px 40px',
        borderColor: 'white',
        width: '70%',
    },
}));
const useCardStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        textAlign: 'center',
        flexGrow: 0,
    },
}));
const landingTheme = createMuiTheme({
    palette: {
        primary: {
            main: '#f2f7fa'
        },
        secondary: {
            main: '#6c7a80'
        },
    },
    appBarHeight: '56px',
    status: {
        danger: 'orange',
    },
});
const useLandingStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    grow: {
        flexGrow: 1,
    },
    title: {
        flexGrow: 1,
    },
    container: {
        marginTop: theme.appBarHeight,
        height: '100vh',
        display: 'flex',
    },
    toolbar: {
        minHeight: 'auto',
        height: theme.appBarHeight,
    },
    rightNavLinks: {
        display: 'flex',
        width: '250px',
    },
}));

const Card = ({children, ...otherProps}) => <Box flexDirection="column"
                                      flexGrow={1} alignItems="center"
                                      justifyContent="center"
                                      className={useCardStyles().root}
                                      {...otherProps}>
    {children}
</Box>;

const RightSection = () => <Box flexDirection="column" justifyContent="center" alignItems="center" style={{zIndex: 1, display: 'flex', flexGrow: 1,}}>
    <Card>
        <Typography variant="body1" color="primary" className={useHomeStyles().italic}>UrnaCare</Typography>
        <Typography variant="h3" color="primary">Primary Care</Typography>
    </Card>
    <Card>
        <Calendar color="primary" style={{ fontSize: '3.5em', marginTop:'70px' }}/>
        <Typography variant="h5" color="primary" style={{margin: '20px 0 10px 0'}}>Book an Appointment</Typography>
        <Typography variant="body1" color="primary" style={{maxWidth: '250px'}}>Remote visits are available for all appointment types.</Typography>
        <Link to="/RegisterPatient" component={RouteLink}>
            <Button color="primary" variant="contained" style={{margin:'20px'}}>Book Appointment</Button>
        </Link>
    </Card>
</Box>;

const Home = () => {
    const {root, right, left, separator,} = useHomeStyles();

    return <ThemeProvider theme={homeTheme}>
        <Grid container className={root}>
            {/*Mobile View*/}
            <Hidden mdUp>
                <Grid item xs={12} className={right}>
                    <div className="bg" />
                    <RightSection />
                </Grid>
            </Hidden>

            {/*Desktop / Tablet View*/}
            <Hidden smDown>
                <Grid item xs={3} className={left}>
                    <div className="bg" />
                    <Box flexDirection="column" justifyContent="center" alignItems="center" style={{zIndex: 1, display: 'flex', flexGrow: 1,}}>
                        <Card>
                            <Typography variant="h3" color="primary">Chat With a Doctor Now</Typography>
                            <IconButton color="secondary">
                                <AddIcon style={{ fontSize: '3.5em' }}/>
                            </IconButton>
                        </Card>
                        <hr className={separator} />
                        <Card>
                            <AddAppointment color="primary" style={{ fontSize: '3.5em', marginTop:'20px' }}/>
                            <Typography variant="h5" color="primary" style={{margin: '20px 0 10px 0'}}>New Patients</Typography>
                            <Typography variant="body1" color="primary">We are accepting new patients for Remote Visits</Typography>
                            <Link to="/RegisterPatient" component={RouteLink}>
                                <Button color="primary" variant="contained" style={{margin:'20px'}}>Register</Button>
                            </Link>
                        </Card>
                    </Box>
                </Grid>
                <Grid item xs={9} className={right}>
                    <div className="bg" />
                    <RightSection />
                </Grid>
            </Hidden>
        </Grid>
    </ThemeProvider>;
};


export default function UrnaLanding() {
    const {loggedIn, setLoggedIn} = React.useContext(appContext);
    const classes = useLandingStyles();

    return (<div className={classes.root}>
        <Router>
            <ThemeProvider theme={landingTheme}>
                <AppBar position="fixed" color="primary">
                    <Toolbar className={classes.toolbar}>
                        <Link component={RouteLink} to="/" color="secondary" className={classes.grow}>
                            <Typography variant="h6" className={classes.title}>UrnaCare</Typography>
                        </Link>

                        <Grid container spacing={3} className={classes.rightNavLinks}>
                            <Grid item xs={6}>
                                <Link component={RouteLink} to="/PatientLogin" color="secondary">
                                    <Typography noWrap={true}>Patient Portal</Typography>
                                </Link>
                            </Grid>
                            <Grid item xs={6}>
                                <Link component={RouteLink} to="/DoctorLogin" color="secondary">
                                    <Typography noWrap={true}>Doctor Portal</Typography>
                                </Link>
                            </Grid>
                        </Grid>
                    </Toolbar>
                </AppBar>
            </ThemeProvider>

            <div className={classes.container}>
                <Route exact path="/" component={Home}/>
                <Route path="/PatientLogin" component={PatientLogin}/>
                <Route path="/DoctorLogin" component={DoctorLogin}/>
                <Route path="/RegisterDoctor" component={RegisterDoctor} exact/>
                <Route path="/RegisterPatient" component={RegisterPatient} exact/>
                <Route path="/UrnaLandingSecuredDoctor" component={UrnaLandingSecuredDoctor} exact/>
                <Route path="/UrnaLandingSecuredPatient" component={UrnaLandingSecuredPatient} exact/>
            </div>
        </Router>
    </div>);
}