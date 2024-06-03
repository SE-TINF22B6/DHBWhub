import React from "react";
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

export const TermsOfService = () => {
    const adBlockDetected: boolean = useDetectAdBlock();
    usePreventScrolling(adBlockDetected);
    const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');
    return (
        <div className="page">
            {adBlockDetected && <AdBlockOverlay/>}
            <Header/>
            <div className="tos-content">
                <TermsOfServiceInterface/>
                <DigitalOceanAd/>
            </div>
            <Footer/>
            {isSmartphoneSize && <MobileFooter/>}
        </div>
    );
};