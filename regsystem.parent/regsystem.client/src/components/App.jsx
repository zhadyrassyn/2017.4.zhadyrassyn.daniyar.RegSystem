import React from 'react';
import {connect} from 'react-redux'
import { Link } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';
import '../style/App.css';

import * as UserActionCreators from '../actions/UserActions';
import { bindActionCreators } from 'redux'

import { withRouter } from 'react-router-dom';

import AlertContainer from 'react-alert'

class App extends React.Component {
  constructor(props) {
    super(props);

    const {dispatch} = props;

    this.alertOptions = {
      offset: 40,
      position: 'top right',
      theme: 'light',
      time: 5000,
      transition: 'scale'
    }


    this.boundActionCreators = bindActionCreators(UserActionCreators, dispatch)

  }


  render() {

    return (
      <div className="container-fluid">
        <AlertContainer ref={a => this.msg = a} {...this.alertOptions} />
        <div className="row">

          <div className="col-md-2">
            <Link to="/login">Выйти</Link>
          </div>
        </div>

      </div>
    );
  }
}



const mapStateToProps = state => ({
});



export default withRouter(connect(mapStateToProps)(App));