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

    addCategory(cname) {
        let config = {
          headers: {
              'Content-Type': 'application/json;charset=UTF-8',
              "Access-Control-Allow-Origin": "*",
          }
        };

         return axios.post(`${API_URL}/categories`, { name: cname }, config);
    }
}

export default new CategoryDataService()