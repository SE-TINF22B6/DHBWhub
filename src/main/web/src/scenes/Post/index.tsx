import React, {useState, useEffect, useRef} from "react";
import {Link} from "react-router-dom";
import { Header } from "../../organisms/header/Header";
import { PostDetail } from "./PostDetail";
import {CreateComment} from "../../organisms/create-comment/CreateComment";
import "./index.css";
import animationData from "../../assets/loading.json";
import Lottie from "lottie-react";
import {Footer} from "../../organisms/footer/Footer";
import ScrollUpButton from "../../atoms/ScrollUpButton";
import {dummyComments} from "./data/dummyComments";
import {dummyPost} from "./data/dummyPost";
import {PostDetailModel} from "./models/PostDetailModel";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import {PostThreadModel} from "./models/PostThreadModel";
import {dummyPostThread} from "./data/dummyPostThread";
import config from "../../config/config";
import {CommentModel} from "./models/CommentModel";
import {CommentProposalModel} from "./models/CommentProposalModel";

export const Post: React.FC = () => {
  const searchParams = new URLSearchParams(window.location.search);
  const postId = searchParams.get('id');
  const [post, setPost] = useState<PostDetailModel>(dummyPost);
  const [comments, setComments] = useState<CommentModel[]>(dummyComments);
  const [postThread, setPostThread] = useState<PostThreadModel[]>();
  const [notFound, setNotFound] = useState(false);
  const [loading, setLoading] = useState(true);


  const isSmartphoneSize = useMediaQuery('(max-width: 412px)');

  const [scrollToComments, setScrollToComments] = useState(false);

  useEffect((): void => {
    if (scrollToComments && commentsWrapperRef.current) {
      commentsWrapperRef.current.scrollIntoView({ behavior: "smooth" });
    }
    setScrollToComments(false);
  }, [scrollToComments]);

  const scrollUpRef = useRef<HTMLDivElement>(null);

  useEffect((): void => {
    const fetchPostThread = async (): Promise<void> => {
      try {
        const response = await fetch(config.apiUrl + `post/post-thread/${postId}`);
        if (response.ok) {
          const postData = await response.json();
          console.log(postData);
          setPostThread(postData);
        } else {
          setNotFound(true);
        }
        setLoading(false);
      } catch (error) {
        console.error("Error when retrieving the post thread:", error);
        setNotFound(true);
        setLoading(false);
      }
    };
    fetchPostThread();
  }, [postId]);

  if (loading) {
    return (
        <div className="post-detail-component">
          <Header/>
          <div className="loading-animation">
            <Lottie animationData={animationData}/>
          </div>
          <Footer/>
          {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
  }

  const handleReplyClick = async (newCommentText: string) => {
    const commentProposal: CommentProposalModel = {
      postId: 3,
      text: newCommentText,
      accountId: 5,
      timestamp: Math.floor(new Date().getTime()),
    };

    try {
      const response: Response = await fetch(config.apiUrl + `post/create-comment}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(commentProposal)
      });

      if (response.ok) {
        const newComment = await response.json();
        setComments([...comments, newComment]);
      } else {
        setNotFound(true);
      }
      setLoading(false);
    } catch (error) {
      console.error("Error when creating the comment:", error);
      setNotFound(true);
      setLoading(false);
    }
  };

  return (
      <div className="post-detail-component">
        <div ref={scrollUpRef}/>
        <Header/>
        <Link to="/" className="navigate-back-link">
          <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-vector.svg'} className="navigate-back-vector"/>
          <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-rectangle.svg'} className="navigate-back-rectangle"/>
          <div className="navigate-back-text">Post</div>
        </Link>
        <div className="post-detail-body">
          <PostDetail
              id={post.id}
              title={post.title}
              description={post.description}
              tags={post.tags}
              likeAmount={post.likeAmount}
              commentAmount={post.commentAmount}
              timestamp={post.timestamp}
              image={post.image}
              accountId={post.accountId}
              username={post.username}
              userImage={post.userImage}
              setScrollToComments={setScrollToComments}
          />
          <CreateComment onReplyClick={handleReplyClick}/>
          <ScrollUpButton scrollUpRef={scrollUpRef}/>
        </div>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};
