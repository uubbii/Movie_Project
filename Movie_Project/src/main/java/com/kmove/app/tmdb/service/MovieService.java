package com.kmove.app.tmdb.service;

import java.util.List;

import com.kmove.app.tmdb.vo.MovieDetailVo.PersonDetailVo;
import com.kmove.app.tmdb.vo.MovieResponseVo;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo.ActorVo;


public interface MovieService {

    /** 
     * TMDB�� Trending -> Movies ī�װ��� �����մϴ�.
     * time_window�� TMDB�� Json������ ���۸� �״�� ����߽��ϴ�.
     * @param time_window "day" �Ǵ� "week" ������ �޽��ϴ�.
     * @return ��ó�� �� ResponseVo�� ��ȭ ����Ʈ�� ��ȯ
     */
    public List<MovieResponseVo> Request_Movies(String time_window) throws Exception;

    
    /**
     * TMDB�� MovieList -> Upcoming ī�װ����� ���� ���� ��ȭ ����� �����ɴϴ�.
     * @return ���� ���� ��ȭ ���
     */
    public List<MovieResponseVo> Request_Upcoming() throws Exception;

    /**
     * TMDB�� Movies -> Details ī�װ����� Ư�� ��ȭ�� ���� ��� ������ �����ɴ�
     * ��.
     * Movie -> credits 
     * Movie -> videos
     * Movie -> images
     * 
     *  �� 3���� ��������Ʈ ����
     * 
     * @param id ��ȭ�� ������ 
     * @return ��ó�� �� �ش� ��ȭ�� Vo�� ��ȯ
     * */
    public MovieVo Request_Detail(int id) throws Exception;
    /**
     * TMDB�� Search -> movie ī�װ����� Ư�� ��ȭ�� ���� ��� ������ �����ɴϴ�.
     * @param word �˻���ȭ
     * @param page ���� ��Ȱ�� �ǵ�
     * @return ��ó�� �� �ش� ��ȭ�� Vo�� ��ȯ
     * */    
	public	List<MovieResponseVo> Request_Search_Movies(String word,int page) throws Exception;
	
    /**
     * TMDB�� Search -> person ī�װ����� Ư�� ��ȭ��쿡 ���� ������ �����ɴϴ�.
     * @param word �˻����
     * @param page ���� ��Ȱ�� �ǵ�
     * @return ��ó�� �� �ش� ����� Vo�� ��ȯ
     * */   
	public	List<ActorVo> Request_Search_Person(String word,int page) throws Exception;

	
	/**TMDB�� PEOPLE -> Details ī�װ����� Ư�� ��ȭ�� ���� ��� ������ �����ɴϴ�.
	 * 
	 * PEOPLE -> Details + PEOPLE -> movie_credits 2���� ��������Ʈ ����  
	 * 
	 * @param id ��ȭ�� ������ ID
	 * */
	public PersonDetailVo Request_People_Credits(int id) throws Exception;
	
}
