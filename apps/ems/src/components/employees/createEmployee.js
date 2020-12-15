import React, { useState } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import { MDBInput, MDBBtn } from "mdbreact";
import '../css/styles.css';
import httpService from '../../services/httpService';

function CreateEmployee() 
{
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [minutesWorked, setMinutesWorked] = useState('');

    const updateInfo = (type, text) => 
    {
        if (type === 'firstName'){ setFirstName(text); } 
        else if (type === 'lastName'){ setLastName(text); } 
        else if (type === 'phoneNumber'){ setPhoneNumber(text); } 
        else if (type === 'minutesWorked') { setMinutesWorked(text); }
    }

    const getInMin = (hoursWorked) => 
    {
        return hoursWorked * 60; 
    }

    const create = () => 
    {
        if(firstName==='')
        {
            alert(`You are missing First Name`)
            return;
        } 
        else if (lastName==='') 
        {
            alert(`You are missing Last Name`)
            return;
        } 
        else if (phoneNumber==='') 
        {
            alert(`You are missing Phone Number`)
            return;
        } 
        else if (minutesWorked==='') 
        {
            alert(`You are missing Weekly Workhours`)
            return;
        }

        const newEmp = 
        {
            firstName: firstName,
            lastName: lastName,
            phoneNumber: phoneNumber,
            minutesWorked: getInMin(minutesWorked) 
        }

        httpService
            .post("/employees",newEmp)
            .then((response) => 
            {
                console.log(response)
                console.log('emplyee added: ' + firstName + ' ' + lastName)
                //window.location.reload(false);  Can be used to reload the page after submitting a new employee
            })
            .catch( (e) => { console.log(e) });
    }

    return (
        <div className="createPage" data-testid="createEmployee">
            <Container>
                <Row style={{paddingTop: '7%'}}>
                    <Col xs={0} md={2} lg={3}></Col>
                    <Col xs={12} md={8} lg={6}>
                        <h1 style={{color:"black"}}>Add Employee</h1><br/>
                        <MDBInput label="First Name" 
                            onChange={e => updateInfo('firstName', e.target.value)} value={firstName}/>
                        <MDBInput label="Last Name" 
                            onChange={e => updateInfo('lastName', e.target.value)} value={lastName} />
                        <MDBInput label="Phone Number" type="number" id="phoneInput"
                            onChange={e => updateInfo('phoneNumber', e.target.value)} value={phoneNumber} />
                        <MDBInput label="Weekly Workhours" type="number" step="0.25" data-testid='getHoursWorkedInMinutes'
                            onChange={e => updateInfo('minutesWorked', e.target.value)} value={minutesWorked} />

                        <div className="text-center">
                            <MDBBtn color="dark" onClick={create}>Create Employee</MDBBtn>
                        </div>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default CreateEmployee; 