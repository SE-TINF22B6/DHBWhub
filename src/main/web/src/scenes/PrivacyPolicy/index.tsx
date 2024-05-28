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
                      <h5>Business Transactions</h5>
                      <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.DisclosureOfYourPersonalData.BusinessTransactions}</p>

                      <h5>Law Enforcement</h5>
                      <p>{privacyPolicyInterface.CollectingAndUsingYourPersonalData.DisclosureOfYourPersonalData.LawEnforcement}</p>

                      <h5>Other Legal Requirements</h5>
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
                      <p>{privacyPolicyInterface.ContactUs.Content}</p>
                      <Link style={{color: "var(--white)"}} to="/contact">contact form</Link>

                  </div>
              </div>
              <Footer />
              {isSmartphoneSize && <MobileFooter />}
          </div>
  );
}
