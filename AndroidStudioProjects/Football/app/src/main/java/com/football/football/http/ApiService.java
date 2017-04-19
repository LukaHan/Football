package com.football.football.http;

import com.football.football.LeagueEntity;
import com.football.football.TeamEntity;
import com.football.football.entity.base.HttpResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/7.
 */
public interface ApiService {


    //联赛信息
    @GET("league")
    Observable<HttpResult<LeagueEntity>> getLeague(@Query("key") String key, @Query("league") String league);

    //联赛信息
    @GET("team")
    Observable<HttpResult<TeamEntity>> getTeamInfo(@Query("key") String key, @Query("team") String team);
}

