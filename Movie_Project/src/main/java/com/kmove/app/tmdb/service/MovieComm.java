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
	 * MovieComm���� ����� genreCache�� ���ؼ� API�� ��� �ҷ����� ���� �ƴ϶�
	 * ���� 1ȸ ���� ��, ������ MapŸ���� genreCache�� �����մϴ�.
	 * */
    public static Map<Integer, String> genreCache = new HashMap<Integer, String>();
	
    /**API���� �帣 ����� �ҷ��ɴϴ�. (���� ���� �� 1ȸ�� ���)
     * �ѹ� �ҷ��� �� genreCache�� �����ؼ� ��� ����մϴ�.
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
      * �帣 ID -> �帣 �̸� ��ȯ MovieComm �޼ҵ�
      * @param genreIds Integer������ ����Ʈ�� �޾ƿɴϴ�.
      * 
      * @return Integer�� �̷���� ����Ʈ(�帣)�� String���� ��ȯ ��, ��ȯ�մϴ�.
      * */
    public static List<String> getGenreNames(List<Integer> genreIds) throws Exception {
     	
//     	System.out.println("\n Genre Cache: " + genreIds + "\n" );
         // �帣 �����͸� �̹� ĳ���س��Ҵٸ� ĳ�õ� �����͸� ���
         if (!genreCache.isEmpty()) {
             List<String> genreNames = new ArrayList<String>();
             
             // genreIds�� ���Ե� �帣 ID�� �ش��ϴ� �帣 �̸��� genreCache���� ã�� �߰�
             for (Integer genreId : genreIds) {
                 String genreName = genreCache.get(genreId); // genreCache���� genreId�� �ش��ϴ� �帣 �̸� ã��
                 if (genreName != null) {
                     genreNames.add(genreName);  // null�� �ƴϸ� �帣 �̸��� ����Ʈ�� �߰�
                 } else {
                     genreNames.add("Unknown");  // API�� ��ϵ��� �ʾ��� ��� "Unknown"�� �߰�
                 }
             }
             
             return genreNames;
         } else {
             throw new Exception("�帣�� NULL ����!!! ���� ����� �� ��");
         }
     }
    
    
 /** JSON �����͸� Ʈ�� ���·� ����ϴ� �޼����Դϴ�.(�׳� print�� ����, Json ���� Ȯ�ο�) 
  * @param node ���ϴ� JsonNode
  * @param �Է¸�
  * 
  * 
  * */
    public static void printJsonTree(JsonNode node, String indent) {
        if (node.isObject()) { // JSON ��ü�� ���
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                printJsonTree(entry.getValue(), indent + "  ");
            }
        } else if (node.isArray()) { // JSON �迭�� ���
            int index = 0;
            for (JsonNode arrayNode : node) {
                printJsonTree(arrayNode, indent + "  ");
                index++;
            }
        } else { // ���� ���� ���
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
		            break; // �ִ� 5�������� �߰� �� ����
		        }
		    }
		}


	    	return movieVoList;
}
    
    
    
}

