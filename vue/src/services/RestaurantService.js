import axios from "axios";

const baseUrl = axios.create({
    baseURL: "http://localhost:9000"
});

export default{
    getRestaurantsByZip(zipCode){
        return baseUrl.get(`/api/restaurants/search/${zipCode}`)
    }
}