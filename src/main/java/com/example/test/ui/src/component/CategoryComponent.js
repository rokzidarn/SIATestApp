import React, { Component } from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import CategoryDataService from '../service/CategoryDataService';

class CategoryComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
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
    validate(values) {
        let errors = {}
        if (!values.name) {
            errors.description = 'Enter a name of the category!'
        } else if (values.name.length < 5) {
            errors.description = 'Enter at least 5 characters!'
        }
        return errors
    }

    render() {
        let name =  this.state.name
        return (
            <div>
                <h3>Add</h3>
                <div className="container">
                    <Formik initialValues={{ name: '' }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}>
                        {(props) => (
                            <Form>
                                <ErrorMessage name="name" component="div"
                                    className="alert alert-warning" />
                                <fieldset className="form-group">
                                    <label>ID</label>
                                    <Field className="form-control" type="text" name="id" disabled />
                                </fieldset>
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
export default CategoryComponent