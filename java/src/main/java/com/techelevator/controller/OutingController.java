package com.techelevator.controller;

import com.techelevator.dao.JdbcOutingDao;
import com.techelevator.dao.OutingDao;
import com.techelevator.model.Outing;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


}
