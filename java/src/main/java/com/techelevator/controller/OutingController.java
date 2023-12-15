package com.techelevator.controller;

import com.techelevator.dao.JdbcOutingDao;
import com.techelevator.dao.OutingDao;
import com.techelevator.model.Outing;
import com.techelevator.model.Restaurant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS}, maxAge = 5000)
@RestController
@RequestMapping("api/outings")
public class OutingController {

  private final OutingDao outingDao;

  public OutingController(OutingDao outingDao){
      this.outingDao = outingDao;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(path= "/newOuting", method = RequestMethod.POST)
  public void addOuting(@RequestBody Outing outing){
      outingDao.createOuting(outing);
  }

  @RequestMapping(path = "/{outingId}", method = RequestMethod.GET)
  public Outing getOutingByOutingId(@PathVariable ("outingId") int outingId){
      return outingDao.getOutingByOutingId(outingId);
  }

  @RequestMapping(path = "/creator/{creatorId}", method = RequestMethod.GET)
  public List<Outing> getOutingByCreatorId(@PathVariable("creatorId") int creatorId){
      return outingDao.getOutingsByCreatorId(creatorId);
  }

  @RequestMapping(path = "/finalists/{outingId}", method = RequestMethod.GET)
  public List<Restaurant> getOutingFinalists(@PathVariable("outingId") int outingId){
      return outingDao.getOutingFinalists(outingId);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @RequestMapping(path= "/updateVotes/{restaurantId}", method = RequestMethod.PUT)
  public void updateVotes(@PathVariable("restaurantId") int restaurantId, @RequestBody Restaurant restaurant){
      outingDao.updateVotes(restaurantId, restaurant);
  }

//    @RequestMapping(path = "/newOuting", method = RequestMethod.OPTIONS)
//    public ResponseEntity<Void> handleOptions() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        return new ResponseEntity<>(headers, HttpStatus.OK);
//    }


}
