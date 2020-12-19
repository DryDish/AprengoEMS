import { render, cleanup } from '@testing-library/react';
import React from 'react';
import ReactDOM from 'react-dom';

import '@testing-library/jest-dom/extend-expect.js';
import renderer from 'react-test-renderer';
import WorkplaceList from "../WorkplaceList.component";

afterEach(cleanup);

it('Renders without crashing', () => {
    const div = document.createElement("div");
    ReactDOM.render(<WorkplaceList />, div);
});

it('Matches the snapshot', () => {
    const workplaceListSnapshot = renderer.create(<WorkplaceList/>).toJSON();
    expect(workplaceListSnapshot).toMatchSnapshot();
});

it('Renders Workplace List correctly', () => {
    const {getByTestId} = render(<WorkplaceList/>);
    expect(getByTestId("workplaceList"))
        .toHaveTextContent("Workplace List"
            && "ID"
            && "Name"
            && "Address");
});