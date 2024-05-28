export interface FaqData {
    question: string;
    answer: string;
}

export const faqData: FaqData[] = [
    {
        question: "How can I Post?",
        answer: "You Click on the Red Button \"Create Post\" on the homepage and enter the Title, Tags, optionally a Picture, the content of your Post and Click the \"Submit\" Button."
    },
    {
        question: "How do I add a Friend?",
        answer: "To add a friend, go to the user's profile and click the \"Follow\" button."
    },
    {
        question: "How do I unfollow a Friend?",
        answer: "To remove a friend, go to your friend list, find the friend you want to unfollow, and click the \"Unfollow\" button."
    },
    {
        question: "Where Can I Contact the Owners or Admins of DHBWhub? ",
        answer: "You can find the Contact Form in the Bottom right of the homepage right underneath where you got to this page :)"
    }

];
