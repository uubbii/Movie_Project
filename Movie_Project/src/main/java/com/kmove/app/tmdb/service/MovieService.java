package com.kmove.app.tmdb.service;

import java.util.List;

import com.kmove.app.tmdb.vo.MovieDetailVo.PersonDetailVo;
import com.kmove.app.tmdb.vo.MovieResponseVo;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo.ActorVo;


public interface MovieService {

    /** 
     * TMDB의 Trending -> Movies 카테고리에 존재합니다.
     * time_window는 TMDB의 Json파일의 매퍼명 그대로 사용했습니다.
     * @param time_window "day" 또는 "week" 값만을 받습니다.
     * @return 전처리 후 ResponseVo로 영화 리스트를 반환
     */
    public List<MovieResponseVo> Request_Movies(String time_window) throws Exception;

    
    /**
     * TMDB의 MovieList -> Upcoming 카테고리에서 개봉 예정 영화 목록을 가져옵니다.
     * @return 개봉 예정 영화 목록
     */
    public List<MovieResponseVo> Request_Upcoming() throws Exception;

    /**
     * TMDB의 Movies -> Details 카테고리에서 특정 영화에 대한 모든 정보를 가져옵니
     * 다.
     * Movie -> credits 
     * Movie -> videos
     * Movie -> images
     * 
     *  총 3개의 엔드포인트 조합
     * 
     * @param id 영화의 고유값 
     * @return 전처리 후 해당 영화의 Vo를 반환
     * */
    public MovieVo Request_Detail(int id) throws Exception;
    /**
     * TMDB의 Search -> movie 카테고리에서 특정 영화에 대한 모든 정보를 가져옵니다.
     * @param word 검색영화
     * @param page 차후 재활용 의도
     * @return 전처리 후 해당 영화의 Vo를 반환
     * */    
	public	List<MovieResponseVo> Request_Search_Movies(String word,int page) throws Exception;
	
    /**
     * TMDB의 Search -> person 카테고리에서 특정 영화배우에 대한 정보를 가져옵니다.
     * @param word 검색배우
     * @param page 차후 재활용 의도
     * @return 전처리 후 해당 배우의 Vo를 반환
     * */   
	public	List<ActorVo> Request_Search_Person(String word,int page) throws Exception;

	
	/**TMDB의 PEOPLE -> Details 카테고리에서 특정 영화에 대한 모든 정보를 가져옵니다.
	 * 
	 * PEOPLE -> Details + PEOPLE -> movie_credits 2가지 엔드포인트 조합  
	 * 
	 * @param id 영화의 고유값 ID
	 * */
	public PersonDetailVo Request_People_Credits(int id) throws Exception;
	
}
