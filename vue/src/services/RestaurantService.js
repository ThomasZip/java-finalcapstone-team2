import axios from "axios";

const baseUrl = axios.create({
    baseURL: "http://localhost:9000"
});

export default{
    getRestaurantsByZipAndCategory(zipCode, category){
        let url = `/api/restaurants/search/${zipCode}/${category}`;
        console.log(url);
        return baseUrl.get(`/api/restaurants/search/${zipCode}/${category}`)

    }, 

    getRestaurantsByZipOnly(zipCode){
        let url = `/api/restaurants/search/${zipCode}`;
        console.log(url);
        return baseUrl.get(`/api/restaurants/search/${zipCode}`)
    }, 

    getRestaurantDetails(restaurantId){
        return baseUrl.get(`/api/restaurants/get-details/${restaurantId}`)
    }, 

    getOutingByOutingId(outingId){
        return baseUrl.get(`/api/outings/${outingId}`)
    }





}


