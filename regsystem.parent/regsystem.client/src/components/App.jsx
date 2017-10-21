import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './../style/app.scss';

import 'bootstrap/dist/js/bootstrap';

export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            add: false,
            students: [
                {
                    "id": "1",
                    "name": "Daniyar",
                    "surname": "Zhadyrassyn",
                    "patronymic": "Temirbekovich",
                    "group": "C"
                },
                {
                    "id": "2",
                    "name": "Daniyar",
                    "surname": "Zhadyrassyn",
                    "patronymic": "Temirbekovich",
                    "group": "C"
                },
                {
                    "id": "3",
                    "name": "Daniyar",
                    "surname": "Zhadyrassyn",
                    "patronymic": "Temirbekovich",
                    "group": "C"
                }
            ]
        };

        this.handleClick = this.handleClick.bind(this);
        this.handlePopupSave = this.handlePopupSave.bind(this);
    }

    handleClick(mode) {
        if(mode === "add") {
            this.setState({add: true}, function () {
                console.log("add: " + this.state.add);
            });
        } else {
            this.setState({add: false}, function () {
                console.log("add: " + this.state.add);
            });
        }
        // if(mode === "add") {
        //     this.setState({ add:true });
        // } else {
        //     this.setState({ add: false });
        // }

    }

    handlePopupSave(student) {
        //!!
        student.id = "10";
        console.log("popup save");
        let students = this.state.students.slice(0);
        students.push(student);

        this.setState({students:students});

    }

    render() {
        const mode = this.state.add;
        const students = this.state.students;
        return (
            <div>
                <Popup value={mode} onClick={this.handlePopupSave}/>
                <div className="container">
                    <div className="row">
                        <div className="col-10">
                            <Table students={students}/>
                        </div>
                        <div className="col-2">
                            <SideBar onClick={this.handleClick}/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

class SideBar extends Component {
    constructor(props) {
        super(props);

        this.handleAddClick = this.handleAddClick.bind(this);
        this.handleEditClick = this.handleEditClick.bind(this);
    }

    handleAddClick(event) {
        this.props.onClick("add");
    }

    handleEditClick(event) {
        this.props.onClick("edit");
        console.log("edit");
    }

    render() {
        return (
            <div className="btn-group-vertical">
                <button type="button" className="btn btn-primary" data-toggle="modal" data-target="#exampleModal" onClick={this.handleAddClick}>+</button>
                <button type="button" className="btn btn-info" data-toggle="modal" data-target="#exampleModal" onClick={this.handleEditClick}>...</button>
                <button type="button" className="btn btn-danger">-</button>
            </div>
        );
    }
}

class Popup extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: "",
            surname: "",
            patronymic: "",
            group: ""
        };

        this.handleSave = this.handleSave.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleSurnameChange = this.handleSurnameChange.bind(this);
        this.handlePatronymicChange = this.handlePatronymicChange.bind(this);
        this.handleGroupChange = this.handleGroupChange.bind(this);
    }

    handleSave() {
        console.log("save");
        const student = {
            name: this.state.name,
            surname: this.state.surname,
            patronymic: this.state.patronymic,
            group: this.state.group
        };
        this.props.onClick(student);
    }

    handleNameChange(event) {
        this.setState({name: event.target.value});
    }

    handleSurnameChange(event) {
        this.setState({surname: event.target.value});
    }

    handlePatronymicChange(event) {
        this.setState({patronymic: event.target.value});
    }

    handleGroupChange(event) {
        this.setState({group: event.target.value});
    }

    render() {
        const add = this.props.value;
        return (
            <div className="modal fade" id="exampleModal" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog modal-lg" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel">{add ? "Create a new student" : "Edit current student"}</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            <div className="container-fluid">
                                <form>
                                    <div className="form-group row">
                                        <label className="col-form-label col-sm-2" htmlFor="name">Name</label>
                                        <div className="col-sm-10">
                                            <input type="text" className="form-control" id="name" placeholder="Name" onChange={this.handleNameChange}/>
                                        </div>
                                    </div>
                                    <div className="form-group row">
                                        <label className="col-form-label col-sm-2" htmlFor="surname">Surname</label>
                                        <div className="col-sm-10">
                                            <input type="text" className="form-control" id="surname" placeholder="Surname" onChange={this.handleSurnameChange}/>
                                        </div>
                                    </div>
                                    <div className="form-group row">
                                        <label className="col-form-label col-sm-2" htmlFor="patronymic">Patronymic</label>
                                        <div className="col-sm-10">
                                            <input type="text" className="form-control" id="patronymic" placeholder="Patronymic" onChange={this.handlePatronymicChange}/>
                                        </div>
                                    </div>
                                    <div className="form-group row">
                                        <label className="col-form-label col-sm-2" htmlFor="group">Group</label>
                                        <div className="col-sm-2">
                                            <select className="form-control" onChange={this.handleGroupChange}>
                                                <option value="0">A</option>
                                                <option value="1">B</option>
                                                <option value="2">C</option>
                                                <option value="3">D</option>
                                            </select>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary" onClick={this.handleSave}>Save</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


class Table extends Component {
    render() {
        const items = this.props.students;
        const rows = items.map((student) => <StudentRowItem key={student.id} value={student}/>);
        return (
            <table className="table table-striped table-bordered table-hover table-responsive">
                <thead className="thead-inverse">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Patronymic</th>
                        <th>Group</th>
                    </tr>
                </thead>
                <tbody>
                    {rows}
                </tbody>

            </table>
        );
    }
}

class StudentRowItem extends Component{
    constructor(props) {
        super(props);
    }
    render() {
        const id = this.props.value.id;
        const name = this.props.value.name;
        const surname = this.props.value.surname;
        const patronymic = this.props.value.patronymic;
        const group = this.props.value.group;

        return (
          <tr>
            <td>{id}</td>
            <td>{name}</td>
            <td>{surname}</td>
            <td>{patronymic}</td>
            <td>{group}</td>
          </tr>
        );
    }
}

const students = [
    {
        "id": "1",
        "name": "Daniyar",
        "surname": "Zhadyrassyn",
        "patronymic": "Temirbekovich",
        "group": "C"
    },
    {
        "id": "2",
        "name": "Daniyar",
        "surname": "Zhadyrassyn",
        "patronymic": "Temirbekovich",
        "group": "C"
    },
    {
        "id": "3",
        "name": "Daniyar",
        "surname": "Zhadyrassyn",
        "patronymic": "Temirbekovich",
        "group": "C"
    }
];

