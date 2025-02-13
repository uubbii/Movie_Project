package com.kmove.app.tmdb.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmove.app.tmdb.vo.MovieDetailVo;
import com.kmove.app.tmdb.vo.MovieDetailVo.PersonDetailVo;
import com.kmove.app.tmdb.vo.MovieDetailVo.PersonDetailVo.CastVo;
import com.kmove.app.tmdb.vo.MovieResponseVo;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo.ActorVo;

@Service
public class MovieServiceIml implements MovieService{
	
	private String APIKEY = System.getenv("API_KEY");
	
    // MovieServiceIml 내부에서 공용사용
    private final HttpClient client = HttpClient.newHttpClient();
    static final ObjectMapper objectMapper = new ObjectMapper();



    
    @Override
    public List<MovieResponseVo> Request_Movies(String time_window)throws Exception {
	    	
    	MovieComm.genreCache = MovieComm.Request_Genre();
    	
    	
	    HttpRequest request = HttpRequest.newBuilder()
	    	    .uri(URI.create("https://api.themoviedb.org/3/trending/movie/"+time_window+"?language=ko-KR"))
	    	    .header("accept", "application/json")
	    	    .header("Authorization", APIKEY)
	    	    .method("GET", HttpRequest.BodyPublishers.noBody())
	    	    .build();
        // API 호출
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 JsonNode로 파싱 (List<MovieResponseVo>로 파싱할 수도 있지만, 여기선 하나씩 대입)
        JsonNode rootNode = objectMapper.readTree(response.body());
        JsonNode resultsNode = rootNode.get("results");

        // 결과를 List<MovieResponseVo>로 변환
        List<MovieResponseVo> movieResponseVoList  = new ArrayList<MovieResponseVo>();
        List<MovieVo> movieVoList = new ArrayList<MovieVo>();
        
        // for-each로 각 결과를 MovieResponseVo에 하나씩 대입
        for (JsonNode movieNode : resultsNode) {
            MovieVo movieVo = new MovieVo();

            // 각 필드를 하나씩 대입
            movieVo.setId(movieNode.get("id").asInt());
            movieVo.setTitle(movieNode.get("title").asText());
            movieVo.setOriginal_title(movieNode.get("original_title").asText());
            movieVo.setOverview(movieNode.get("overview").asText());
            movieVo.setPoster_path(movieNode.get("poster_path").asText());
            movieVo.setBackdrop_path(movieNode.get("backdrop_path").asText());
            movieVo.setRelease_date(movieNode.get("release_date").asText());
            movieVo.setPopularity(movieNode.get("popularity").asDouble());
            movieVo.setVote_average(movieNode.get("vote_average").asDouble());
            movieVo.setVote_count(movieNode.get("vote_count").asInt());

            
            // 장르 배열 처리 (List<Integer>)
            List<Integer> genreIds = new ArrayList<Integer>();
            
            for (JsonNode genreNode : movieNode.get("genre_ids")) {
                genreIds.add(genreNode.asInt());
            }
            
            
            // VO 객체에 genrename 필드에 저장하는 부분            
            List<String> genreNames = MovieComm.getGenreNames(genreIds); // 장르 이름 리스트 받기
            movieVo.setGenrename(genreNames); // VO에 genrename 설정
            // List에 추가
            
            movieVoList.add(movieVo);
            movieVo.setGenrename(MovieComm.getGenreNames(genreIds));            

        }


        // MovieResponseVo 생성 후 movieVoList를 설정
        MovieResponseVo movieResponseVo = new MovieResponseVo();
        movieResponseVo.setResults(movieVoList);

        // movieResponseVoList에 추가 (필요한 경우 여러 MovieResponseVo가 있을 수 있음)
        movieResponseVoList.add(movieResponseVo);

        
        
        // MovieResponseVo 리스트 반환
        return movieResponseVoList;
    }


 
	@Override
	public List<MovieResponseVo> Request_Upcoming() throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.themoviedb.org/3/movie/upcoming?language=ko-KR&page=1&region=KR"))
			    .header("accept", "application/json")
			    .header("Authorization", APIKEY)
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			  // JSON 응답을 JsonNode로 파싱 (List<MovieResponseVo>로 파싱할 수도 있지만, 여기선 하나씩 대입)
	        JsonNode rootNode = objectMapper.readTree(response.body());
	        JsonNode resultsNode = rootNode.get("results");

