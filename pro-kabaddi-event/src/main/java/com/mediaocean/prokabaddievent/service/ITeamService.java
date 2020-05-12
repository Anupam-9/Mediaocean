package com.mediaocean.prokabaddievent.service;

import java.util.List;

import com.mediaocean.prokabaddievent.model.RequestModel;
import com.mediaocean.prokabaddievent.model.ResponseModel;
import com.mediaocean.prokabaddievent.model.Team;

public interface ITeamService
{
  
   public ResponseModel add(RequestModel model);  
   
   public List<Team> add(List<Team> model);

}
