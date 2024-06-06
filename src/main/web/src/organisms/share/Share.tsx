import React, {useEffect, useState} from 'react';
import {EmailIcon, EmailShareButton, LinkedinIcon, LinkedinShareButton, RedditIcon, RedditShareButton, TelegramIcon, TelegramShareButton,
  XIcon, TwitterShareButton, WhatsappIcon, WhatsappShareButton} from "react-share";
import './Share.css';
import {useMediaQuery} from "@mui/system";

interface ShareProps {
  postId?: number;
  eventId?: number;
  commentId?: number;
  currentPageURL: string;
}

export const Share = (props: ShareProps) => {
  const {
    postId,
    eventId,
    commentId,
    currentPageURL
  } = props;
  let url: string = currentPageURL;

  let type: string = "";
  if (postId) {
    type = "post";
  } else if (eventId) {
    type = "event";
  } else if (commentId) {
    type = "comment";
  }

  const title: string = "Check out this " + type + " at DHBWhub:";

  if (postId && !currentPageURL.includes("post/?id=")) {
    url += "post/?id=" + postId;
  }
  if (eventId && !currentPageURL.includes("event/?id=")) {
    url += "event/?id=" + eventId;
  }
  if (commentId) {
    url += "&comment=" + commentId;
  }

  const [iconSize, setIconSize] = useState<number>(32);
  const matches: boolean = useMediaQuery('(max-width: 412px)')

  useEffect((): void => {
    const handleResize = (): void => {
      const newSize = matches ? 25 : 32;
      setIconSize(newSize);
    };
    handleResize();
  }, []);

  const copyToClipboard = (): void => {
    navigator.clipboard.writeText(url)
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
        text: title,
        url: url,
      });
    } catch (error) {
      console.error('Error sharing the ' + type + ' : ', error);
      alert('Error sharing the ' + type + '. Please try again.');
    }
  };

  const openWindowsShareOptions = (): void => {
    sharePost().then(() => console.log('Shared ' + type + ' successfully.'));
  };

  return (
      <div className="share">
        <img src={process.env.PUBLIC_URL + '/assets/home/post/copy.svg'} onClick={copyToClipboard} className="copy-link-button"
             alt="Copy"></img>
        <EmailShareButton subject={title} url={url}>
          <EmailIcon size={iconSize} round={true}/>
        </EmailShareButton>
        <WhatsappShareButton title={title} url={url}>
          <WhatsappIcon size={iconSize} round={true}/>
        </WhatsappShareButton>
        <LinkedinShareButton title={title} url={url}>
          <LinkedinIcon size={iconSize} round={true}/>
        </LinkedinShareButton>
        <TelegramShareButton title={title} url={url}>
          <TelegramIcon size={iconSize} round={true}/>
        </TelegramShareButton>
        <TwitterShareButton title={title} url={url}>
          <XIcon size={iconSize} round={true}/>
        </TwitterShareButton>
        <RedditShareButton title={title} url={url}>
          <RedditIcon size={iconSize} round={true}/>
        </RedditShareButton>
        <img src={process.env.PUBLIC_URL + '/assets/home/post/more.svg'} onClick={openWindowsShareOptions} className="windows-share-button"
             alt="More"></img>
      </div>
  );
};