import axios from "axios";

const baseUrl = axios.create({
    baseURL: "http://localhost:9000"
});

export default{
    getRestaurantsByZipAndCategory(zipCode, category){
        return baseUrl.get(`/api/restaurants/search/${zipCode}/${category}`)
    }, 

    getRestaurantsByZipOnly(zipCode){
        return baseUrl.get(`/api/restaurants/search/${zipCode}`)
    }
}