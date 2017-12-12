import React, {Component} from 'react';

class Employee extends Component {
    render() {
        return (
            <tr>
                <td className="id">{this.props.employee.id}</td>
                <td className="name">{this.props.employee.firstName + ' ' + this.props.employee.lastName}</td>
                <td className="email">{this.props.employee.email}</td>
                <td className="workspaceId">{this.props.employee.workspaceId}</td>
            </tr>
        );
    }
}

export default Employee;