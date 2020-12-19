import { render, cleanup } from '@testing-library/react';
import {act, renderHook} from '@testing-library/react-hooks'
import React from 'react';
import ReactDOM from 'react-dom'; 

import '@testing-library/jest-dom/extend-expect.js';
import renderer from 'react-test-renderer';
import CreateEmployee from '../createEmployee';

afterEach(cleanup); 
// Cleans up after each test. If this is not added
// it runs all tests at the same time. This can create
// several conflicts when it renders multiple components at once 

it('renders without creashing', () => {
    const div = document.createElement("div");
    ReactDOM.render(<CreateEmployee />, div);
}) 

it('matches snapshot', () => {
    const tree = renderer.create(<CreateEmployee />).toJSON(); 
    expect(tree).toMatchSnapshot()
})

it('renders createEmployee correctly', () => {
    const { getByTestId } = render(<CreateEmployee />);
    expect(getByTestId("createEmployee"))
        .toHaveTextContent("Create Employee");
}) 

// describe('input', () => 
// {
//     it('Converts hours into minutes properly', () => 
//     {
//         const {result} = renderHook(CreateEmployee) 

//         act(() => 
//         {
//             result.current.updateInfo('minutesWorked', 3.5)
//         })

//         expect(result.current.firstName).toBe(210)
//     })
// })

