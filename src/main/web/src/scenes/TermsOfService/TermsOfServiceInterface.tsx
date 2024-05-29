
import React, {ReactNode} from 'react';
import {Link} from "react-router-dom";

interface TermsOfServiceContent {
    title: string;
    content: ReactNode[];
}

const TermsOfServiceInterface: React.FC = () => {
    const termsContent: TermsOfServiceContent[] = [
        {
            title: "Acceptance of Terms",
            content: [
                "By accessing or using the Service, you agree to be bound by these Terms and our Privacy Policy. If you do not agree to these Terms or the Privacy Policy, you may not use the Service."
            ]
        },
        {
            title: "Eligibility",
            content: [
                "The Service is intended for individuals aged 18 and above. By using the Service, you represent and warrant that you are at least 18 years old. If you are under 18, you may only use the Service with the involvement of a parent or guardian."
            ]
        },
        {
            title: "User Accounts",
            content: [
                "You may be required to create an account to access certain features of the Service. When creating an account, you agree to provide accurate and complete information. You are responsible for maintaining the confidentiality of your account credentials and for all activities that occur under your account."
            ]
        },
        {
            title: "Acceptable Use",
            content: [
                "You agree to use the Service only for lawful purposes and in accordance with these Terms. You may not use the Service to:",
                "- Violate any applicable laws or regulations",
                "- Infringe upon the rights of others",
                "- Transmit any viruses or malicious code",
                "- Engage in any activity that disrupts or interferes with the Service"
            ]
        },
        {
            title: "Intellectual Property",
            content: [
                "All content on the Service, including text, graphics, logos, and images, is the property of DHBWhub or its licensors and is protected by copyright and other intellectual property laws. You may not use, reproduce, or distribute any content from the Service without prior written permission."
            ]
        },
        {
            title: "Third-Party Links",
            content: [
                "The Service may contain links to third-party websites or services that are not owned or controlled by DHBWhub. DHBWhub has no control over, and assumes no responsibility for, the content, privacy policies, or practices of any third-party websites or services. You access third-party websites or services at your own risk."
            ]
        },
        {
            title: "Termination",
            content: [
                "DHBWhub may terminate or suspend your access to the Service at any time, without prior notice or liability, for any reason whatsoever, including if you breach these Terms."
            ]
        },
        {
            title: "Disclaimer of Warranties",
            content: [
                "The Service is provided on an 'as is' and 'as available' basis, without any warranties of any kind, either express or implied. DHBWhub disclaims all warranties, including, but not limited to, the implied warranties of merchantability, fitness for a particular purpose, and non-infringement."
            ]
        },
        {
            title: "Limitation of Liability",
            content: [
                "In no event shall DHBWhub, its officers, directors, employees, or agents, be liable to you for any direct, indirect, incidental, special, consequential, or punitive damages arising out of or in connection with your use of the Service."
            ]
        },
        {
            title: "Governing Law",
            content: [
                "These Terms shall be governed by and construed in accordance with the laws of Germany, without regard to its conflict of law provisions."
            ]
        },
        {
            title: "Changes to Terms",
            content: [
                "DHBWhub reserves the right to update or modify these Terms at any time, without prior notice. Your continued use of the Service after any such changes constitutes your acceptance of the new Terms."
            ]
        },
        {
            title: "Contact Us",
            content: [
                <>If you have any questions about these Terms, please contact us at our {" "}
                    <Link style={{color: "var(--white)"}} to="/contact">Contact Form</Link></>
            ]
        }
    ];

    return (
        <div>
            <h2>Terms of Service</h2>
            {termsContent.map((term, index) => (
                <div key={index}>
                    <h3>{term.title}</h3>
                    {term.content.map((paragraph, index) => (
                        <p key={index}>{paragraph}</p>
                    ))}
                </div>
            ))}
        </div>
    );
};

export default TermsOfServiceInterface;
