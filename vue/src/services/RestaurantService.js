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
<<<<<<< HEAD
    },



    getBusinessDetails(restaurantId) {
        return baseUrl.get(`/api/businesses/get-details/${restaurantId}`)
            .then(response => response.data)
            .catch(error => {
                console.error("Error fetching restaurant details:", error);
                throw error;
            });
=======
    }, 

    getRestaurantDetails(restaurantId){
        return baseUrl.get(`/api/restaurants/get-details/${restaurantId}`)
>>>>>>> 3678e33f68cd7ea6c23e4c2277e80464639117cf
    }





}


