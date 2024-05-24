import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import {privacyPolicyInterface} from "./PrivacyPolicyInterface";
export const PrivacyPolicy = () => {
  const isSmartphoneSize = useMediaQuery('(max-width: 412px)');
  return (
      <div className="privacy-policy-component">
        <Header/>
        <div className="privacy-policy-content">
           <h2>Privacy Policy</h2>
            <p>{privacyPolicyInterface.lastUpdated}</p>
            <p>{privacyPolicyInterface.introduction}</p>
            <p>{privacyPolicyInterface.interpretationAndDefinitions.interpretation}</p>
            <p>{privacyPolicyInterface.interpretationAndDefinitions.definitions}</p>
            <p>{privacyPolicyInterface.definitions.account}</p>
            <p>{privacyPolicyInterface.definitions.company}</p>
            <p>{privacyPolicyInterface.definitions.cookies}</p>
            <p>{privacyPolicyInterface.definitions.country}</p>
            <p>{privacyPolicyInterface.definitions.device}</p>
            <p>{privacyPolicyInterface.definitions.personalData}</p>
            <p>{privacyPolicyInterface.definitions.service}</p>
            <p>{privacyPolicyInterface.definitions.serviceProvider}</p>
            <p>{privacyPolicyInterface.definitions.thirdPartySocialMediaService}</p>
            <p>{privacyPolicyInterface.definitions.usageData}</p>
            <p>{privacyPolicyInterface.definitions.website}</p>
            <p>{privacyPolicyInterface.definitions.you}</p>
            <p>{privacyPolicyInterface.collectingAndUsingPersonalData.typesOfDataCollected.personalData.description}</p>
            <p>{privacyPolicyInterface.collectingAndUsingPersonalData.typesOfDataCollected.personalData.examples}</p>
            <p>{privacyPolicyInterface.collectingAndUsingPersonalData.typesOfDataCollected.usageData}</p>
            <p>{privacyPolicyInterface.retentionOfPersonalData}</p>
            <p>{privacyPolicyInterface.transferOfPersonalData}</p>
            <p>{privacyPolicyInterface.deleteYourPersonalData}</p>
            <p>{privacyPolicyInterface.disclosureOfYourPersonalData.businessTransactions}</p>
            <p>{privacyPolicyInterface.disclosureOfYourPersonalData.lawEnforcement}</p>
            <p>{privacyPolicyInterface.disclosureOfYourPersonalData.otherLegalRequirements}</p>

        </div>
        <Footer/>
        {isSmartphoneSize && <MobileFooter/>}
      </div>
  );
}
