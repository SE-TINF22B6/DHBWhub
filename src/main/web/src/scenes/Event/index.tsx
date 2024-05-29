import React, {useState, useEffect, useRef} from "react";
import {Link, useLocation} from "react-router-dom";
import { Header } from "../../organisms/header/Header";
import { EventDetail } from "./components/EventDetail";
import {CreateComment} from "../../organisms/create-comment/CreateComment";
import {Comment} from "../../organisms/comment/Comment";
import "./index.css";
import loadingAnimationData from "../../assets/loading.json";
import Lottie from "lottie-react";
import {Footer} from "../../organisms/footer/Footer";
import ScrollUpButton from "../../atoms/ScrollUpButton";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {dummyEvent} from "./data/dummyEvent";
import {PrimeAd} from "../../atoms/ads/PrimeAd";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import config from "../../config/config";
import {getJWT} from "../../services/AuthService";
import {dummyComments} from "./data/dummyComments";
import {EventCommentModel} from "./model/EventCommentModel";
import {CommentProposalModel} from "../Post/models/CommentProposalModel";

export const Event = () => {
  const location = useLocation();
  const [event, setEvent] = useState(dummyEvent);
  const eventId = location.pathname.split('event/').pop();
  const [post, setPost] = useState(null);
  const [notFound, setNotFound] = useState(false);
  const [loading, setLoading] = useState(true);
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  const [comments, setComments] = useState<EventCommentModel[]>(dummyComments);
  const scrollUpRef = useRef<HTMLDivElement>(null);
  const isSmartphoneSize: boolean  = useMediaQuery('(max-width: 412px)');

  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);

  useEffect((): void => {
    const fetchEvent = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + `event/?id=${eventId}`, {
          headers: headersWithJwt
        });
        if (response.ok) {
          const eventData = await response.json();
          setPost(eventData);
          setComments(eventData.comments);
        } else {
          setNotFound(true);
        }
        setLoading(false);
      } catch (error) {
        console.error("Fehler beim Abrufen des Events:", error);
        setNotFound(true);
        setLoading(false);
      }
    };
    fetchEvent();
  }, [eventId]);

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
      setLoading(false);
    }
  };

  if (loading) {
    return (
        <div className="page">
          {adBlockDetected && <AdBlockOverlay/>}
          <Header/>
          <div className="loading-animation">
            <Lottie animationData={loadingAnimationData}/>
          </div>
          <Footer/>
          {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
  }

  return (
      <div className="page">
        {adBlockDetected && <AdBlockOverlay/>}
        <div ref={scrollUpRef}/>
        <Header/>
        <Link to="/" className="navigate-back-link">
          <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-vector.svg'} className="navigate-back-vector"/>
          <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-rectangle.svg'}
               className="navigate-back-rectangle"/>
          <div className="navigate-back-text">Event</div>
        </Link>
        <div className="event-detail-body">
          <EventDetail
              id={event.id}
              title={event.title}
              description={event.description}
              tags={event.tags}
              locationProposal={event.locationProposal}
              likeAmount={event.likeAmount}
              commentAmount={event.commentAmount}
              startDate={event.startDate}
              endDate={event.endDate}
              comments={event.comments}
          />
          <CreateComment onReplyClick={handleReplyClick}/>
          <div className="event-detail-comments-wrapper">
            {comments
            .slice()
            .sort((a: EventCommentModel, b: EventCommentModel): number => {
              const dateA: Date = new Date(a.timestamp);
              const dateB: Date = new Date(b.timestamp);
              if (dateA > dateB) return -1;
              if (dateA < dateB) return 1;
              return 0;
            })
            .map((comment: EventCommentModel) => (
                <Comment
                    key={comment.commentId}
                    postId={comment.eventId}
                    commentId={comment.commentId}
                    text={comment.text}
                    authorUsername={comment.authorUsername}
                    authorImage={comment.authorImage}
                    accountId={comment.accountId}
                    timestamp={comment.timestamp}
                    likeAmount={comment.likeAmount}
                />
            ))}
          </div>
          <PrimeAd/>
          <ScrollUpButton scrollUpRef={scrollUpRef}/>
        </div>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};