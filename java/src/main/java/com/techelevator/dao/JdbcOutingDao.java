package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.BusinessSearchDao;
import com.techelevator.model.Guest;
import com.techelevator.model.Outing;
import com.techelevator.model.Restaurant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
        Outing returnedOuting = new Outing();
        String outingSql = "SELECT outing_id, outing_name, deadline, event_date, creator, zipcode " +
                    "FROM outings " +
                    "WHERE outing_id = ?;";


        try{
            SqlRowSet outingResults = jdbcTemplate.queryForRowSet(outingSql, outingId);
            if (outingResults.next()){
                returnedOuting = mapRowToOuting(outingResults);
            }

            String restaurantSql = "SELECT restaurant_id, restaurant_name, thumbs_up, thumbs_down, outing_id, long_restaurant_id FROM restaurants WHERE outing_id = ?";
            SqlRowSet restaurantResults = jdbcTemplate.queryForRowSet(restaurantSql, outingId);
            List<Restaurant> restaurants = mapRowToRestaurants(restaurantResults);
            returnedOuting.setOutingRestaurants(restaurants);

            String guestSql = "SELECT guest_id, guest_name, email_address FROM guests WHERE outing_id = ?";
            SqlRowSet guestResults = jdbcTemplate.queryForRowSet(guestSql, outingId);
            List<Guest> guests = mapRowToGuests(guestResults);
            returnedOuting.setGuests(guests);

        }catch(Exception e){
            throw new DaoException("error",e);
        }
        return returnedOuting;
    }
    @Override
    public List<Outing> getOutingsByCreatorId(int creatorId){
        List<Outing> returnedOutings = new ArrayList<>();

        String sql = "SELECT outing_id, outing_name, deadline, event_date, creator, zipcode FROM outings WHERE creator = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, creatorId);
            while (results.next()) {
                Outing outing = mapRowToOuting(results);
                returnedOutings.add(outing);
            }
        }catch (Exception e){
            throw e;
        }

        return returnedOutings;

    }

    //Is this where 0 is set?
    @Override
    public void createOuting(Outing outing){
        String sqlOutings = "INSERT INTO outings (outing_name, deadline, event_date, creator, zipcode) " +
                     "VALUES (?, ?, ?, ?, ?) " +
                     "RETURNING outing_id;";


        try{
            int outingId =jdbcTemplate.queryForObject(sqlOutings, int.class, outing.getName(), outing.getDateDeadline(), outing.getDateEvent(), outing.getCreatorId(), outing.getZipCode());

            List<Integer> restaurants = new ArrayList<>();

            for (int i = 0; i < outing.getOutingRestaurants().size(); i++ ) {
                String name = outing.getOutingRestaurants().get(i).getRestaurantName();
                System.out.println(name);
                String restaurantLongId = outing.getOutingRestaurants().get(i).getLongRestaurantId();

                String sqlRestaurant = "INSERT INTO restaurants (restaurant_name, thumbs_up, thumbs_down, outing_id, long_restaurant_id) " +
                        "VALUES (?, 0, 0, ?, ?) " +
                        "RETURNING restaurant_id;";
                Integer restaurantId = jdbcTemplate.queryForObject(sqlRestaurant, Integer.class, name, outingId, restaurantLongId );
                restaurants.add(restaurantId);
            }

            List<Integer> guests = new ArrayList<>();

            for (int i = 0; i < outing.getGuests().size(); i++ ) {
                String guestName = outing.getGuests().get(i).getName();
                String guestEmail = outing.getGuests().get(i).getEmailAddress();

                String sqlGuests = "INSERT INTO guests (guest_name, email_address, outing_id) " +
                        "VALUES (?, ?, ?) " +
                        "RETURNING guest_id;";

                Integer guestId = jdbcTemplate.queryForObject(sqlGuests, Integer.class, guestName, guestEmail, outingId );
                guests.add(guestId);
            }

        }catch (Exception e){
            throw e;
        }


    }

    @Override
    public List<Restaurant> getOutingFinalists(int outingId){
        List<Restaurant> returnedFinalists;
        String sql = "SELECT restaurant_id, restaurant_name, thumbs_up, thumbs_down, outing_id, long_restaurant_id, (thumbs_up - thumbs_down) AS diff " +
                "FROM restaurants " +
                "WHERE outing_id = ? AND (thumbs_up - thumbs_down) = (SELECT MAX(thumbs_up - thumbs_down) FROM restaurants);";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, outingId);
            returnedFinalists = mapRowToRestaurants(results);
        }catch (Exception e){
            throw e;
        }
        return returnedFinalists;
    }

    public void updateVotes(int restaurantId, Restaurant restaurant) {
        String sql = "UPDATE restaurants SET thumbs_up = ?, thumbs_down = ?" +
                " WHERE restaurant_id = ?;";
        int numOfRows = 0;

        try {
            numOfRows = jdbcTemplate.update(sql,restaurant.getThumbsUp(),restaurant.getThumbsDown(),restaurantId);
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected one.");
            }
        } catch (Exception e){
            throw e;
        }
    }

    private List<Guest> mapRowToGuests(SqlRowSet rs){
        List<Guest> guests = new ArrayList<>();

        while(rs.next()){
            Guest guest = new Guest();
            guest.setGuestId(rs.getInt("guest_id"));
            guest.setName(rs.getString("guest_name"));
            guest.setEmailAddress(rs.getString("email_address"));
            guests.add(guest);
        }
        return guests;
    }

    private List<Restaurant> mapRowToRestaurants(SqlRowSet rs){
        List<Restaurant> restaurants = new ArrayList<>();

        while(rs.next()){
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(rs.getInt("restaurant_id"));
            restaurant.setRestaurantName(rs.getString("restaurant_name"));
            restaurant.setThumbsDown(rs.getInt("thumbs_down"));
            restaurant.setThumbsUp(rs.getInt("thumbs_up"));
            restaurant.setLongRestaurantId(rs.getString("long_restaurant_id"));
            restaurants.add(restaurant);
        }
        return restaurants;
    }

    private Outing mapRowToOuting(SqlRowSet rs){
        Outing outing = new Outing();
        outing.setOutingId(rs.getInt("outing_id"));
        outing.setName(rs.getString("outing_name"));
        outing.setDateDeadline(rs.getString("deadline"));
        outing.setDateEvent(rs.getString("event_date"));
        outing.setCreatorId(rs.getInt("creator"));
        outing.setZipCode(rs.getString("zipcode"));

        return outing;
    }








}
