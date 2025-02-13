package com.kmove.app.tmdb.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo.ActorVo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponseVo {
    private int page;
    private List<MovieVo> results;
    private List<ActorVo> actorResults; 
    private int total_pages;  
    private int total_results;

     
    
    public MovieResponseVo(){}

    






	public MovieResponseVo(int page, List<MovieVo> results, List<ActorVo> actorResults, int total_pages,
			int total_results) {
		super();
		this.page = page;
		this.results = results;
		this.actorResults = actorResults;
		this.total_pages = total_pages;
		this.total_results = total_results;

	}






	@Override
    public String toString() {
        return "MovieResponseVo {\n" +
               "  Page=" + page + ",\n" +
               "  Total Pages=" + total_pages + ",\n" +
               "  Total Results=" + total_results + ",\n" +
               "  Results=" + (results != null ? results.toString() : "null") + ",\n" +
               "  Actor Results=" + (actorResults != null ? actorResults.toString() : "null") + ",\n" +
               "}";
    }



	public int getPage() {
		return page;
	}








	public void setPage(int page) {
		this.page = page;
	}








	public List<MovieVo> getResults() {
		return results;
	}








	public void setResults(List<MovieVo> results) {
		this.results = results;
	}








	public List<ActorVo> getActorResults() {
		return actorResults;
	}








	public void setActorResults(List<ActorVo> actorResults) {
		this.actorResults = actorResults;
	}








	public int getTotal_pages() {
		return total_pages;
	}








	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}








	public int getTotal_results() {
		return total_results;
	}








	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}






	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MovieVo {
        private String backdrop_path;
        private int id;
        private String title;
        private String original_title;
        private String overview;
        private String poster_path;
        private String media_type;
        private boolean adult;
        private String original_language;
        private List<Integer> genre_ids;
        private List<String> genrename;
        private double popularity;
        private String release_date;
        private boolean video;
        private double vote_average;
        private int vote_count;
        private String character;
        private String credit_id;
        private int order;

        
        
        public MovieVo() {}
        
    

		public String getCharacter() {
			return character;
		}

		public void setCharacter(String character) {
			this.character = character;
		}


		public String getCredit_id() {
			return credit_id;
		}



		public void setCredit_id(String credit_id) {
			this.credit_id = credit_id;
		}



		public int getOrder() {
			return order;
		}


		public void setOrder(int order) {
			this.order = order;
		}






		public MovieVo(String backdrop_path, int id, String title, String original_title, String overview,
				String poster_path, String media_type, boolean adult, String original_language, List<Integer> genre_ids,
				List<String> genrename, double popularity, String release_date, boolean video, double vote_average,
				int vote_count, String character, String credit_id, int order) {
			super();
			this.backdrop_path = backdrop_path;
			this.id = id;
			this.title = title;
			this.original_title = original_title;
			this.overview = overview;
			this.poster_path = poster_path;
			this.media_type = media_type;
			this.adult = adult;
			this.original_language = original_language;
			this.genre_ids = genre_ids;
			this.genrename = genrename;
			this.popularity = popularity;
			this.release_date = release_date;
			this.video = video;
			this.vote_average = vote_average;
			this.vote_count = vote_count;
			this.character = character;
			this.credit_id = credit_id;
			this.order = order;
		}



		public List<String> getGenrename() {
			return genrename;
		}



		public void setGenrename(List<String> genrename) {
			this.genrename = genrename;
		}



		public String getBackdrop_path() {
			return "https://image.tmdb.org/t/p/original/"+backdrop_path;
		}
		public void setBackdrop_path(String backdrop_path) {
			this.backdrop_path = backdrop_path;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getOriginal_title() {
			return original_title;
		}
		public void setOriginal_title(String original_title) {
			this.original_title = original_title;
		}
		public String getOverview() {
			return overview;
		}
		public void setOverview(String overview) {
			this.overview = overview;
		}
		public String getPoster_path() {
			return "https://image.tmdb.org/t/p/w500" + poster_path;
		}
		public void setPoster_path(String poster_path) {
			this.poster_path = poster_path;
		}
		public String getMedia_type() {
			return media_type;
		}
		public void setMedia_type(String media_type) {
			this.media_type = media_type;
		}
		public boolean isAdult() {
			return adult;
		}
		public void setAdult(boolean adult) {
			this.adult = adult;
		}
		public String getOriginal_language() {
			return original_language;
		}
		public void setOriginal_language(String original_language) {
			this.original_language = original_language;
		}
		public List<Integer> getGenre_ids() {
			return genre_ids;
		}
		public void setGenre_ids(List<Integer> genre_ids) {
			this.genre_ids = genre_ids;
		}
		public double getPopularity() {
			return popularity;
		}
		public void setPopularity(double popularity) {
			this.popularity = popularity;
		}
		public String getRelease_date() {
			return release_date;
		}
		public void setRelease_date(String release_date) {
			this.release_date = release_date;
		}
		public boolean isVideo() {
			return video;
		}
		public void setVideo(boolean video) {
			this.video = video;
		}
		public double getVote_average() {
			return vote_average;
		}
		public void setVote_average(double vote_average) {
			this.vote_average = vote_average;
		}
		public int getVote_count() {
			return vote_count;
		}
		public void setVote_count(int vote_count) {
			this.vote_count = vote_count;
		}






		@Override
		public String toString() {
		    StringBuilder sb = new StringBuilder();
		    
		    sb.append("MovieVo {")
		      .append("\n  backdrop_path='").append(backdrop_path).append('\'')
		      .append(",\n  id=").append(id)
		      .append(",\n  title='").append(title).append('\'')
		      .append(",\n  original_title='").append(original_title).append('\'')
		      .append(",\n  overview='").append(overview).append('\'')
		      .append(",\n  poster_path='").append(poster_path).append('\'')
		      .append(",\n  media_type='").append(media_type).append('\'')
		      .append(",\n  adult=").append(adult)
		      .append(",\n  original_language='").append(original_language).append('\'')
		      .append(",\n  genre_ids=").append(genre_ids)
		      .append(",\n  genrename=").append(genrename)
		      .append(",\n  popularity=").append(popularity)
		      .append(",\n  release_date='").append(release_date).append('\'')
		      .append(",\n  video=").append(video)
		      .append(",\n  vote_average=").append(vote_average)
		      .append(",\n  vote_count=").append(vote_count)
		      .append(",\n  character='").append(character).append('\'')
		      .append(",\n  credit_id='").append(credit_id).append('\'')
		      .append(",\n  order=").append(order)
		      .append("\n}");

		    return sb.toString();
		}







		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class ActorVo extends MovieVo {
		    private boolean adult;
		    private int gender;
		    private int id;
		    private String known_for_department;
		    private String name;
		    private String original_name;
		    private double popularity;
		    private String profile_path;
		    private List<MovieVo> known_for; 		   


		    
		    
		    
		    
		    public ActorVo() {}

		    @Override
		    public String toString() {
		        StringBuilder sb = new StringBuilder();
		        
		        sb.append("Person {")
		          .append("\n  adult=").append(adult)
		          .append(", \n  gender=").append(gender)
		          .append(", \n  id=").append(id)
		          .append(", \n  known_for_department='").append(known_for_department).append('\'')
		          .append(", \n  name='").append(name).append('\'')
		          .append(", \n  original_name='").append(original_name).append('\'')
		          .append(", \n  popularity=").append(popularity)
		          .append(", \n  profile_path='").append(profile_path).append('\'')
		          .append(", \n  known_for=").append(known_for != null ? known_for.size() : 0).append(" items") // known_for 리스트 크기
		          .append("\n}");

		        return sb.toString();
		    }
		    
		    
		    
		    
		    public boolean isAdult() {
		        return adult;
		    }

		    public void setAdult(boolean adult) {
		        this.adult = adult;
		    }

		    public int getGender() {
		        return gender;
		    }

		    public void setGender(int gender) {
		        this.gender = gender;
		    }

		    public int getId() {
		        return id;
		    }

		    public void setId(int id) {
		        this.id = id;
		    }

		    public String getKnown_for_department() {
		        return known_for_department;
		    }

		    public void setKnown_for_department(String known_for_department) {
		        this.known_for_department = known_for_department;
		    }

		    public String getName() {
		        return name;
		    }

		    public void setName(String name) {
		        this.name = name;
		    }

		    public String getOriginal_name() {
		        return original_name;
		    }

		    public void setOriginal_name(String original_name) {
		        this.original_name = original_name;
		    }

		    public double getPopularity() {
		        return popularity;
		    }

		    public void setPopularity(double popularity) {
		        this.popularity = popularity;
		    }

		    public String getProfile_path() {
		        return "https://image.tmdb.org/t/p/w500" + profile_path;
		    }

		    public void setProfile_path(String profile_path) {
		        this.profile_path = profile_path;
		    }

		    public List<MovieVo> getKnown_for() {
		        return known_for;
		    }

		    public void setKnown_for(List<MovieVo> known_for) {
		        this.known_for = known_for;
		    }


			public ActorVo(boolean adult, int gender, int id, String known_for_department, String name,
					String original_name, double popularity, String profile_path, List<MovieVo> known_for) {
				super();
				this.adult = adult;
				this.gender = gender;
				this.id = id;
				this.known_for_department = known_for_department;
				this.name = name;
				this.original_name = original_name;
				this.popularity = popularity;
				this.profile_path = profile_path;
				this.known_for = known_for;

			}

		    
		    


		}


        
    }
}
