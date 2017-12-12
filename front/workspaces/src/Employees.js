import React, { Component } from 'react';

import Employee from './Employee'

class Employees extends Component {
    // renderEmployee(e) {
    //     return <Employee value={e} />
    // }
    constructor() {
        super();
        this.state = {
            employees: [
                { id: '00001', firstName: 'Petr', lastName: 'Petrov', email: 'PPetrov@gmail.com', workspaceId: '0000000000001' },
                { id: '00002', firstName: 'Ivan', lastName: 'Ivanov', email: 'IIvanov@gmail.com', workspaceId: '0000000000002' },
            ]
        }
    }
    render() {
        return (
            <table>
                <tbody>
                    {this.state.employees.map((e, i) => <Employee employee={e}/>)}
                </tbody>
            </table>
        );
    }
}

export default Employees;