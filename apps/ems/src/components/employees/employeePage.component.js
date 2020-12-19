import React from "react";
import EmployeeList from "./employeeList.component";
import CreateEmployee from "./createEmployee";

export default function EmployeePage()
{
    return (
        <>
            <CreateEmployee />
            <EmployeeList />
        </>
    );
}