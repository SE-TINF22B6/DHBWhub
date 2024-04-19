package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.mapper.views.ViewPostMapper;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringPostRepository;
import de.tinf22b6.dhbwhub.repository.interfaces_views.*;
import de.tinf22b6.dhbwhub.repository.views.ViewHomepagePostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private final SpringPostRepository postRepository;
    private final SpringViewFacGesundheitPostsRepository viewFacGesundheitPostsRepository;
    private final SpringViewFacWirtschaftPostsRepository viewFacWirtschaftPostsRepository;
    private final SpringViewFacTechnikPostsRepository viewFacTechnikPostsRepository;
    private final SpringViewHomepagePostsRepository viewHomepagePostsRepository;
    private final SpringViewCommentAmountRepository viewCommentAmountRepository;

    public PostRepository(@Autowired SpringPostRepository postRepository,
                          @Autowired SpringViewHomepagePostsRepository viewHomepagePostsRepository,
                          @Autowired SpringViewFacGesundheitPostsRepository viewFacGesundheitPostsRepository,
                          @Autowired SpringViewFacTechnikPostsRepository viewFacTechnikPostsRepository,
                          @Autowired SpringViewFacWirtschaftPostsRepository viewFacWirtschaftPostsRepository,
                          @Autowired SpringViewCommentAmountRepository viewCommentAmountRepository) {
        this.postRepository = postRepository;
        this.viewHomepagePostsRepository = viewHomepagePostsRepository;
        this.viewFacGesundheitPostsRepository = viewFacGesundheitPostsRepository;
        this.viewFacTechnikPostsRepository = viewFacTechnikPostsRepository;
        this.viewFacWirtschaftPostsRepository = viewFacWirtschaftPostsRepository;
        this.viewCommentAmountRepository = viewCommentAmountRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post find(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findHomepagePosts(){
        return viewHomepagePostsRepository.findAll().stream().map(ViewPostMapper::mapToModel).collect(Collectors.toList());
    }

    public List<Post> findPostsFromFacGesundheit(){
        return viewFacGesundheitPostsRepository.findAll().stream().map(ViewPostMapper::mapToModel).collect(Collectors.toList());
    }

    public List<Post> findPostsFromFacTechnik(){
        return viewFacTechnikPostsRepository.findAll().stream().map(ViewPostMapper::mapToModel).collect(Collectors.toList());
    }

    public List<Post> findPostsFromFacWirtschaft(){
        return viewFacWirtschaftPostsRepository.findAll().stream().map(ViewPostMapper::mapToModel).collect(Collectors.toList());
    }

    public int findAmountOfComments(Long id){
        return viewCommentAmountRepository.findById(id).get().getComment_amount();
    }
}
