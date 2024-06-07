import React, {useState, useEffect} from "react";
import {Link, useLocation} from "react-router-dom";
import { Header } from "../../organisms/header/Header";
import "./index.css";
import Lottie from "lottie-react";
import animationData from "../../assets/loading.json";
import {Footer} from "../../organisms/footer/Footer";
import {Tag} from "../../atoms/Tag";
import {SortOptions} from "../Home/components/sort-options/SortOptions";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {C24Ad} from "../../atoms/ads/C24Ad";
import {RentalCarAd} from "../../atoms/ads/RentalCarAd";
import {useMediaQuery} from "@mui/system";
import {Post} from "../Home/components/post/Post";
import {PostModel} from "../Home/components/post/models/PostModel";
import {fetchFriendPostsByTag, fetchPostsByTag} from "../../services/PostsByTagService";

export const TagOverview = () => {
  const location = useLocation();
  const searchParams: URLSearchParams = new URLSearchParams(location.search);
  const nameQueryParam: string | null = searchParams.get('name');
  const tagName: string | null = nameQueryParam ? nameQueryParam.replace(nameQueryParam.charAt(0), nameQueryParam.charAt(0).toUpperCase()) : null;
  const [postsByTag, setPostsByTag] = useState<PostModel[]>();
  const [followingPostsByTag, setFollowingPostsByTag] = useState<PostModel[]>();

  const [notFound, setNotFound] = useState(true);
  const [loading, setLoading] = useState(true);
  const [sortOption, setSortOption] = useState<string>('popular');

  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

  const handleSortChange = (option: string): void => {
    setSortOption(option);
  };

  useEffect((): void => {
    const fetchData = async (): Promise<void> => {
      const posts: PostModel[] | null = await fetchPostsByTag(tagName);
      if (posts !== null && posts.length > 0) {
        setPostsByTag(posts);
        setNotFound(false);
      } else {
        setNotFound(true);
      }
      setLoading(false);
    };
    fetchData();
  }, [tagName]);

  useEffect((): void => {
    const fetchData = async (): Promise<void> => {
      const posts: PostModel[] | null = await fetchFriendPostsByTag(tagName);
      if (posts !== null && posts.length > 0) {
        setFollowingPostsByTag(posts);
        setNotFound(false);
      } else {
        setNotFound(true);
      }
      setLoading(false);
    };
    fetchData();
  }, [tagName]);

  const sortedPostsByTag = (): PostModel[] | undefined => {
    if (postsByTag) {
      switch (sortOption) {
        case "newest":
          return postsByTag.sort((a: PostModel, b: PostModel) => Date.parse(new Date(b.timestamp).toISOString()) -
              Date.parse(new Date(a.timestamp).toISOString()));
        case "following":
          return followingPostsByTag;
        case "popular":
          return postsByTag.sort((a: PostModel, b: PostModel) => b.likeAmount - a.likeAmount);
        default:
          return postsByTag;
      }
    }
  };

  if (loading) {
    return (
        <div className="page">
          {adBlockDetected && <AdBlockOverlay/>}
          <Header/>
          <div className="loading-animation">
            <Lottie animationData={animationData}/>
          </div>
          <Footer/>
          {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
  }

  if (notFound || !tagName) {
    return (
        <div className="page">
          {adBlockDetected && <AdBlockOverlay/>}
          <Header/>
          <Link to="/" className="navigate-back-link">
            <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-vector.svg'}
                 className="navigate-back-vector"/>
            <img alt="Navigate back" src={process.env.PUBLIC_URL + '/assets/post/navigate-back-rectangle.svg'}
                 className="navigate-back-rectangle"/>
            <div className="navigate-back-text">Tag</div>
          </Link>
          {tagName && (
              <>
                <Tag name={tagName} isEventTag={false} index={1}/>
                <div className="loading" style={{marginTop: "20px"}}>
                  No posts for tag found
                </div>
              </>
          )}
          <Footer/>
          {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
  }

  return (
      <div className="page">
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
        <div className="tag-overview-content">
          <div className="tag-overview-left">
            <SortOptions onSortChange={handleSortChange}/>
            <RentalCarAd/>
          </div>
          <div className="tag-overview-middle">
            {tagName && (
                <Tag name={(tagName).charAt(0).toUpperCase() + (tagName || "").slice(1).toLowerCase()} index={1} isEventTag={false}/>
            )}
            {sortedPostsByTag()?.map(post => (
                <Post
                    key={post.id}
                    id={post.id}
                    title={post.title}
                    description={post.description}
                    tags={post.tags}
                    likeAmount={post.likeAmount}
                    commentAmount={post.commentAmount}
                    timestamp={post.timestamp}
                    postImage={post.postImage}
                    accountId={post.accountId}
                    username={post.username}
                />
            ))}
          </div>
          <div className="tag-overview-right">
            <C24Ad/>
          </div>
        </div>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};
