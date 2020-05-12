package com.mediaocean.prokabaddievent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediaocean.prokabaddievent.model.ResponseModel;
import com.mediaocean.prokabaddievent.model.Team;
import com.mediaocean.prokabaddievent.service.ITeamService;


@RestController
@RequestMapping(path = "/prokabadi/team")
public class TeamController
{
  
  @Autowired
  private ITeamService service;
  
  @PostMapping(path = "/add", consumes="application/json", produces="application/json")
  public ResponseModel add ( @RequestBody Team team )
  {
    return service.add(team);
  }
  
  @PostMapping(path="/add/all", consumes="application/json", produces="application/json")
  public List<Team> addAll(@RequestBody List<Team> teamList)
  {
     return service.add(teamList);
  }
 
}
