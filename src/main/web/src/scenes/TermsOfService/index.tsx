import React, {useRef} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import TermsOfServiceInterface from "./TermsOfServiceInterface";
import {DigitalOceanAd} from "../../atoms/ads/DigitalOceanAd";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import ScrollUpButton from "../../atoms/ScrollUpButton";

export const TermsOfService = () => {
    const adBlockDetected: boolean = useDetectAdBlock();
    usePreventScrolling(adBlockDetected);
    const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');
    const scrollUpRef = useRef<HTMLDivElement>(null);

    return (
        <div className="page">
          <div ref={scrollUpRef}></div>
            {adBlockDetected && <AdBlockOverlay/>}
            <Header/>
            <div className="tos-content">
                <TermsOfServiceInterface/>
                <DigitalOceanAd/>
            </div>
            <ScrollUpButton scrollUpRef={scrollUpRef}/>
            <Footer/>
            {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
};