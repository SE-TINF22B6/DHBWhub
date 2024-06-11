import React, {useMemo, useEffect, useState} from "react";
import "./index.css";
import Lottie from "lottie-react";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import animationData from "../../assets/loading.json";
import {UserFriendModel} from "./models/UserFriendModel";
import {getJWT, getUserId} from "../../services/AuthService";
import {UserFriend} from "./UserFriend";
import config from "../../config/config";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";

export const Friends = () => {
  const jwt: string | null = getJWT();
  const headersWithJwt = useMemo(() => ({
      ...config.headers,
      'Authorization': jwt ? `Bearer ${jwt}` : ''
  }), [jwt]);

  const [friendsResults, setFriendsResults] = useState<any[]>([]);
  const [notFound, setNotFound] = useState(false);
  const [loading, setLoading] = useState(true);
  const adBlockDetected = useDetectAdBlock();
  const userSelfId = getUserId();
  usePreventScrolling(adBlockDetected);

  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

  useEffect(() => {
    setFriendsResults([]);
    setNotFound(false);
    setLoading(true);

    const fetchResults = async (url: string): Promise<void> => {
      try {
        const response: Response = await fetch(url, {
          headers: headersWithJwt
        });
        if (response.ok) {
          const data = await response.json();
          if (data.length !== 0) {
            setFriendsResults(data);
            setNotFound(false);
          } else {
            setNotFound(true);
          }
        } else {
          setNotFound(true);
        }

        setLoading(false);
      } catch (error) {
        console.error("Error when retrieving following data:", error);
        setNotFound(true);
        setLoading(false);
      }
    };

    fetchResults(config.apiUrl + `friendship/friendlist/${userSelfId}`)
  }, [userSelfId, headersWithJwt]);

  if (loading) {
    return (
       <div className="page">
          {adBlockDetected && <AdBlockOverlay />}
          <Header />
          <div className="animation">
            <div className="loading-animation">
              <Lottie animationData={animationData}/>
            </div>
          </div>
          <Footer />
         {isSmartphoneSize && <MobileFooter />}
       </div>
    );
  }

  if (notFound) {
    return (
      <div className="page">
        {adBlockDetected && <AdBlockOverlay />}
        <Header />
        <h1 className="empty-page-info">You're not following anyone yet</h1>
        <Footer />
        {isSmartphoneSize && <MobileFooter />}
      </div>
    );
  }

  return (
    <div className="page">
      {adBlockDetected && <AdBlockOverlay />}
      <Header />
      <div className="friends-container">
        <div className="friend-list-container">
          <h1 className="friends-title">Following</h1>
          <div className="friend-list">
            {friendsResults.map((user: UserFriendModel) => (
              <UserFriend
                key={user.friendlistId}
                friendlistId={user.friendlistId}
                userId={user.userId}
                username={user.username}
                image={user.image}
              />))
            }
          </div>
        </div>
      </div>
      <Footer />
      {isSmartphoneSize && <MobileFooter />}
    </div>
  );
};
