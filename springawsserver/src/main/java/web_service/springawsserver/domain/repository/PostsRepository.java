package web_service.springawsserver.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web_service.springawsserver.domain.entity.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}