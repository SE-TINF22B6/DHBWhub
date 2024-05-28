export interface Imprint {
    informationAccordingTo: string,
    addressLine1: string,
    addressLine2: string,
    addressLine3: string,
    representedBy: string,
    contact: Contact,
    disclaimerOfLiability: DisclaimerOfLiability,
    liabilityForLinks: LiabilityForLinks,
    copyright: string,
    dataProtection: string
}

export interface Contact {
    email: string;
}

export interface DisclaimerOfLiability {
    content: string;
}

export interface LiabilityForLinks {
    content: string;
}

export const imprint: Imprint = {
    informationAccordingTo: "Information according to § 5 TMG",
    addressLine1: "DHBWhub",
    addressLine2: "Erzbergerstraße 121",
    addressLine3: "76133 Karlsruhe",
    representedBy: "Josha Schmitt",
    contact: {
        email: "info@dhbwhub.de"
    },
    disclaimerOfLiability: {
        content: `Liability for content The content of our website has been created with the utmost care. However, we cannot accept any liability for the accuracy, completeness and topicality of the content. As a service provider, we are responsible for our own content on these pages in accordance with § 7 (1) TMG (German Telemedia Act) and general laws. According to §§ 8 to 10 TMG, however, we as a service provider are not obliged to monitor transmitted or third-party information or to investigate circumstances that indicate illegal activity. Obligations to remove or block the use of information in accordance with general legislation remain unaffected by this. However, liability in this respect is only possible from the time of knowledge of a specific infringement. As soon as we become aware of such infringements, we will remove this content immediately.`
    },
    liabilityForLinks: {
        content: `Our website contains links to external third-party websites over whose content we have no influence. Therefore, we cannot accept any liability for this third-party content. The respective provider or operator of the pages is always responsible for the content of the linked pages. The linked pages were checked for possible legal violations at the time of linking. Illegal contents were not recognizable at the time of linking. However, permanent monitoring of the content of the linked pages is not reasonable without concrete evidence of an infringement. If we become aware of any legal infringements, we will remove such links immediately.`
    },
    copyright: `The content and works created by the site operators on these pages are subject to German copyright law. Duplication, processing, distribution and any kind of exploitation outside the limits of copyright law require the written consent of the respective author or creator. Downloads and copies of this site are only permitted for private, non-commercial use. Insofar as the content on this site was not created by the operator, the copyrights of third parties are respected. In particular, third-party content is identified as such. Should you nevertheless become aware of a copyright infringement, please inform us accordingly. If we become aware of any infringements, we will remove such content immediately.`,
    dataProtection: `The use of our website is generally possible without providing personal data. Insofar as personal data (e.g. name, address or e-mail addresses) is collected on our pages, this is always done on a voluntary basis as far as possible. This data will not be passed on to third parties without your express consent.
We would like to point out that data transmission over the Internet (e.g. when communicating by e-mail) may be subject to security vulnerabilities. Complete protection of data against access by third parties is not possible.
We hereby expressly prohibit the use of contact data published within the scope of the imprint obligation by third parties for sending unsolicited advertising and information material. The operators of this website expressly reserve the right to take legal action in the event of the unsolicited sending of advertising information, such as spam e-mails.`
};
