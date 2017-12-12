import React, { Component } from 'react';

import Employee from './Employee'

class Employees extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <table border="1">
                <tr>
                    {this.props.headers.map(colName =>
                        <th align="left">{colName}</th>
                    )}
                </tr>
                <tbody>
                    {this.props.employees.map((e, i) => <Employee idx={i} employee={e}/>)}
                </tbody>
            </table>
        );
    }
}

export default Employees;