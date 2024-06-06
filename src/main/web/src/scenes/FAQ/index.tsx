import React, {useRef} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {useDetectAdBlock} from "adblock-detect-react";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import Typography from "@mui/material/Typography";
import {FaqData, faqData} from "./FaqData";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import AccordionDetails from "@mui/material/AccordionDetails";
import {DigitalOceanAd} from "../../atoms/ads/DigitalOceanAd";
import ScrollUpButton from "../../atoms/ScrollUpButton";

export const FAQ = () => {
  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');
  const scrollUpRef = useRef<HTMLDivElement>(null);

  return (
      <div className="page">
        <div ref={scrollUpRef}></div>
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
        <div className="faq-content">
          <Typography variant="h5">
            Frequently Asked Questions
          </Typography>
          <div className="faq-accordion">
            {faqData.map((item: FaqData, index: number) => (
                <Accordion key={index} className="accordion-item">
                  <AccordionSummary expandIcon={<ExpandMoreIcon style={{color: 'fff'}}/>}
                                    className="accordion-summary">
                    <Typography>{item.question}</Typography>
                  </AccordionSummary>
                  <AccordionDetails>
                    <Typography>{item.answer.content}</Typography>
                  </AccordionDetails>
                </Accordion>
            ))}
          </div>
          <DigitalOceanAd/>
        </div>
        <ScrollUpButton scrollUpRef={scrollUpRef}/>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
};