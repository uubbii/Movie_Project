package com.kmove.app.tmdb.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo;




public class MovieComm {        
    
	
	/**
	 * MovieComm에서 선언된 genreCache를 통해서 API를 계속 불러오는 것이 아니라
	 * 최초 1회 실행 후, 동일한 Map타입의 genreCache를 재사용합니다.
	 * */
    public static Map<Integer, String> genreCache = new HashMap<Integer, String>();
	
    /**API에서 장르 목록을 불러옵니다. (최초 실행 시 1회만 사용)
     * 한번 불러온 후 genreCache에 저장해서 계속 사용합니다.
     * 
     * */
     static Map<Integer, String> Request_Genre() throws Exception {
    	

        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.themoviedb.org/3/genre/movie/list?language=ko-KR"))
            .header("accept", "application/json")
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YTRmNDQyMjk0ZWVkYWRlNzg0Y2YzYmMxYmY4MTg0NSIsIm5iZiI6MTczNTM4MjgzMy42NTY5OTk4LCJzdWIiOiI2NzZmZDczMTJjOTA5N2IyNjY2MTgxODYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.KeXKIxREdOF3yahLShScp-dLpJ5HHrx932boLbQriS0")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode genresNode = MovieServiceIml.objectMapper.readTree(response.body());
        Map<Integer, String> genreMap = new HashMap<Integer, String>();

        for (JsonNode genreNode : genresNode.get("genres")) {
            Integer genreId = genreNode.get("id").asInt();
            String genreName = genreNode.get("name").asText();
            
            genreMap.put(genreId, genreName); 
        }

        return genreMap;
    }


     
     /**
      * 장르 ID -> 장르 이름 변환 MovieComm 메소드
      * @param genreIds Integer형식의 리스트를 받아옵니다.
      * 
      * @return Integer로 이루어진 리스트(장르)를 String으로 변환 후, 반환합니다.
      * */
    public static List<String> getGenreNames(List<Integer> genreIds) throws Exception {
     	
//     	System.out.println("\n Genre Cache: " + genreIds + "\n" );
         // 장르 데이터를 이미 캐시해놓았다면 캐시된 데이터를 사용
         if (!genreCache.isEmpty()) {
             List<String> genreNames = new ArrayList<String>();
             
             // genreIds에 포함된 장르 ID에 해당하는 장르 이름을 genreCache에서 찾아 추가
             for (Integer genreId : genreIds) {
                 String genreName = genreCache.get(genreId); // genreCache에서 genreId에 해당하는 장르 이름 찾기
                 if (genreName != null) {
                     genreNames.add(genreName);  // null이 아니면 장르 이름을 리스트에 추가
                 } else {
                     genreNames.add("Unknown");  // API에 등록되지 않았을 경우 "Unknown"을 추가
                 }
             }
             
             return genreNames;
         } else {
             throw new Exception("장르값 NULL 오류!!! 서버 재실행 할 것");
         }
     }
    
    
 /** JSON 데이터를 트리 형태로 출력하는 메서드입니다.(그냥 print와 동일, Json 계층 확인용) 
  * @param node 원하는 JsonNode
  * @param 입력명
  * 
  * 
  * */
    public static void printJsonTree(JsonNode node, String indent) {
        if (node.isObject()) { // JSON 객체인 경우
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                printJsonTree(entry.getValue(), indent + "  ");
            }
        } else if (node.isArray()) { // JSON 배열인 경우
            int index = 0;
            for (JsonNode arrayNode : node) {
                printJsonTree(arrayNode, indent + "  ");
                index++;
            }
        } else { // 단일 값인 경우
            System.out.println(indent + node.asText() + "\n\n");
        }
    }


	public static List<MovieVo> getMovieList(JsonNode knownForNode) {
		
		List<MovieVo> movieVoList = new ArrayList<MovieVo>();

		for (JsonNode movieNode : knownForNode) {
		    if (movieNode.has("media_type") && "movie".equals(movieNode.get("media_type").asText()) &&
		        movieNode.has("adult") && "false".equals(movieNode.get("adult").asText())) {

		        MovieVo movieVo = new MovieVo();
		        movieVo.setId(movieNode.has("id") ? movieNode.get("id").asInt() : 0);
		        movieVo.setTitle(movieNode.has("title") ? movieNode.get("title").asText() : "");
		        movieVo.setOriginal_title(movieNode.has("original_title") ? movieNode.get("original_title").asText() : "");
		        movieVo.setOverview(movieNode.has("overview") ? movieNode.get("overview").asText() : "");
		        movieVo.setPoster_path(movieNode.has("poster_path") ? movieNode.get("poster_path").asText() : "");
		        movieVo.setRelease_date(movieNode.has("release_date") ? movieNode.get("release_date").asText() : "");
		        movieVo.setPopularity(movieNode.has("popularity") ? movieNode.get("popularity").asDouble() : 0.0);
		        movieVo.setVote_average(movieNode.has("vote_average") ? movieNode.get("vote_average").asDouble() : 0.0);

		        movieVoList.add(movieVo);

		        if (movieVoList.size() >= 5) {
		            break; // 최대 5개까지만 추가 후 종료
		        }
		    }
		}


	    	return movieVoList;
}
    
    
    
}

