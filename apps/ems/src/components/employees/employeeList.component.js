import React from "react";
import httpService from "../../services/httpService";

export default class EmployeeList extends React.Component {
    constructor(props)
    {
        super(props);

        this.getEmployeeList = this.getEmployeeList.bind(this);

        this.state = {
            employees: [],
        };
    }

    getEmployeeList() {
        //TODO Make sure this works once Jan's API is done
        httpService
            .get("/employees")
            .then((response) => {
                console.log(response.data);
                this.setState({
                    employees: response.data,
                });
                console.log("getEmployeeList Response :");
            })
            .catch((e) => {
                console.log(e);
            });
    }

    componentDidMount() {
        this.getEmployeeList();
    }

    render() {
        const employeeList = this.state.employees.map((employee, index) => {
            return(
                <li key={index}>
                    <div>
                        <p>Employee ID : {employee.id}</p>
                        <p>{`Name : ${employee.firstName} ${employee.lastName}`}</p>
                        <p>Phone Number : {employee.phoneNumber}</p>
                        <p>Worked hours : {convertMinutesToHours(employee.minutesWorked)}</p>
                    </div>
                </li>
            );
        });

        return (
                <div className="col-md-6">
                    <h4>Employee List</h4>
                    <ul className="list-group">
                        {employeeList}
                    </ul>
                </div>
        );
    }
}

function convertMinutesToHours(minutes) {
    return parseInt(minutes/60) + ":" + minutes%60;
}