package org.learning.javaconcurrency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class JsonService {

	private static final Logger LOG = LoggerFactory.getLogger(JsonService.class);
	private static final RestTemplate restTemplate = new RestTemplate();
	private static final WebClient WEB_CLIENT = WebClient.create();
	private static final String USER_API = "https://jsonplaceholder.typicode.com/users";
	//JSON Place Holder
//	private static final String POSTS_API = "https://jsonplaceholder.typicode.com/posts";
//	private static final String COMMENTS_API = "https://jsonplaceholder.typicode.com/comments";
//	private static final String ALBUMS_API = "https://jsonplaceholder.typicode.com/albums";
//	private static final String PHOTOS_API = "https://jsonplaceholder.typicode.com/photos";
	//Mocky.io to add delay in response time
	private static final String POSTS_API = "http://www.mocky.io/v2/5c3ddcc235000012003e9646?mocky-delay=500ms";
	private static final String COMMENTS_API = "https://jsonplaceholder.typicode.com/comments";
	private static final String ALBUMS_API = "http://www.mocky.io/v2/5c3ddf1c3500002d003e9651?mocky-delay=400ms";
	private static final String PHOTOS_API = "https://jsonplaceholder.typicode.com/photos";

	public static String getUsers() {

		long startTime = System.currentTimeMillis();

		String result = restTemplate.getForObject(USER_API, String.class);

		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		LOG.info("Time Taken for JSON Service getUsers :: " + timeTaken + " - in Thread "
				+ Thread.currentThread().getName());

		return result;
	}

	public static String getPosts() {

		long startTime = System.currentTimeMillis();

		String result = restTemplate.getForObject(POSTS_API, String.class);

		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		LOG.info("Time Taken for JSON Service getPosts :: " + timeTaken + " - in Thread "
				+ Thread.currentThread().getName());

		return result;
	}

	public static String getComments() {

		long startTime = System.currentTimeMillis();

		String result = restTemplate.getForObject(COMMENTS_API, String.class);

		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		LOG.info("Time Taken for JSON Service getComments :: " + timeTaken + " - in Thread "
				+ Thread.currentThread().getName());

		return result;
	}

	public static String getAlbums() {

		long startTime = System.currentTimeMillis();

		String result = restTemplate.getForObject(ALBUMS_API, String.class);

		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		LOG.info("Time Taken for JSON Service getAlbums :: " + timeTaken + " - in Thread "
				+ Thread.currentThread().getName());

		return result;
	}

	public static String getPhotos() {

		long startTime = System.currentTimeMillis();

		String result = restTemplate.getForObject(PHOTOS_API, String.class);

		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		LOG.info("Time Taken for JSON Service getPhotos :: " + timeTaken + " - in Thread "
				+ Thread.currentThread().getName());

		return result;
	}

	public static Mono<String> getUsersReactive() {

		return callApiReactive(USER_API, "Time Taken for JSON Service getUsers :: ");
	}

	public static Mono<String> getCommentsReactive() {

		return callApiReactive(COMMENTS_API, "Time Taken for JSON Service getComments :: ");
	}

	public static Mono<String> getPostsReactive() {

		return callApiReactive(POSTS_API, "Time Taken for JSON Service getPosts :: ");
	}

	public static Mono<String> getAlbumsReactive() {

		return callApiReactive(ALBUMS_API, "Time Taken for JSON Service getAlbums :: ");
	}

	public static Mono<String> getPhotosReactive() {

		return callApiReactive(PHOTOS_API, "Time Taken for JSON Service getPhotos :: ");
	}


	private static Mono<String> callApiReactive(String commentsApi, String s)
	{
		return WEB_CLIENT.get()
						 .uri(commentsApi)
						 .retrieve()
						 .bodyToMono(String.class)
						 .elapsed()
						 .doOnNext(
								 tuple -> LOG.info(
										 s + tuple.getT1() + " - in Thread "
												 + Thread.currentThread().getName()))
						 .flatMap(tuple -> Mono.just(tuple.getT2()));
	}
}
