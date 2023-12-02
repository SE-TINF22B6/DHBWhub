import React from 'react';
import {EmailIcon, EmailShareButton, LinkedinIcon, LinkedinShareButton, RedditIcon, RedditShareButton, TelegramIcon, TelegramShareButton,
  XIcon, TwitterShareButton, WhatsappIcon, WhatsappShareButton} from "react-share";
import './Share.css';

interface ShareProps {
  postId: number;
  currentPageURL: string;
}

export const Share = (props: ShareProps): JSX.Element => {
  const {currentPageURL} = props;
  const copyToClipboard = (): void => {
    navigator.clipboard.writeText(currentPageURL + "post/?id=" + props.postId)
    .then((): void => {
      alert('Link has been copied to the clipboard!');
    })
    .catch(err => {
      console.error('Error copying the link: ', err);
      alert('Error copying the link. Please copy manually.');
    });
  };

  const sharePost = async (): Promise<void> => {
    try {
      await navigator.share({
        title: 'DHBWhub',
        text: 'Check out this post at DHBWhub:',
        url: currentPageURL + 'post/?id=' + props.postId,
      });
    } catch (error) {
      console.error('Error sharing the post: ', error);
      alert('Error sharing the post. Please try again.');
    }
  };

  const openWindowsShareOptions = (): void => {
    sharePost().then(result => console.log('Shared post successfully.'));
  };

  return (
      <div className="share">
        <button onClick={copyToClipboard} className="copy-link-button"></button>
        <EmailShareButton subject="Check out this post at DHBWhub" url={currentPageURL + "post/?id=" + props.postId}>
          <EmailIcon size={32} round={true}/>
        </EmailShareButton>
        <WhatsappShareButton title="Check out this post at DHBWhub:" url={currentPageURL + "post/?id=" + props.postId}>
          <WhatsappIcon size={32} round={true}/>
        </WhatsappShareButton>
        <LinkedinShareButton title="Check out this post at DHBWhub:" url={currentPageURL + "post/?id=" + props.postId}>
          <LinkedinIcon size={32} round={true}/>
        </LinkedinShareButton>
        <TelegramShareButton title="Check out this post at DHBWhub!" url={currentPageURL + "post/?id=" + props.postId}>
          <TelegramIcon size={32} round={true}/>
        </TelegramShareButton>
        <TwitterShareButton title="Check out this post at DHBWhub:" url={currentPageURL + "post/?id=" + props.postId}>
          <XIcon size={32} round={true}/>
        </TwitterShareButton>
        <RedditShareButton title="Check out this post at DHBWhub:" url={currentPageURL + "post/?id=" + props.postId}>
          <RedditIcon size={32} round={true}/>
        </RedditShareButton>
        <button onClick={openWindowsShareOptions} className="windows-share-button">
          <div className="share-dots">...</div>
        </button>
      </div>
  );
};