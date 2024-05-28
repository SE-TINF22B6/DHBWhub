import {Link} from "react-router-dom";
import React, {ReactNode} from "react";

export interface FaqData {
    question: string;
    answer: {
        content: ReactNode;
    };
}

export const faqData: FaqData[] = [
    {
        question: "How can I Post?",
        answer: {
            content: `You Click on the Red Button "Create Post" on the homepage and enter the Title, Tags, optionally a Picture, the content of your Post and Click the "Submit" Button. `
        }
    },
    {
        question: "How do I add a Friend?",
        answer: {
            content: `To add a friend, go to the user's profile and click the "Follow" button.`
        }
    },
    {
        question: "How do I unfollow a Friend?",
        answer: {
            content: `To remove a friend, go to your friend list, find the friend you want to unfollow, and click the "Unfollow" button.`
        }
    },
    {
        question: "Where Can I Contact the Owners or Admins of DHBWhub?",
        answer: {
            content: <>You can contact us by filling out our{" "}
                <Link style={{color: "var(--white)"}} to="/contact">Contact Form</Link>
            </>
        }
    },
    {
        question: "How is my Data used?",
        answer: {
            content: <>
                You can read about how we use your Data in our{" "}
                <Link style={{color: "var(--white)"}} to="/privacy-policy">Privacy Policy</Link>.
            </>
        }
    }
];
