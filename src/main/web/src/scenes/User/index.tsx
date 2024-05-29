import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { Header } from "../../organisms/header/Header";
import "./index.css";
import Lottie from "lottie-react";
import animationData from "../../assets/loading.json";
import errorAnimationData from "../../assets/error.json";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import { useDetectAdBlock } from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import config from "../../config/config";
import {getJWT} from "../../services/AuthService";

export const User = () => {
  const location = useLocation();
  const searchParams: URLSearchParams = new URLSearchParams(location.search);
  const username: string | null = searchParams.get('name');

  const [notFound, setNotFound] = useState(false);
  const [loading, setLoading] = useState(true);
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');

  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);

  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  useEffect((): void => {
    const fetchUser = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + `user/${username}`,{
          headers: headersWithJwt
        });
        if (response.ok) {
          // const userData = await response.json();
        } else {
          setNotFound(true);
        }
        setLoading(false);
      } catch (error) {
        console.error("Fehler beim Abrufen der Nutzerdaten:", error);
        setNotFound(true);
        setLoading(false);
      }
    };

    fetchUser();
  }, [username]);

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

  if (notFound || !username) {
    return (
        <div className="page">
          {adBlockDetected && <AdBlockOverlay/>}
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
      <div className="page">
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};