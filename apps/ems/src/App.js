import './App.css';
import WorkplaceList from "./components/workplace/workplaceList.component";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import EmployeePage from "./components/employees/employeePage.component";
import React from "react";

function App()
{
    return (
        <Router>
            <div className="NavBar">
                <div>
                    <Link to={"/employees"}>
                        Employees
                    </Link>
                </div>
                <div>
                    <Link to={"/workplaces"}>
                        Workplaces
                    </Link>
                </div>
            </div>

            <div className="App">
                <Switch>
                    <Route exact
                           path={["/employees", ""]}
                           component={EmployeePage}
                    />
                    <Route exact
                           path={"/workplaces"}
                           component={WorkplaceList}
                    />
                </Switch>
            </div>
        </Router>
    );
}

export default App;
