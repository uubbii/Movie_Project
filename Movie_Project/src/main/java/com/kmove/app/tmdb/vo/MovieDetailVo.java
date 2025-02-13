package com.kmove.app.tmdb.vo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kmove.app.tmdb.vo.MovieDetailVo.PersonDetailVo.CastVo;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo.ActorVo;




@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailVo extends MovieResponseVo.MovieVo {
    private int budget;
    private List<Genre> genres;
    private List<ProductionCompany> production_companies;
    private String release_date;
    private int runtime;
    private double revenue;
    private List<Language> spoken_languages;
    
    private List<String> imageUrls;  // 이미지 파일 경로 리스트
    private List<String> videoUrls; // 비디오 파일 경로 리스트
    
    private List<ActorVo> actors; // 영화 배우 관련 정보리스트
    private List<CastVo> casts; // 영화 배우 관련 정보리스트
    
    public MovieDetailVo() {}
    
    





    public List<CastVo> getCasts() {
		return casts;
	}







	public void setCasts(List<CastVo> casts) {
		this.casts = casts;
	}







	public MovieDetailVo(int budget, List<Genre> genres, List<ProductionCompany> production_companies,
			String release_date, int runtime, double revenue, List<Language> spoken_languages, List<String> imageUrls,
			List<String> videoUrls, List<ActorVo> actors, List<CastVo> casts) {
		super();
		this.budget = budget;
		this.genres = genres;
		this.production_companies = production_companies;
		this.release_date = release_date;
		this.runtime = runtime;
		this.revenue = revenue;
		this.spoken_languages = spoken_languages;
		this.imageUrls = imageUrls;
		this.videoUrls = videoUrls;
		this.actors = actors;
		this.casts = casts;
	}




    @Override
    public String toString() {
        return "MovieDetailVo{" +
                "budget=" + budget +
                ", genres=" + (genres != null ? genres : "null") +
                ", production_companies=" + (production_companies != null ? production_companies : "null") +
                ", release_date='" + release_date + '\'' +
                ", runtime=" + runtime +
                ", revenue=" + revenue +
                ", spoken_languages=" + (spoken_languages != null ? spoken_languages : "null") +
                ", imageUrls=" + (imageUrls != null ? imageUrls : "null") +
                ", videoUrls=" + (videoUrls != null ? videoUrls : "null") +
                ", actors=" + (actors != null ? actors : "null") +
                ", casts=" + (casts != null ? casts : "null") +
                '}';
    }



    


	public List<ActorVo> getActors() {
		return actors;
	}









	public void setActors(List<ActorVo> actors) {
		this.actors = actors;
	}

    
    public List<String> getImageUrls() {
        return imageUrls; 
    }



	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}


	public List<String> getVideoUrls() {
	    return this.videoUrls;
	}



	public void setVideoUrls(List<String> videoUrls) {
		this.videoUrls = videoUrls;
	}


	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<ProductionCompany> getProduction_companies() {
		return production_companies;
	}

	public void setProduction_companies(List<ProductionCompany> production_companies) {
		this.production_companies = production_companies;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public List<Language> getSpoken_languages() {
		return spoken_languages;
	}

	public void setSpoken_languages(List<Language> spoken_languages) {
		this.spoken_languages = spoken_languages;
	}

	public static class Genre {
        private int id;
        private String name;
        
        public Genre() {}        
        
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "Genre [id=" + id + ", name=" + name + "]";
		}
		public Genre(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

    }

    public static class ProductionCompany {
        private int id;
        private String name;
        private String origin_country;
        
        public ProductionCompany() {}    
        
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getOrigin_country() {
			return origin_country;
		}
		public void setOrigin_country(String origin_country) {
			this.origin_country = origin_country;
		}
		@Override
		public String toString() {
			return "ProductionCompany [id=" + id + ", name=" + name + ", origin_country=" + origin_country + "]";
		}
		public ProductionCompany(int id, String name, String origin_country) {
			super();
			this.id = id;
			this.name = name;
			this.origin_country = origin_country;
		}
        
        
        
        
    }

    public static class Language {
        private String iso_639_1;
        private String name;
        
        public Language() {}
        
		public String getIso_639_1() {
			return iso_639_1;
		}
		public void setIso_639_1(String iso_639_1) {
			this.iso_639_1 = iso_639_1;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "Language [iso_639_1=" + iso_639_1 + ", name=" + name + "]";
		}
		public Language(String iso_639_1, String name) {
			super();
			this.iso_639_1 = iso_639_1;
			this.name = name;
		}
       
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieCredits {
        private List<MovieVo> cast;  // 출연작
        private List<MovieVo> crew;  // 제작 관련
        
        public MovieCredits() {}
        
		public List<MovieVo> getCast() {
			return cast;
		}
		public void setCast(List<MovieVo> cast) {
			this.cast = cast;
		}
		public List<MovieVo> getCrew() {
			return crew;
		}
		public void setCrew(List<MovieVo> crew) {
			this.crew = crew;
		}
		public MovieCredits(List<MovieVo> cast, List<MovieVo> crew) {
			super();
			this.cast = cast;
			this.crew = crew;
		}

        
        
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PersonDetailVo extends ActorVo {
    	
        private List<String> also_known_as; // 다른 이름들
        private String biography;
        private String birthday;
        private String deathday;
        private String homepage;
        private String imdb_id;
        private String place_of_birth;
        private List<CastVo> cast; // 출연한 영화 목록
        private List<CrewVo> crew;
        private MovieCredits movie_credits;
    	
        public PersonDetailVo() {}
        






		public PersonDetailVo(List<String> also_known_as, String biography, String birthday, String deathday,
				String homepage, String imdb_id, String place_of_birth, List<CastVo> cast, List<CrewVo> crew,
				MovieCredits movie_credits) {
			super();
			this.also_known_as = also_known_as;
			this.biography = biography;
			this.birthday = birthday;
			this.deathday = deathday;
			this.homepage = homepage;
			this.imdb_id = imdb_id;
			this.place_of_birth = place_of_birth;
			this.cast = cast;
			this.crew = crew;
			this.movie_credits = movie_credits;
		}







		public MovieCredits getMovie_credits() {
			return movie_credits;
		}







		public void setMovie_credits(MovieCredits movie_credits) {
			this.movie_credits = movie_credits;
		}







		public List<CastVo> getCast() {
			return cast;
		}




		public void setCast(List<CastVo> cast) {
			this.cast = cast;
		}




		public List<CrewVo> getCrew() {
			return crew;
		}


		public void setCrew(List<CrewVo> crew) {
			this.crew = crew;
		}


		public List<String> getAlso_known_as() {
			return also_known_as;
		}

		public void setAlso_known_as(List<String> also_known_as) {
			this.also_known_as = also_known_as;
		}

		public String getBiography() {
			return biography;
		}

		public void setBiography(String biography) {
			this.biography = biography;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getDeathday() {
			return deathday;
		}

		public void setDeathday(String deathday) {
			this.deathday = deathday;
		}

		public String getHomepage() {
			return homepage;
		}

		public void setHomepage(String homepage) {
			this.homepage = homepage;
		}

		public String getImdb_id() {
			return imdb_id;
		}

		public void setImdb_id(String imdb_id) {
			this.imdb_id = imdb_id;
		}

		public String getPlace_of_birth() {
			return place_of_birth;
		}

		public void setPlace_of_birth(String place_of_birth) {
			this.place_of_birth = place_of_birth;
		}

	    @JsonIgnoreProperties(ignoreUnknown = true)
		public static class CastVo extends ActorVo {
		    private String character;  // 역할
		    private String credit_id;  // 크레딧 ID
		    private int order;         // 출연 순서
		    private int id;            // 영화 ID
		    private String title;      // 영화 제목
		    private String release_date; // 개봉일
            
		    public CastVo () {}

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

			public String getRelease_date() {
				return release_date;
			}

			public void setRelease_date(String release_date) {
				this.release_date = release_date;
			}

			public CastVo(String character, String credit_id, int order, int id, String title, String release_date) {
				super();
				this.character = character;
				this.credit_id = credit_id;
				this.order = order;
				this.id = id;
				this.title = title;
				this.release_date = release_date;
			}
		    
		    
		    
		    
            
            
        }
	    
	    @JsonIgnoreProperties(ignoreUnknown = true)
        public static class CrewVo {
        	private String credit_id;   // 크레딧 ID
    	    private String department;  // 부서
    	    private String job;         // 직업
    	    private int id;             // 영화 ID
    	    private String title;       // 영화 제목
    	    private String release_date; // 개봉일


            public CrewVo() {}


			public String getCredit_id() {
				return credit_id;
			}


			public void setCredit_id(String credit_id) {
				this.credit_id = credit_id;
			}


			public String getDepartment() {
				return department;
			}


			public void setDepartment(String department) {
				this.department = department;
			}


			public String getJob() {
				return job;
			}


			public void setJob(String job) {
				this.job = job;
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


			public String getRelease_date() {
				return release_date;
			}


			public void setRelease_date(String release_date) {
				this.release_date = release_date;
			}


			public CrewVo(String credit_id, String department, String job, int id, String title, String release_date) {
				super();
				this.credit_id = credit_id;
				this.department = department;
				this.job = job;
				this.id = id;
				this.title = title;
				this.release_date = release_date;
			}

            
            
            
            
            
        }
    }

    
    
}
