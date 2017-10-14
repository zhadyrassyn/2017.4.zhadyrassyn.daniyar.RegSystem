import React, { Component } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component {
  render() {
    return (
        <div className="container">
          <div className="row">
            <div className="col-8">
              <Table/>
            </div>
            <div className="col">
              <SideBar/>
            </div>
          </div>
        </div>

    );
  }
}

class SideBar extends Component {
  render() {
    return (
      <p>SideBar</p>
    );
  }
}

class Table extends Component {
  render() {
    return (
      <table>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Surname</th>
          <th>Patronymic</th>
          <th>Code</th>
          <th>Gender</th>
        </tr>
        <tr>
          <td>1</td>
          <td>Daniyar</td>
          <td>Zhadyrassyn</td>
          <td>Temirbekovich</td>
          <td>5B07300</td>
          <td>Male</td>
        </tr>
      </table>
    );
  }
}

class ListPerson extends Component {

}

export default App;