	        // 결과를 List<MovieResponseVo>로 변환
	        List<MovieResponseVo> movieResponseVoList  = new ArrayList<MovieResponseVo>();
	        List<MovieVo> movieVoList = new ArrayList<MovieVo>();
	        
	        // for-each로 각 결과를 MovieResponseVo에 하나씩 대입
	        for (JsonNode movieNode : resultsNode) {
	            MovieVo movieVo = new MovieVo();

	            // 각 필드를 하나씩 대입
	            movieVo.setId(movieNode.get("id").asInt());
	            movieVo.setTitle(movieNode.get("title").asText());
	            movieVo.setOriginal_title(movieNode.get("original_title").asText());
	            movieVo.setOverview(movieNode.get("overview").asText());
	            movieVo.setPoster_path(movieNode.get("poster_path").asText());
	            movieVo.setBackdrop_path(movieNode.get("backdrop_path").asText());
	            movieVo.setRelease_date(movieNode.get("release_date").asText());
	            movieVo.setPopularity(movieNode.get("popularity").asDouble());
	            movieVo.setVote_average(movieNode.get("vote_average").asDouble());
	            movieVo.setVote_count(movieNode.get("vote_count").asInt());

	            
	            // 장르 배열 처리 (List<Integer>)
	            List<Integer> genreIds = new ArrayList<Integer>();
	            
	            for (JsonNode genreNode : movieNode.get("genre_ids")) {
	                genreIds.add(genreNode.asInt());
	            }
	            
	            
	            // VO 객체에 genrename 필드에 저장하는 부분            
	            List<String> genreNames = MovieComm.getGenreNames(genreIds); // 장르 이름 리스트 받기
	            movieVo.setGenrename(genreNames); // VO에 genrename 설정
	            // List에 추가
	            
	            movieVoList.add(movieVo);
	            movieVo.setGenrename(MovieComm.getGenreNames(genreIds));
	            

	        }


	        // MovieResponseVo 생성 후 movieVoList를 설정
	        MovieResponseVo movieResponseVo = new MovieResponseVo();
	        movieResponseVo.setResults(movieVoList);

