import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import logger from 'redux-logger'
import rootReducers from '../reducers'

export default function configureStore() {
    return createStore(rootReducers, applyMiddleware(thunkMiddleware, logger));
};