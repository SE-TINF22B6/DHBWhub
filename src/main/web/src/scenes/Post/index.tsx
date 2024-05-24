import React, {useState, useEffect, useRef} from "react";
import {Link} from "react-router-dom";
import { Header } from "../../organisms/header/Header";
import { PostDetail } from "./PostDetail";
import {CreateComment} from "../../organisms/create-comment/CreateComment";
import {Comment} from "../../organisms/comment/Comment";
import animationData from "../../assets/loading.json";
import errorAnimationData from "../../assets/error.json";
import Lottie from "lottie-react";
import {Footer} from "../../organisms/footer/Footer";
import ScrollUpButton from "../../atoms/ScrollUpButton";
import {dummyComments} from "./data/dummyComments";
import {dummyPost} from "./data/dummyPost";
import {PostDetailModel} from "./models/PostDetailModel";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import {PostThreadModel} from "./models/PostThreadModel";
import {CommentModel} from "../../organisms/comment/CommentModel";
import {CommentProposalModel} from "./models/CommentProposalModel";
import {getJWT} from "../../services/AuthService";
import {dummyPostThread} from "./data/dummyPostThread";
import {dummyComments} from "./data/dummyComments";
import config from "../../config/config";
import "./index.css";

export const Post: React.FC = () => {
  const searchParams: URLSearchParams = new URLSearchParams(window.location.search);
  const postId: string | null = searchParams.get('id');
  const [comments, setComments] = useState<CommentModel[]>(dummyComments);
  const [postThread, setPostThread] = useState<PostThreadModel>(dummyPostThread);
  const [notFound, setNotFound] = useState(false);
  const [loading, setLoading] = useState(true);
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  const isSmartphoneSize = useMediaQuery('(max-width: 412px)');
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

  const [scrollToComments, setScrollToComments] = useState(false);
  const commentsWrapperRef = useRef<HTMLDivElement>(null);
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
        const response: Response = await fetch(config.apiUrl + `post/post-thread/${postId}`, {
          headers: headersWithJwt
        });
        if (response.ok) {
          const postThreadData = await response.json();
          console.log(postThreadData);
          setPostThread(postThreadData);
          setComments(postThreadData.comments);
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

  const handleReplyClick = async (newCommentText: string): Promise<void> => {
    const commentProposal: CommentProposalModel = {
      postId: 3,
      text: newCommentText,
      accountId: 5,
      timestamp: Math.floor(new Date().getTime()),
    };

    try {
      const response: Response = await fetch(config.apiUrl + `post/create-comment`, {
        method: 'POST',
        headers: headersWithJwt,
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

  if (notFound || !postThread) {
     return (
         <div className="post-detail-component">
           <Header/>
           <div className="error-animation">
             <Lottie animationData={errorAnimationData}/>
           </div>
           <Footer/>
           {isSmartphoneSize && <MobileFooter/>}
         </div>
     );
   }

  return (
      <div className="post-detail-component">
        <div ref={scrollUpRef}/>
        <Header/>
        <Link to="/" className="navigate-back-link">
          <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-vector.svg'}
               className="navigate-back-vector"/>
          <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-rectangle.svg'}
               className="navigate-back-rectangle"/>
          <div className="navigate-back-text">Post</div>
        </Link>
        <div className="post-detail-body">
          <PostDetail
              id={postThread.id}
              title={postThread.title}
              description={postThread.description}
              tags={postThread.tags}
              likeAmount={postThread.likeAmount}
              commentAmount={postThread.commentAmount}
              timestamp={postThread.timestamp}
              postImage={postThread.postImage}
              accountId={postThread.accountId}
              username={postThread.username}
              userImage={postThread.userImage}
              setScrollToComments={setScrollToComments}
          />
          <CreateComment onReplyClick={handleReplyClick}/>
          <div ref={commentsWrapperRef} className="post-detail-comments-wrapper">
            {comments
            .slice()
            .sort((a: CommentModel, b: CommentModel): number => {
              const dateA: Date = new Date(a.timestamp);
              const dateB: Date = new Date(b.timestamp);
              if (dateA > dateB) return -1;
              if (dateA < dateB) return 1;
              return 0;
            })
            .map((comment: CommentModel) => (
                <Comment
                    key={comment.commentId}
                    postId={comment.postId}
                    commentId={comment.commentId}
                    text={comment.text}
                    authorUsername={comment.authorUsername}
                    accountId={comment.accountId}
                    authorImage={comment.authorImage}
                    timestamp={comment.timestamp}
                    likeAmount={comment.likeAmount}
                />
            ))}
          </div>
          <ScrollUpButton scrollUpRef={scrollUpRef}/>
        </div>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};