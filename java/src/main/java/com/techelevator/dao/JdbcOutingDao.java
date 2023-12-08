package com.techelevator.dao;

import com.techelevator.model.BusinessSearchDao;
import com.techelevator.model.Outing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcOutingDao implements OutingDao {
    private List<Outing> myOutings;


    private final JdbcTemplate jdbcTemplate;

    public JdbcOutingDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Outing> getMyOutings() {
        return myOutings;
    }

    public void setMyOutings(List<Outing> myOutings) {
        this.myOutings = myOutings;
    }
    @Override
    public Outing getOutingByOutingId(int outingId){
        Outing outing = null;
        String sql = "";
        return null;
    }

    //Is this where 0 is set?
    @Override
    public void createOuting(Outing outing){
        String sqlOutings = "INSERT INTO outings (outing_name, deadline, event_date, creator) " +
                     "VALUES (?, ?, ?, ?) " +
                     "RETURNING outing_id;";

//        String sqlRestaurants = "INSERT INTO restaurants (restaurant_name, thumbs_up, thumbs_down, outing_id) " +
//                "VALUES (?, 0, 0, ?) " +
//                "RETURNING restaurant_id;";
//
//        String sqlGuests = "INSERT INTO guests (guest_name, email_address, outing_id) " +
//                "VALUES (?, ?, ?) " +
//                "RETURNING guest_id;";


        try{
            int outingId =jdbcTemplate.queryForObject(sqlOutings, int.class, outing.getName(), outing.getDateDeadline(), outing.getDateEvent(), outing.getCreatorId());

//            List<Integer> restaurantIds = new ArrayList<Integer>();
//
//            for (int i = 0; i < outing.getOutingRestaurants().size(); i++ ) {
//                int restaurantId = jdbcTemplate.queryForObject(sqlRestaurants, int.class, outing.getOutingRestaurants().get(i).getRestaurantName(), outingId );
//                restaurantIds.add(restaurantId);
//            }
//
//            List<Integer> guestIds = new ArrayList<Integer>();
//
//            for (int i = 0; i < outing.getGuests().size(); i++ ) {
//                int guestId = jdbcTemplate.queryForObject(sqlGuests, int.class, outing.getGuests().get(i).getName(), outing.getGuests().get(i).getEmailAddress(), outingId );
//                guestIds.add(guestId);
//            }

        }catch (Exception e){
            throw e;
        }


    }



}
