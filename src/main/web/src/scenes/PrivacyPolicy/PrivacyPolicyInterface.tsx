export interface PrivacyPolicy {
    lastUpdated: string;
    introduction: string;
    interpretationAndDefinitions: InterpretationAndDefinitions;
    definitions: Definitions;
    collectingAndUsingPersonalData: CollectingAndUsingPersonalData;
    retentionOfPersonalData: string;
    transferOfPersonalData: string;
    deleteYourPersonalData: string;
    disclosureOfYourPersonalData: DisclosureOfYourPersonalData;
    otherLegalRequirements: string;
}

export interface InterpretationAndDefinitions {
    interpretation: string;
    definitions: string;
}

export interface Definitions {
    account: string;
    company: string;
    cookies: string;
    country: string;
    device: string;
    personalData: string;
    service: string;
    serviceProvider: string;
    thirdPartySocialMediaService: string;
    usageData: string;
    website: string;
    you: string;
}

export interface CollectingAndUsingPersonalData {
    typesOfDataCollected: TypesOfDataCollected;

}

export interface TypesOfDataCollected {
    personalData: PersonalData;
    usageData: string;

}

export interface PersonalData {
    description: string;
    examples: string[];
}


export interface DisclosureOfYourPersonalData {
    businessTransactions: string;
    lawEnforcement: string;
    otherLegalRequirements: string;
}

export const privacyPolicyInterface: PrivacyPolicy = {
    lastUpdated: "Last updated: May 13, 2024",
    introduction: `This Privacy Policy describes Our policies and procedures on the collection, use and disclosure of Your information when You use the Service and tells You about Your privacy rights and how the law protects You.
  We use Your Personal data to provide and improve the Service. By using the Service, You agree to the collection and use of information in accordance with this Privacy Policy. This Privacy Policy has been created with the help of the Privacy Policy Generator.`,
    interpretationAndDefinitions: {
        interpretation: `The words of which the initial letter is capitalized have meanings defined under the following conditions. The following definitions shall have the same meaning regardless of whether they appear in singular or in plural.`,
        definitions: ``
    },
    definitions: {
        account: `Account means a unique account created for You to access our Service or parts of our Service.`,
        company: `Company (referred to as either "the Company", "We", "Us" or "Our" in this Agreement) refers to DHBWhub.`,
        cookies: `Cookies are small files that are placed on Your computer, mobile device or any other device by a website, containing the details of Your browsing history on that website among its many uses.`,
        country: `Country refers to: Baden-Württemberg, Germany`,
        device: `Device means any device that can access the Service such as a computer, a cellphone or a digital tablet.`,
        personalData: `Personal Data is any information that relates to an identified or identifiable individual.`,
        service: `Service refers to the Website.`,
        serviceProvider: `Service Provider means any natural or legal person who processes the data on behalf of the Company. It refers to third-party companies or individuals employed by the Company to facilitate the Service, to provide the Service on behalf of the Company, to perform services related to the Service or to assist the Company in analyzing how the Service is used.`,
        thirdPartySocialMediaService: `Third-party Social Media Service refers to any website or any social network website through which a User can log in or create an account to use the Service.`,
        usageData: `Usage Data refers to data collected automatically, either generated by the use of the Service or from the Service infrastructure itself (for example, the duration of a page visit).`,
        website: `Website refers to DHBWhub, accessible from https://dhbwhub.de`,
        you: `You means the individual accessing or using the Service, or the company, or other legal entity on behalf of which such individual is accessing or using the Service, as applicable.`
    },
    collectingAndUsingPersonalData: {
        typesOfDataCollected: {
            personalData: {
                description: `While using Our Service, We may ask You to provide Us with certain personally identifiable information that can be used to contact or identify You. Personally identifiable information may include, but is not limited to:`,
                examples: [
                    "Email address",
                    "First name and last name",
                    "Usage Data"
                ]
            },
            usageData: `Usage Data is collected automatically when using the Service.
      Usage Data may include information such as Your Device's Internet Protocol address (e.g. IP address), browser type, browser version, the pages of our Service that You visit, the time and date of Your visit, the time spent on those pages, unique device identifiers and other diagnostic data.
When You access the Service by or through a mobile device, We may collect certain information automatically, including, but not limited to, the type of mobile device You use, Your mobile device unique ID, the IP address of Your mobile device, Your mobile operating system, the type of mobile Internet browser You use, unique device identifiers and other diagnostic data.
We may also collect information that Your browser sends whenever You visit our Service or when You access the Service by or through a mobile device.
Information from Third-Party Social Media Services
The Company allows You to create an account and log in to use the Service through the following Third-party Social Media Services:
Google
If You decide to register through or otherwise grant us access to a Third-Party Social Media Service, We may collect Personal data that is already associated with Your Third-Party Social Media Service's account, such as Your name, Your email address, Your activities or Your contact list associated with that account.
You may also have the option of sharing additional information with the Company through Your Third-Party Social Media Service's account. If You choose to provide such information and Personal Data, during registration or otherwise, You are giving the Company permission to use, share, and store it in a manner consistent with this Privacy Policy.
Tracking Technologies and Cookies`
        }
    },
    retentionOfPersonalData: `The Company will retain Your Personal Data only for as long as is necessary for the purposes set out in this Privacy Policy. We will retain and use Your Personal Data to the extent necessary to comply with our legal obligations (for example, if we are required to retain your data to comply with applicable laws), resolve disputes, and enforce our legal agreements and policies.
  The Company will also retain Usage Data for internal analysis purposes. Usage Data is generally retained for a shorter period of time, except when this data is used to strengthen the security or to improve the functionality of Our Service, or We are legally obligated to retain this data for longer time periods.`,

    transferOfPersonalData: `Your information, including Personal Data, is processed at the Company's operating offices and in any other places where the parties involved in the processing are located. It means that this information may be transferred to — and maintained on — computers located outside of Your state, province, country or other governmental jurisdiction where the data protection laws may differ than those from Your jurisdiction.
  Your consent to this Privacy Policy followed by Your submission of such information represents Your agreement to that transfer.
  The Company will take all steps reasonably necessary to ensure that Your data is treated securely and in accordance with this Privacy Policy and no transfer of Your Personal Data will take place to an organization or a country unless there are adequate controls in place including the security of Your data and other personal information.`,

    deleteYourPersonalData: `You have the right to delete or request that We assist in deleting the Personal Data that We have collected about You.
  Our Service may give You the ability to delete certain information about You from within the Service.
  You may update, amend, or delete Your information at any time by signing in to Your Account, if you have one, and visiting the account settings section that allows you to manage Your personal information. You may also contact Us to request access to, correct, or delete any personal information that You have provided to Us.
  Please note, however, that We may need to retain certain information when we have a legal obligation or lawful basis to do so.`,

    disclosureOfYourPersonalData: {
        businessTransactions: `If the Company is involved in a merger, acquisition or asset sale, Your Personal Data may be transferred. We will provide notice before Your Personal Data is transferred and becomes subject to a different Privacy Policy.`,
        lawEnforcement: `Under certain circumstances, the Company may be required to disclose Your Personal Data if required to do so by law or in response to valid requests by public authorities (e.g. a court or a government agency).`,
        otherLegalRequirements: `The Company may disclose Your Personal Data in the good faith belief that such action is necessary to:
    • Comply with a legal obligation
    • Protect and defend the rights or property of the Company
    • Prevent or investigate possible wrongdoing in connection with the Service
    • Protect the personal safety of Users of the Service or the public
    • Protect against legal liability`
    },
    otherLegalRequirements: ""
};


