package com.techelevator.controller;

import com.techelevator.dao.JdbcOutingDao;
import com.techelevator.dao.OutingDao;
import com.techelevator.model.Outing;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*")
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


}
