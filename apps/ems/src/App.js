import './App.css';
import EmployeeList from "./components/employees/employeeList.component";
import CreateEmployee from './components/employees/createEmployee';
import React from "react";

function App()
{
    return (
        <div className="App">
            <CreateEmployee />
            <EmployeeList />
        </div>
    );
}

export default App;
