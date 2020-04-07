import React, { Component } from 'react';
import './App.css';
import CategoryListComponent from './component/CategoryListComponent';
import CategoryAddComponent from './component/CategoryAddComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

// https://dzone.com/articles/creating-spring-boot-and-react-crud-full-stack-app
// npx create-react-app ux + App.css -> @import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css)
// npm add axios, react-router-dom, formik
// npm start

class App extends Component {
  render() {
          return (
              <Router>
                  <>
                      <h1>Category Endpoint</h1>
                      <Switch>
                          <Route path="/" exact component={CategoryListComponent} />
                          <Route path="/categories" exact component={CategoryListComponent} />
                          <Route path="/categories/add" component={CategoryAddComponent} />
                      </Switch>
                  </>
              </Router>
          )
      }
}
export default App;