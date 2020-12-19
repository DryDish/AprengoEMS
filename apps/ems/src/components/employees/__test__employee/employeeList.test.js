import { render, cleanup } from '@testing-library/react';
import React from 'react';
import ReactDOM from 'react-dom';

import '@testing-library/jest-dom/extend-expect.js';
import renderer from 'react-test-renderer';
import EmployeeList from "../employeeList.component";

afterEach(cleanup);

it('Renders without crashing', () => {
    const div = document.createElement("div");
    ReactDOM.render(<EmployeeList />, div);
});

it('Matches the snapshot', () => {
   const employeeListSnapshot = renderer.create(<EmployeeList/>).toJSON();
   expect(employeeListSnapshot).toMatchSnapshot();
});

it('Renders Employee List correctly', () => {
   const {getByTestId} = render(<EmployeeList/>);
   expect(getByTestId("employeeList"))
       .toHaveTextContent("Employee List"
                                && "ID"
                                && "Full Name"
                                && "Phone Number"
                                && "Hours");

});