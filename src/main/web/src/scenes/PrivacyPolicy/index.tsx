import React from "react";
import "./index.css";
import {Header} from "../../organisms/header/Header";
import {Footer} from "../../organisms/footer/Footer";
import {MobileFooter} from "../../organisms/header/MobileFooter";
import {useMediaQuery} from "@mui/system";
import {privacyPolicyInterface} from "./PrivacyPolicyInterface";
import {Link} from "react-router-dom";
export const PrivacyPolicy = () => {
  const isSmartphoneSize = useMediaQuery('(max-width: 412px)');
  return (
      <div className="privacy-policy-component">
        <Header/>
          return (
              <div className="privacy-policy-content">
                  <div>
                      <h2>Privacy Policy</h2>
                      <p><strong>Last Updated: </strong>{privacyPolicyInterface.lastUpdated}</p>

                      <h3>Introduction</h3>
                      <p>{privacyPolicyInterface.introduction}</p>

                      <h3>Interpretation and Definitions</h3>
                      <p><strong>Interpretation:</strong> {privacyPolicyInterface.interpretationAndDefinitions.interpretation}</p>
                      <h4>Definitions</h4>
                      <ul>
                          {Object.entries(privacyPolicyInterface.definitions).map(([key, definition], index) => (
                              <li key={index}><strong>{key}:</strong> {definition}</li>
                          ))}
                      </ul>

                      <h3>Collecting and Using Your Personal Data</h3>
                      <h4>Types of Data Collected</h4>
                      <ul>
                          {privacyPolicyInterface.collectingAndUsingYourPersonalData.typesOfDataCollected.personalData.map((dataType, index) => (
                              <li key={index}>{dataType}</li>
                          ))}
                      </ul>
                      <h4>Usage Data</h4>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.usageData.content}</p>

                      <h4>Information from Third-Party Social Media Services</h4>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.informationFromThirdPartySocialMediaServices.content}</p>
                      <ul>
                          {privacyPolicyInterface.collectingAndUsingYourPersonalData.informationFromThirdPartySocialMediaServices.services.map((service, index) => (
                              <li key={index}>{service}</li>
                          ))}
                      </ul>

                      <h4>Retention of Your Personal Data</h4>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.retentionOfYourPersonalData.content}</p>

                      <h4>Transfer of Your Personal Data</h4>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.transferOfYourPersonalData.content}</p>

                      <h4>Delete Your Personal Data</h4>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.deleteYourPersonalData.content}</p>

                      <h4>Disclosure of Your Personal Data</h4>
                      <h5>Business Transactions</h5>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.disclosureOfYourPersonalData.businessTransactions}</p>

                      <h5>Law Enforcement</h5>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.disclosureOfYourPersonalData.lawEnforcement}</p>

                      <h5>Other Legal Requirements</h5>
                      <ul>
                          {privacyPolicyInterface.collectingAndUsingYourPersonalData.disclosureOfYourPersonalData.otherLegalRequirements.map((requirement, index) => (
                              <li key={index}>{requirement}</li>
                          ))}
                      </ul>

                      <h4>Security of Your Personal Data</h4>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.securityOfYourPersonalData}</p>

                      <h4>Children's Privacy</h4>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.childrensPrivacy}</p>

                      <h4>Links to Other Websites</h4>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.linksToOtherWebsites}</p>

                      <h4>Changes to This Privacy Policy</h4>
                      <p>{privacyPolicyInterface.collectingAndUsingYourPersonalData.changesToThisPrivacyPolicy}</p>
                      <h4>Contact Us</h4>
                      <p>{privacyPolicyInterface.contactUs.content}</p>
                      <p><Link style={{color: "var(--white)"}} to="/contact">contact form</Link></p>

                  </div>
              </div>
              <Footer />
              {isSmartphoneSize && <MobileFooter />}
          </div>
  );
}
