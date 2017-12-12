import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Employees from './Employees';
import registerServiceWorker from './registerServiceWorker';

// ReactDOM.render(<App />, document.getElementById('root'));
ReactDOM.render(<Employees />, document.getElementById('root'));
registerServiceWorker();
