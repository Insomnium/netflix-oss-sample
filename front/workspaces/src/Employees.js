import React, {Component} from 'react';
import PropTypes from 'prop-types';

import Employee from './Employee'

class Employees extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <table border="1">
                <tbody>
                <tr>
                    {this.props.headers.map(colName =>
                        <th align="left">{colName}</th>
                    )}
                </tr>
                {this.props.employees.map((e, i) => <Employee idx={i}
                                                              id={e.id}
                                                              firstName={e.firstName}
                                                              lastName={e.lastName}
                                                              email={e.email}
                                                              workspaceId={e.workspaceId}/>)}
                </tbody>
            </table>
        );
    }
}

Employees.propTypes = {
    headers: PropTypes.array.isRequired,
    employees: PropTypes.array
};

export default Employees;