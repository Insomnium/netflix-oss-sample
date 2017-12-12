import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Employees from './Employees';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(
    <Employees
        employees={[
            {
                id: '00001',
                firstName: 'Petr',
                lastName: 'Petrov',
                email: 'PPetrov@gmail.com',
                workspaceId: '0000000000001'
            },
            {
                id: '00002',
                firstName: 'Ivan',
                lastName: 'Ivanov',
                email: 'IIvanov@gmail.com',
                workspaceId: '0000000000002'
            },
        ]}
        headers={[
            'index', 'ID', 'Employee name', 'Email', 'Workspace ID'
        ]}
    />,
    document.getElementById('root'));
registerServiceWorker();
