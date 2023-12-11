import axios from "axios";
import { computed } from "vue";

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
    }, 

    addOuting(outing){
        

        // const headers = {
        //     Authorization: `Bearer ${currentToken}`, 
        //     'Content-Type': 'application/json'
        // };


        return baseUrl.post('/api/outings/newOuting', outing);
    }, 

    getOutingsByUserId(userId){
        return baseUrl.get(`/api/outings/creator/${userId}`)
    },

    updateRestaurant(restaurant, restaurantId){
        return baseUrl.put(`/api/outings/updateVotes/${restaurantId}`, restaurant)
    }

    





}



