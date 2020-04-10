import React, { Component } from 'react'
import './App.css';
import List, {ListItem, ListItemText} from '@material/react-list';

class Hello extends Component {
    constructor(props) {
        super(props)  // <Hello greeting="Goodbye"> -> access through this.props.greeting
        this.basics = this.basics.bind(this)
        this.state = {active : 'user'}
    }

    componentDidMount() {
        this.setState({active : 'admin'})  // update state, suitable also for calling backend API
        /*
        fetch('http://api.openweathermap.org/data/2.5/weather?q=London&units=Metric&APIkey=XXXXXXX')
      	    .then(response => response.json())
      	    .then(responseData => {
                console.log(responseData)
                })
            })
            .catch(err => console.error(err));
         */
      }

    basics () {
        const PI = 3.14;
        console.log(PI);
        var person = {name : "Rok", id : "1"};
        var hello = `My name is ${person.name}`;

        return hello;

    }

    render () {  // { ... } means execution
        const data = [2, 4, 6, 8];
        const divide = data.map((number, index) =>
            <ListItem><ListItemText primaryText={number / 2}/></ListItem>
        );
        return (
            <>
                <h3>Hello!</h3>
                <p>{this.basics()}</p>
                <div>
                    <List>{divide}</List>
                </div>
            </>
        );
    }
}
export default Hello