import axios from 'axios'

const API_URL = 'http://localhost:8080'

class CategoryDataService {
    retrieveAllCategories() {
        return axios.get(`${API_URL}/categories`);
    }

    deleteCategory(id) {
        return axios.delete(`${API_URL}/categories/${id}`);
    }

    retrieveCategory(id) {
        return axios.get(`${API_URL}/categories/${id}`);
    }

    addCategory(name) {
          return axios.post(`${API_URL}/categories`, name);
    }
}

export default new CategoryDataService()