	        // movieResponseVoList에 추가 (필요한 경우 여러 MovieResponseVo가 있을 수 있음)
	        movieResponseVoList.add(movieResponseVo);
	        
	        
	        // MovieResponseVo 리스트 반환
	        return movieResponseVoList;
	    }

	@Override
	public MovieVo Request_Detail(int id) throws Exception {
	    HttpRequest request = HttpRequest.newBuilder()
	    		.uri(URI.create("https://api.themoviedb.org/3/movie/" + id +
	    			    "?append_to_response=credits,videos,images" +
	    			    "&include_image_language=ko,null" +
	    			    "&language=ko-KR"))

	        .header("accept", "application/json")
	        .header("Authorization", APIKEY)
	        .method("GET", HttpRequest.BodyPublishers.noBody())
	        .build();

	    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

	    // JSON 응답 파싱
	    JsonNode rootNode = objectMapper.readTree(response.body());

	    MovieDetailVo movieVo = new MovieDetailVo();
	    movieVo.setId(rootNode.get("id").asInt());
	    movieVo.setTitle(rootNode.get("title").asText());
	    movieVo.setOriginal_title(rootNode.get("original_title").asText());
	    movieVo.setOverview(rootNode.get("overview").asText());
	    movieVo.setPoster_path(rootNode.get("poster_path").asText());
	    movieVo.setBackdrop_path(rootNode.get("backdrop_path").asText());
	    movieVo.setRelease_date(rootNode.get("release_date").asText());
	    movieVo.setPopularity(rootNode.get("popularity").asDouble());
	    movieVo.setVote_average(rootNode.get("vote_average").asDouble());
	    movieVo.setVote_count(rootNode.get("vote_count").asInt());

	    // 수익 처리 (소숫점 1자리로 반올림)
	    double revenue = Math.round(rootNode.get("revenue").asDouble() * 10) / 10.0;
	    movieVo.setRevenue(revenue);

	    // 장르 처리
	    JsonNode genresNode = rootNode.get("genres");
	    List<Integer> genreIds = new ArrayList<Integer>();
	    List<String> genreNames = new ArrayList<String>();

	    if (genresNode != null && genresNode.isArray()) {
	        for (JsonNode genreNode : genresNode) {
	            Integer genreId = genreNode.get("id").asInt();
	            genreIds.add(genreId);

	            String genreName = MovieComm.genreCache.get(genreId); 
	            if (genreName != null) {
	                genreNames.add(genreName);
	            } else {
	                genreNames.add("Unknown");
	            }
	        }
	    }

	    movieVo.setGenre_ids(genreIds);
	    movieVo.setGenrename(genreNames);

	    // Video
	    List<String> videoUrls = new ArrayList<String>();
	    JsonNode videosNode = rootNode.path("videos").path("results");
	    for (JsonNode videoNode : videosNode) {
	        String videoUrl = videoNode.path("key").asText();
	        videoUrls.add(videoUrl);
	    }
	    movieVo.setVideoUrls(videoUrls);
 
	 // Image
	    JsonNode backdropsNode = rootNode.path("images").path("backdrops");

	    List<String> imageUrls = new ArrayList<String>();
	    for (JsonNode backdrop : backdropsNode) {
	        String filePath = backdrop.path("file_path").asText();
	        if (!filePath.isEmpty()) {
	            String imageUrl = "https://image.tmdb.org/t/p/original" + filePath;
	            imageUrls.add(imageUrl);
	        }
	    }

	    movieVo.setImageUrls(imageUrls);
	    
	    
	    // 배우 정보 처리 (credits.cast)
	    JsonNode castNode = rootNode.path("credits").path("cast");
	    List<CastVo> actorVoList = new ArrayList<CastVo>();
	    for (JsonNode actorNode : castNode) {
	        CastVo actorVo = new CastVo();
	        actorVo.setId(actorNode.get("id").asInt());
	        actorVo.setName(actorNode.get("name").asText());
	        actorVo.setKnown_for_department(actorNode.get("known_for_department").asText());
	        actorVo.setProfile_path(actorNode.get("profile_path").asText());
	        actorVo.setGender(actorNode.get("gender").asInt());
	        actorVo.setCharacter(actorNode.get("character").asText());
	        actorVo.setOriginal_name(actorNode.get("original_name").asText());
	        actorVoList.add(actorVo);
	    }
	    movieVo.setCasts(actorVoList); // ActorVo 리스트를 MovieVo에 설정	   
	    return movieVo;
	}

	
	
	@Override
	public	List<MovieResponseVo> Request_Search_Movies(String word,int page) throws Exception{
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.themoviedb.org/3/search/movie?query="+word.replaceAll("\\s+", "")+"&include_adult=false&language=ko-KR&page=" + page))
			    .header("accept", "application/json")
			    .header("Authorization", APIKEY)
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		

			  // JSON 응답을 JsonNode로 파싱 (List<MovieResponseVo>로 파싱할 수도 있지만, 여기선 하나씩 대입)
	        JsonNode rootNode = objectMapper.readTree(response.body());
	        JsonNode resultsNode = rootNode.get("results");

	        // 결과를 List<MovieResponseVo>로 변환
	        List<MovieResponseVo> movieResponseVoList  = new ArrayList<MovieResponseVo>();
	        List<MovieVo> movieVoList = new ArrayList<MovieVo>();
	        
	        // for-each로 각 결과를 MovieResponseVo에 하나씩 대입
	        for (JsonNode movieNode : resultsNode) {
	            MovieVo movieVo = new MovieVo();

	            // 각 필드를 하나씩 대입
	            movieVo.setId(movieNode.get("id").asInt());
	            movieVo.setTitle(movieNode.get("title").asText());
	            movieVo.setOriginal_title(movieNode.get("original_title").asText());
	            movieVo.setOverview(movieNode.get("overview").asText());
	            movieVo.setPoster_path(movieNode.get("poster_path").asText());
	            movieVo.setBackdrop_path(movieNode.get("backdrop_path").asText());
	            movieVo.setRelease_date(movieNode.get("release_date").asText());
	            movieVo.setPopularity(movieNode.get("popularity").asDouble());
	            movieVo.setVote_average(movieNode.get("vote_average").asDouble());
	            movieVo.setVote_count(movieNode.get("vote_count").asInt());

	            
	            // 장르 배열 처리 (List<Integer>)
	            List<Integer> genreIds = new ArrayList<Integer>();
	            
	            for (JsonNode genreNode : movieNode.get("genre_ids")) {
	                genreIds.add(genreNode.asInt());
	            }
	            
	            
	            // VO 객체에 genrename 필드에 저장하는 부분            
	            List<String> genreNames = MovieComm.getGenreNames(genreIds); // 장르 이름 리스트 받기
	            movieVo.setGenrename(genreNames); // VO에 genrename 설정
	            // List에 추가
	            
	            movieVoList.add(movieVo);
	            movieVo.setGenrename(MovieComm.getGenreNames(genreIds));
	            

	        }


	        // MovieResponseVo 생성 후 movieVoList를 설정
	        MovieResponseVo movieResponseVo = new MovieResponseVo();
	        movieResponseVo.setResults(movieVoList);

	        // movieResponseVoList에 추가 (필요한 경우 여러 MovieResponseVo가 있을 수 있음)
	        movieResponseVoList.add(movieResponseVo);

	        
	        // MovieResponseVo 리스트 반환
	        return movieResponseVoList;
			
	}

	
	@Override
	public List<ActorVo> Request_Search_Person(String word, int page) throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.themoviedb.org/3/search/person?query="+word.replaceAll("\\s+", "")+"&include_adult=false&language=ko-KR&page=1"))
			    .header("accept", "application/json")
			    .header("Authorization", APIKEY)
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

			JsonNode rootNode = objectMapper.readTree(response.body());
			JsonNode resultsNode = rootNode.get("results");
			
			List<ActorVo> actorVoList = new ArrayList<ActorVo>();
			
			for (JsonNode actorNode : resultsNode) {
			    List<MovieVo> movieList = MovieComm.getMovieList(actorNode.get("known_for"));

			    if (movieList.isEmpty()) {
			        continue; // 영화 목록이 0개이면 다음파싱 진행, 최대 5개까지만 파싱
			    }

			    ActorVo actorVo = new ActorVo();
			    actorVo.setKnown_for(movieList);

			    // 필수 정보만 추출
			    actorVo.setId(actorNode.get("id").asInt());
			    actorVo.setName(actorNode.get("name").asText());
			    actorVo.setKnown_for_department(actorNode.get("known_for_department").asText());
			    actorVo.setProfile_path(actorNode.get("profile_path").asText());
			    actorVo.setGender(actorNode.get("gender").asInt());

			    actorVoList.add(actorVo);
			}

			
		return actorVoList; // ActorVo 리스트 반환
	}
	


	@Override
	public PersonDetailVo  Request_People_Credits(int id) throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.themoviedb.org/3/person/"+id+"?append_to_response=%2Cmovie_credits&language=ko-KR"))
			    .header("accept", "application/json")
			    .header("Authorization", APIKEY)
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
	     	     

		    // JSON을 바로 Java 객체로 매핑
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 알 수 없는 필드 무시
		    PersonDetailVo personDetailVo = objectMapper.readValue(response.body(), PersonDetailVo.class);

		    List<MovieResponseVo.MovieVo> movies = personDetailVo.getMovie_credits().getCast();

		    movies.sort(new Comparator<MovieVo>() {
		        @Override
		        public int compare(MovieVo movie1, MovieVo movie2) {
		            // 내림차순 정렬: vote_average가 큰 것이 먼저 오도록(투표 많은 순.)
		            return Double.compare(movie2.getPopularity(), movie1.getPopularity());
		        }
		    });


		    
		    
		    return personDetailVo;


	    }
		
}