import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {Field, reduxForm, SubmissionError} from 'redux-form';
import fetch from 'isomorphic-fetch';
import {loginActions} from '../actions';
import './login.sass';
import {BASE_API_URL} from '../constants';

class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.props = props;
    }

    componentDidMount() {
        this.props.loginActions.performLogin();
    }

    componentWillReceiveProps(nextProps) {

    }

    onSubmitHandler = async (values) => {
        return fetch(`${BASE_API_URL}/me`, {
            method: 'GET',
            headers: {Authorization: 'Basic ' + btoa(values.login + ':' + values.password)},
            credentials: 'include'
        })
            .then(response => {
                if (response.ok) return response.json();
                throw new Error(response.statusText);
            })
            .then(json => this.props.userActions.login(json))
            .catch(error => {
                this.props.userActions.loginError(error);
                throw new SubmissionError({
                    password: 'Логин или пароль не подходят',
                });
            });
    }

    render() {
        const {handleSubmit} = this.props;
        return (
            <div className={'login'}>
                <form onSubmit={handleSubmit(this.onSubmitHandler)}>
                    <Field name="login" component={renderField} type="text" label={'Ваш логин'}/>
                    <Field name="password" component={renderField} type="password" label={'Пароль'}/>
                    <div className={'login-field'}>
                        <button type="submit" className={'btn btn-submit'}>Войти</button>
                    </div>
                </form>
            </div>
        );
    }
}

const renderField = ({input, label, type, meta: {touched, error, warning}}) => (
    <div className={'login-field'}>
        <label>{label}</label>
        <input {...input} placeholder={label} type={type}/>
        {touched &&
        ((error && <span className={'login-error'}>{error}</span>) ||
            (warning && <span className={'login-error'}>{warning}</span>))}
    </div>
)

const validate = values => {
    const errors = {}
    if (!values.login) {
        errors.login = 'Login is empty'
    }
    if (!values.password) {
        errors.password = 'Password is empty'
    }

    return errors
};

const mapStateToProps = (state) => ({
    user: state.user
});

const mapDispatchToProps = (dispatch) => {
    return {
        loginActions: bindActionCreators(loginActions, dispatch)
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(reduxForm({form: 'login', validate})(LoginPage));