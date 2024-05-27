export interface FaqData {
    question: string;
    answer: string;
}

export const faqData: FaqData[] = [
    {
        question: "How can I Post?",
        answer: "To post a message, go to the 'New Post' section, write your message, and click the 'Submit' button."
    },
    {
        question: "How do I add a Friend?",
        answer: "To add a friend, go to the user's profile and click the 'Add Friend' button."
    },
    {
        question: "How do I remove a Friend?",
        answer: "To remove a friend, go to your friend list, find the friend you want to remove, and click the 'Remove' button."
    }

];
