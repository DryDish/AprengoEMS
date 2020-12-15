import React from "react";
import httpService from "../../services/httpService";
import { Container, Row, Col } from 'react-bootstrap';

export default class EmployeeList extends React.Component
{
    constructor(props)
    {
        super(props);

        this.getEmployeeList = this.getEmployeeList.bind(this);

        this.state = {
            employees: [],
        };
    }

    getEmployeeList()
    {
        //TODO Make sure this works once Jan's API is done
        httpService
            .get("/employees")
            .then((response) =>
            {
                console.log(response.data);
                this.setState({
                    employees: response.data,
                });
                console.log("getEmployeeList Response :");
            })
            .catch((e) =>
            {
                console.log(e);
            });
    }

    componentDidMount()
    {
        this.getEmployeeList();
    }

    render()
    {
        const employeeList = this.state.employees.map((employee, index) =>
        {
            return (
                // <li key={index}>   Sorry cris
                //     <div>
                //         <p>Employee ID : {employee.id}</p>
                //         <p>{`Name : ${employee.firstName} ${employee.lastName}`}</p>
                //         <p>Phone Number : {employee.phoneNumber}</p>
                //         <p>Worked hours : {convertMinutesToHours(employee.minutesWorked)}</p>
                //     </div>
                // </li>
                <Container>
                    <Row key={index}>
                        <Col xs={1}>{employee.id}</Col>
                        <Col>{employee.firstName} {employee.lastName}</Col>
                        <Col>{employee.phoneNumber}</Col>
                        <Col xs={2}>{convertMinutesToHours(employee.minutesWorked)}</Col>
                    </Row>
                </Container>
            );
        });

        return (
            <div className="col-" style={{width: "80%", margin: "auto"}}>
                <h3 style={{fontWeight:"bold"}}>Employee List</h3><br/>
                <Container>
                    <Row style={{fontWeight: "Bold"}}>
                        <Col xs={1}>ID</Col>
                        <Col>Full Name</Col>
                        <Col>Phone Number</Col>
                        <Col xs={2}>Hours</Col>
                    </Row>
                </Container>
                <hr></hr>
                <ul className="list-group">
                    {employeeList}
                </ul>
            </div>
        );
    }
}

function convertMinutesToHours(minutes)
{
    return parseInt(minutes / 60) + ":" + minutes % 60;
}