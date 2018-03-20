import React, {Component} from 'react';
import { withRouter, Switch, Route } from 'react-router-dom';
import logo from './logo.svg';
import './App.css';

class App extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Switch>
                <Route exact path={'/'} component={} />
            </Switch>
        );
    }
}

export default App;
