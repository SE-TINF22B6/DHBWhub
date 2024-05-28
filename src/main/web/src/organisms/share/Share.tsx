import React, {useEffect, useState} from 'react';
import {EmailIcon, EmailShareButton, LinkedinIcon, LinkedinShareButton, RedditIcon, RedditShareButton, TelegramIcon, TelegramShareButton,
  XIcon, TwitterShareButton, WhatsappIcon, WhatsappShareButton} from "react-share";
import './Share.css';
import {useMediaQuery} from "@mui/system";

interface ShareProps {
  postId: number;
  commentId?: number;
  currentPageURL: string;
}

export const Share = (props: ShareProps) => {
  const {postId, commentId, currentPageURL} = props;
  let url = currentPageURL;
  const title= "Check out this post at DHBWhub";

  if (!currentPageURL.includes("post/?id=")) {
    url += "post/?id=" + postId;
  }
  if (commentId) {
    url += "&comment=" + commentId;
  }

  const [iconSize, setIconSize] = useState<number>(32);
  const matches = useMediaQuery('(max-width: 412px)')

  useEffect((): void => {
    const handleResize = () => {
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
      console.error('Error sharing the post: ', error);
      alert('Error sharing the post. Please try again.');
    }
  };

  const openWindowsShareOptions = (): void => {
    sharePost().then(result => console.log('Shared post successfully.'));
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