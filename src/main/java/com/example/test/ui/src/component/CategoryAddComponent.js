import React, { Component } from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import CategoryDataService from '../service/CategoryDataService';

class CategoryAddComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
    }
    componentDidMount() {
        // eslint-disable-next-line
        if (this.state.name == '') {
            return
        }
    }

    onSubmit(values) {
        let name = values.name
        CategoryDataService.addCategory(name).then(() => this.props.history.push('/categories'))
    }

    render() {
        let name =  this.state.name
        return (
            <div>
                <h3>Add</h3>
                <div className="container">
                    <Formik initialValues={{ name: '' }}
                        onSubmit={this.onSubmit}
                        enableReinitialize={true}>
                        {(props) => (
                            <Form>
                                <fieldset className="form-group">
                                    <label>Name</label>
                                    <Field className="form-control" type="text" name="name" />
                                </fieldset>
                                <button className="btn btn-success" type="submit">Save</button>
                            </Form>
                        )}
                    </Formik>
                </div>
            </div>
        )
    }
}
export default CategoryAddComponent