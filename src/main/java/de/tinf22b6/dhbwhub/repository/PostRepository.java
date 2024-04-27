package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.mapper.CommentMapper;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.PostTag;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.CommentThreadViewProposal;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class PostRepository {
    private final SpringPostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostTagRepository postTagRepository;

    public PostRepository(@Autowired SpringPostRepository postRepository,
                          @Autowired CommentRepository commentRepository,
                          @Autowired PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.postTagRepository = postTagRepository;
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
        return postRepository.getHomepagePosts();
    }

    public List<Post> findPostsFromFacGesundheit(){
        return postRepository.getFacGesundheitPosts();
    }

    public List<Post> findPostsFromFacTechnik(){
        return postRepository.getFacTechnikPosts();
    }

    public List<Post> findPostsFromFacWirtschaft(){
        return postRepository.getFacWirtschaftPosts();
    }

    public int getAmountOfComments(Long id){
        Integer commentAmount = postRepository.getCommentAmount(id);
        return commentAmount != null ? commentAmount : 0;
    }

    public List<CommentThreadViewProposal> getPostComments(Long id) {
        List<Comment> postComments = commentRepository.findByPostId(id);
        if (postComments == null){
            return Collections.emptyList();
        }
        return postComments.stream().map(CommentMapper::mapToThreadView).toList();
    }

    public List<String> getPostTags(Long id) {
       List<PostTag> postTags = postTagRepository.findByPostId(id);
       if (postTags == null){
           return Collections.emptyList();
       }
        return postTags.stream().map(PostTag::getTag).toList();
    }


}
