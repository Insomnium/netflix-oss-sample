import React, {Component} from 'react';
import PropTypes from 'prop-types';

class Employee extends Component {
    constructor(props) {
        super(props)
    }
    render() {
        return (
            <tr>
                <td className="index">{this.props.idx}</td>
                <td className="id">{this.props.id}</td>
                <td className="name">{this.props.firstName + ' ' + this.props.lastName}</td>
                <td className="email">{this.props.email}</td>
                <td className="workspaceId">{this.props.workspaceId}</td>
            </tr>
        );
    }
}

Employee.propTypes = {
    idx: PropTypes.number,
    id: PropTypes.string,
    firstName: PropTypes.string,
    lastName: PropTypes.string,
    email: PropTypes.string,
    workspaceId: PropTypes.string
};

export default Employee;