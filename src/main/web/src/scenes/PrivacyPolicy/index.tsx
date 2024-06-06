import React, {useRef} from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import AdBlockOverlay from "../../organisms/ad-block-overlay/AdBlockOverlay";
import {useDetectAdBlock} from "adblock-detect-react";
import {usePreventScrolling} from "../../organisms/ad-block-overlay/preventScrolling";
import {DigitalOceanAd} from "../../atoms/ads/DigitalOceanAd";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import {privacyPolicyInterface} from "./PrivacyPolicyInterface";
import {Link} from "react-router-dom";
import ScrollUpButton from "../../atoms/ScrollUpButton";

export const PrivacyPolicy = () => {
  const adBlockDetected: boolean = useDetectAdBlock();
  usePreventScrolling(adBlockDetected);
  const isSmartphoneSize: boolean = useMediaQuery('(max-width: 412px)');
  const scrollUpRef = useRef<HTMLDivElement>(null);

  return (
      <div className="page">
        <div ref={scrollUpRef}></div>
        {adBlockDetected && <AdBlockOverlay/>}
        <Header/>
          <div className="privacy-policy-content">
              <h2>Privacy Policy</h2>
              <p><strong>Last Updated: </strong>{privacyPolicyInterface.LastUpdated}</p>

              <h3>Introduction</h3>
              <p>{privacyPolicyInterface.Introduction}</p>
              <h3>Interpretation and Definitions</h3>
              <p><strong>Interpretation:</strong> {privacyPolicyInterface.InterpretationAndDefinitions.Interpretation}</p>
              <h4>Definitions</h4>
              <ul>
                  {Object.entries(privacyPolicyInterface.Definitions).map(([key, definition], index) => (
                      <li key={index}><strong>{key}:</strong> {definition}</li>
                  ))}
              </ul>
              <h3>Collecting and Using Your Personal Data</h3>
              <h4>Types of Data Collected</h4>
              <ul>
                  {privacyPolicyInterface.CollectingAndUsingYourPersonalData.TypesOfDataCollected.PersonalData.map((dataType, index) => (
                      <li key={index}>{dataType}</li>
                  ))}
              </ul>
              <h4>Usage Data</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.UsageData.Content}</p>

              <h4>Information from Third-Party Social Media Services</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.InformationFromThirdPartyServices.Content}</p>
              <ul>
                  {privacyPolicyInterface.CollectingAndUsingYourPersonalData.InformationFromThirdPartyServices.Services.map((service, index) => (
                      <li key={index}>{service}</li>
                  ))}
              </ul>

              <h4>Retention of Your Personal Data</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.RetentionOfYourPersonalData.Content}</p>

              <h4>Transfer of Your Personal Data</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.TransferOfYourPersonalData.Content}</p>

              <h4>Delete Your Personal Data</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.DeleteYourPersonalData.Content}</p>

              <h4>Disclosure of Your Personal Data</h4>
              <h4>Business Transactions</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.DisclosureOfYourPersonalData.BusinessTransactions}</p>

              <h4>Law Enforcement</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.DisclosureOfYourPersonalData.LawEnforcement}</p>

              <h4>Other Legal Requirements</h4>
              <ul>
                  {privacyPolicyInterface.CollectingAndUsingYourPersonalData.DisclosureOfYourPersonalData.OtherLegalRequirements.map((requirement, index) => (
                      <li key={index}>{requirement}</li>
                  ))}
              </ul>

              <h4>Security of Your Personal Data</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.SecurityOfYourPersonalData}</p>

              <h4>Children's Privacy</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.ChildrensPrivacy}</p>

              <h4>Links to Other Websites</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.LinksToOtherWebsites}</p>

              <h4>Changes to This Privacy Policy</h4>
              <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.ChangesToThisPrivacyPolicy}</p>
              <h4>Contact Us</h4>
              <p>{privacyPolicyInterface.ContactUs.Content} <Link style={{color: "var(--white)"}} to="/contact">Contact form</Link></p>
              <br/>
              <DigitalOceanAd/>
          </div>
          <ScrollUpButton scrollUpRef={scrollUpRef}/>
          <Footer/>
          {isSmartphoneSize && <MobileFooter />}
      </div>
  );
};