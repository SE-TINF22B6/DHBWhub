import React, { useState, useEffect, useRef, useMemo } from "react";
import { Link } from "react-router-dom";
import { Header } from "../../organisms/header/Header";
import { PostDetail } from "./PostDetail";
import { CreateComment } from "../../organisms/create-comment/CreateComment";
import { Comment } from "../../organisms/comment/Comment";
import animationData from "../../assets/loading.json";
import Lottie from "lottie-react";
import { Footer } from "../../organisms/footer/Footer";
import ScrollUpButton from "../../atoms/ScrollUpButton";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import { useDetectAdBlock } from "adblock-detect-react";
import { PrimeAd } from "../../atoms/ads/PrimeAd";
import { usePreventScrolling } from "../../organisms/ad-block-overlay/preventScrolling";
import { MobileFooter } from "../../organisms/header/MobileFooter";
import { useMediaQuery } from "@mui/system";
import { PostThreadModel } from "./models/PostThreadModel";
import { PostCommentModel } from "../Home/components/post/models/PostCommentModel";
import { getAccountId, getJWT } from "../../services/AuthService";
import config from "../../config/config";
import "./index.css";
import { PostCommentProposalModel } from "./models/PostCommentProposalModel";

export const Post = () => {
  const searchParams: URLSearchParams = new URLSearchParams(window.location.search);
  const idString: string | null = searchParams.get('id');
  const postId: number | null = idString !== null ? parseInt(idString) : null;
  const [comments, setComments] = useState<PostCommentModel[]>([]);
  const [postThread, setPostThread] = useState<PostThreadModel | null>(null);
  const [notFound, setNotFound] = useState(false);
  const [loading, setLoading] = useState(true);
  const jwt: string | null = getJWT();
  const headersWithJwt = useMemo(() => ({
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  }), [jwt]);
  const accountId: number | null = getAccountId();

  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);

  const [scrollToComments, setScrollToComments] = useState(false);
  const commentsWrapperRef = useRef<HTMLDivElement>(null);
  useEffect((): void => {
    if (scrollToComments && commentsWrapperRef.current) {
      commentsWrapperRef.current.scrollIntoView({ behavior: "smooth" });
    }
    setScrollToComments(false);
  }, [scrollToComments]);
  const scrollUpRef = useRef<HTMLDivElement>(null);
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

  useEffect((): void => {
    const fetchPostThread = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + `post/post-thread/${postId}`, {
          headers: headersWithJwt
        });
        if (response.ok) {
          const postThread = await response.json();
          setPostThread(postThread);
          setComments(postThread.comments);
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
  }, [postId, headersWithJwt]);

  const handleReplyClick = async (newCommentText: string): Promise<void> => {
    if (postId == null) {
      console.error("Post ID null");
      return;
    }

    if (accountId == null) {
      console.error("Account ID null");
      return;
    }

    const commentProposal: PostCommentProposalModel = {
      postId: postId,
      accountId: accountId,
      description: newCommentText,
      timestamp: Math.floor(new Date().getTime()),
    };

    try {
      const response: Response = await fetch(config.apiUrl + `comment/create-comment`, {
        method: 'POST',
        headers: headersWithJwt,
        body: JSON.stringify(commentProposal)
      });

      if (response.ok) {
        const newComment = await response.json();
        setComments(prevComments => [...prevComments, newComment]);
      } else {
        console.error("Failed to create the comment");
        console.error("Response:", response);
      }
      setLoading(false);
    } catch (error) {
      console.error("Error when creating the comment:", error);
      setLoading(false);
    }
  };

  if (loading) {
    return (
        <div className="page">
          {adBlockDetected && <AdBlockOverlay/>}
          <Header/>
          <Link to="/" className="navigate-back-link">
            <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-vector.svg'}
                 className="navigate-back-vector"/>
            <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-rectangle.svg'}
                 className="navigate-back-rectangle"/>
            <div className="navigate-back-text">Post</div>
          </Link>
          <div className="loading-animation">
            <Lottie animationData={animationData}/>
          </div>
          <Footer/>
          {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
  }

  if (notFound) {
    return (
        <div className="page">
          <Header/>
          <Link to="/" className="navigate-back-link">
            <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-vector.svg'}
                 className="navigate-back-vector"/>
            <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-rectangle.svg'}
                 className="navigate-back-rectangle"/>
            <div className="navigate-back-text">Post</div>
          </Link>
          <div className="loading">
            Post not found
          </div>
          <Footer/>
          {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
  }

  return (
      postThread && (
          <div className="page">
            {adBlockDetected && <AdBlockOverlay/>}
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
              {comments && (
                  <div ref={commentsWrapperRef} className="post-detail-comments-wrapper">
                    {comments
                    .slice()
                    .sort((a: PostCommentModel, b: PostCommentModel): number => {
                      const dateA: Date = new Date(a.timestamp);
                      const dateB: Date = new Date(b.timestamp);
                      if (dateA > dateB) return -1;
                      if (dateA < dateB) return 1;
                      return 0;
                    })
                    .map((comment: PostCommentModel) => (
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
                            type={"comment"}
                        />
                    ))}
                  </div>
              )
              }
              <PrimeAd/>
              <ScrollUpButton scrollUpRef={scrollUpRef}/>
            </div>
            <Footer/>
            {isSmartphoneSize && <MobileFooter/>}
          </div>
      )
  );
};