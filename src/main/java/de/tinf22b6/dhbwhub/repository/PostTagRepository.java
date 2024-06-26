package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.PostTag;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringPostTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostTagRepository {
    private final SpringPostTagRepository repository;

    public PostTagRepository(@Autowired SpringPostTagRepository repository) {
        this.repository = repository;
    }

    public List<PostTag> findAll() {
        return repository.findAll();
    }

    public PostTag find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PostTag save(PostTag postTag) {
        return repository.save(postTag);
    }

    public void save(List<PostTag> postTag) {
        postTag.forEach(this::save);
    }

    public List<PostTag> findByPostId(Long postId) {
        return repository.findByPostId(postId);
    }

    public PostTag findByPostIdAndTag(Long id, String tag) {
        return repository.findByPostIdAndTag(id, tag);
    }

    public List<PostTag> findByTag(String tag) {
        return repository.findByTag(tag);
    }

    public List<String> findPopularTags(){
        return repository.findPopularTags().stream().limit(10).toList();
    }

    public List<PostTag> findTagByKeyword(String keyword) {
        return repository.findByTagContainingIgnoreCase(keyword);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
