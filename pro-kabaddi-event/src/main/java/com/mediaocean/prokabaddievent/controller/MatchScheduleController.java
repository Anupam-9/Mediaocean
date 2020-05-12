package com.mediaocean.prokabaddievent.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediaocean.prokabaddievent.model.Match;
import com.mediaocean.prokabaddievent.service.IScheduleService;


@RestController
@RequestMapping(path = "/prokabadi/schedule")
public class MatchScheduleController
{
  
  @Autowired
  private IScheduleService scheduleService;
  
  @GetMapping({"/generate","/generate/{startDate}"})
  public List<Match> generate(@PathVariable Optional<String> startDate)
  {
    LocalDateTime startDateTime = startDate.isPresent() ? LocalDateTime.parse(startDate.get(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")): LocalDateTime.now().plusDays(1);
    return scheduleService.initStartDateAndGenerateSchedule(startDateTime);
  }
  
}
