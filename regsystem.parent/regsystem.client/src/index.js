import React from 'react';
import ReactDOM from 'react-dom';
import { Route, Redirect } from 'react-router-dom';
import { Router, Switch } from 'react-router-dom';
import configureStore from "./store/conigureStore";
import {Provider} from 'react-redux';
import App from './components/App';
import Login from './components/Login';
import Register from './components/Register'

import {history} from "./helpers/history";
import {browserHistory} from 'react-router'
import RegisterResponse from "./components/RegisterResponse";


// sendMessage({sourceAddress: 'daniyar', destinationAddress: '87028715515', smsText: 'I love you!'})(store.dispatch);
const store = configureStore();

ReactDOM.render(
    <Provider store={store}>

        <Router history={history}>
            <div>
                <Route path="/login" component={Login}/>
                <Route path="/register" component={Register}/>
                <Route path="/register-response" component={RegisterResponse}/>

                <Route path="/**" render={() => (
                    !localStorage.getItem('user') ? (
                        <Redirect to="/login"/>
                    ) : (
                        <App/>
                    )
                )}/>
            </div>
        </Router>
    </Provider>
    , document.getElementById('root'));



// function smth(a) {
//   return function smth(action) {
//     action(a);
//   }
// }



// const smth = (a) => (action) => action(a);
// smth(23)(console.log);
// console.log(console.log);




