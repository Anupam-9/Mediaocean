package com.mediaocean.prokabaddievent.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Match implements ResponseModel {

	private static final long serialVersionUID = 1004193081628327382L;

	private long id;
	private long homeTeamId;
	private long awayTeamId;
	private LocalDateTime matchDate;
	private String location;

	public boolean isTeamMatch(Match other) {
		return (other != null && other instanceof Match)
				&& (this.homeTeamId == other.homeTeamId || this.homeTeamId == other.awayTeamId
						|| this.awayTeamId == other.awayTeamId || this.awayTeamId == other.homeTeamId);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(long homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public long getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(long awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	public LocalDateTime getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(LocalDateTime matchDate) {
		this.matchDate = matchDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
