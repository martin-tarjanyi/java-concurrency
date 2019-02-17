package org.learning.javaconcurrency.reactive;

import org.learning.javaconcurrency.service.JsonService;
import org.learning.javaconcurrency.service.ResponseUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Random;

@Component
public class ReactorService
{
	public Mono<String> getMono() {

		int userId = new Random().nextInt(10) + 1;

		Mono<String> postsMono = JsonService.getPostsReactive();
		Mono<String> commentsMono = JsonService.getCommentsReactive();
		Mono<String> albumsMono = JsonService.getAlbumsReactive();
		Mono<String> photosMono = JsonService.getPhotosReactive();

		Mono<String> postsAndCommentsMono = Mono.zip(postsMono, commentsMono,
				(posts, comments) -> ResponseUtil.getPostsAndCommentsOfRandomUser(userId, posts, comments));

		Mono<String> albumsAndPhotosMono = Mono.zip(albumsMono, photosMono,
				(albums, photos) -> ResponseUtil.getAlbumsAndPhotosOfRandomUser(userId, albums, photos));

		return Mono.zip(postsAndCommentsMono, albumsAndPhotosMono, (r1, r2) -> r1 + r2);
	}
}
