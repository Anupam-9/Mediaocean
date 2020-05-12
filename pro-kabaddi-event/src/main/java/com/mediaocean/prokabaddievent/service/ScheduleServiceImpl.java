package com.mediaocean.prokabaddievent.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mediaocean.prokabaddievent.entity.MatchEntity;
import com.mediaocean.prokabaddievent.entity.TeamEntity;
import com.mediaocean.prokabaddievent.model.Match;
import com.mediaocean.prokabaddievent.model.Team;
import com.mediaocean.prokabaddievent.repository.MatchEntityRepository;
import com.mediaocean.prokabaddievent.repository.TeamEntityRepository;


@Component("ScheduleServiceImpl")
public class ScheduleServiceImpl implements IScheduleService
{
  @Autowired
  private TeamEntityRepository teamRepository;
  @Autowired
  private MatchEntityRepository matchRepository;
  @Autowired
  private DozerBeanMapper mapper;
  
  public Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class.getSimpleName());
  
  private  LocalDateTime startDateTime;
  
  private  List<Match> generate ( )
  {
    logger.info("Generating schedule ...");
    Iterator<TeamEntity> homeTeamIterator = teamRepository.findAll().iterator();
    List<Team> homeTeamList = new ArrayList<>();
    while ( homeTeamIterator.hasNext() ){
      homeTeamList.add(mapper.map(homeTeamIterator.next(), Team.class));
    }
    
    List<Team> awayTeamList = new ArrayList<>(homeTeamList);
    List<Match> matches = new ArrayList<>();
    
    for ( Team homeTeam : homeTeamList )
    {
      for ( Team awayTeam : awayTeamList )
      {
        if ( homeTeam.getId() != awayTeam.getId() )
        {
          Match match = new Match();
          match.setAwayTeamId(awayTeam.getId());
          match.setHomeTeamId(homeTeam.getId());
          
          match.setLocation(homeTeam.getCity());
          MatchEntity matchEntity = mapper.map(match, MatchEntity.class);
          logger.info("Match Created : {}", matchEntity);
          matches.add(mapper.map(matchRepository.save(matchEntity), Match.class));
        }
      }
    }
    
    while ( matches.stream().filter(match -> match.getMatchDate() == null).count() != 0 )
    {
      List<Match> pendingMatchList = matches.stream().filter(match -> match.getMatchDate() == null)
          .collect(Collectors.toList());
      
      for ( Match match : pendingMatchList )
      {
        List<Match> currentDayMatchList = matches.stream()
            .filter(currentDayMatch -> currentDayMatch.getMatchDate() != null
                && currentDayMatch.getMatchDate().isEqual(startDateTime))
            .collect(Collectors.toList());
        List<Match> previousMatchList = matches.stream()
            .filter(previousDayMatch -> previousDayMatch.getMatchDate() != null
                && previousDayMatch.getMatchDate().isEqual(startDateTime.minusDays(1)))
            .collect(Collectors.toList());
        if ( currentDayMatchList.size() < 2
            && currentDayMatchList.stream().filter(currentMatch -> currentMatch.isTeamMatch(match)).count() == 0
            && previousMatchList.stream().filter(prevMatch -> prevMatch.isTeamMatch(match)).count() == 0 ){
            match.setMatchDate(startDateTime);
            MatchEntity matchEntity = mapper.map(match, MatchEntity.class);
            matchRepository.save(matchEntity);
            logger.info(" MatchDateUpdated  : {}", match);
        }
      }
      startDateTime = startDateTime.plusDays(1);
    }
    
    matches.sort(Comparator.comparing(Match::getMatchDate));
    return matches;
  }

  private void initStartDate(LocalDateTime startDateTime)
  {
    this.startDateTime = startDateTime;
  }
  
  @Override
  public List<Match> initStartDateAndGenerateSchedule (LocalDateTime startDateTime )
  {
    initStartDate(startDateTime);
    return generate();
  }
  
}
