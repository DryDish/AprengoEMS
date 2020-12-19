import React from "react";
import httpService from "../../services/httpService";
import { Container, Row, Col } from 'react-bootstrap';

export default class WorkplaceList extends React.Component
{
    constructor(props)
    {
        super(props);

        this.getWorkplaceList = this.getWorkplaceList.bind(this);

        this.state = {
            workplaces: [],
        };
    }
    getWorkplaceList() {
        // TODO - Adjust to work with Jan's API when it is done.
        // TODO - Learn mocking.
        httpService
            .get("/workplaces")
            .then((response) =>
            {
                console.log("getWorkplaceList Response :");
                console.log(response.data);

                this.setState({
                    workplaces: response.data,
                });
            })
            .catch((e) =>
            {
                console.log(e);
            });
    }

    componentDidMount()
    {
        this.getWorkplaceList();
    }

    render()
    {
        const workplaceList = this.state.workplaces.map((workplace, index) =>
        {
            return (
                <Container>
                    <Row key={index}>
                        <Col xs={1}>{workplace.id}</Col>
                        <Col>{workplace.name}</Col>
                        <Col xs={2}>{workplace.address}</Col>
                    </Row>
                </Container>
            );
        });

        return (
            <div className="col-" data-testid={"workplaceList"} style={{width: "80%", margin: "auto"}}>
                <h3 style={{fontWeight:"bold"}}>Workplace List</h3><br/>
                <Container>
                    <Row style={{fontWeight: "Bold"}}>
                        <Col xs={1}>ID</Col>
                        <Col>Name</Col>
                        <Col xs={2}>Address</Col>
                    </Row>
                </Container>
                <hr></hr>
                <ul className="list-group">
                    {workplaceList}
                </ul>
            </div>
        );
    }
}