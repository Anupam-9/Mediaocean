package com.mediaocean.prokabaddievent.service;

import java.time.LocalDateTime;
import java.util.List;

import com.mediaocean.prokabaddievent.model.Match;


public interface IScheduleService
{
  
  public List<Match> initStartDateAndGenerateSchedule(LocalDateTime startDateTime); 
}
