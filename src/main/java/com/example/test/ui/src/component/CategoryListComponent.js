import React, { Component } from 'react';
import CategoryDataService from '../service/CategoryDataService';

class CategoryListComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            categories: [],
            message: null
        }
        this.refreshCategories = this.refreshCategories.bind(this)
        this.deleteCategoryClicked = this.deleteCategoryClicked.bind(this)
        this.addCategoryClicked = this.addCategoryClicked.bind(this)
    }
    componentDidMount() {
        this.refreshCategories();
    }

    refreshCategories() {
        CategoryDataService.retrieveAllCategories()
            .then(
                response => {
                    console.log(response);
                    this.setState({ categories: response.data })
                }
            )
    }
    deleteCategoryClicked(id) {
        CategoryDataService.deleteCategory(id)
            .then(
                response => {
                    this.setState({ message: `Delete of category was successful` })
                    this.refreshCategories()
                }
            )
    }
    addCategoryClicked() {
        this.props.history.push(`/categories/add`)
    }

    render() {
        return (
            <div className="container">
                <h3>All Categories</h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.categories.map(
                                    category =>
                                        <tr key={category.id}>
                                            <td>{category.id}</td>
                                            <td>{category.name}</td>
                                            <td>
                                                <button className="btn btn-warning" onClick={() =>
                                                    this.deleteCategoryClicked(category.id)}>Delete
                                                </button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <div className="row">
                    <button className="btn btn-success" onClick={this.addCategoryClicked}>Add</button>
                </div>
            </div>
        )
    }
}
export default CategoryListComponent
