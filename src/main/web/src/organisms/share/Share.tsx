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
  const {currentPageURL} = props;
  let url = currentPageURL;
  if (!url.includes("post/?id=")) {
    url += "post/?id=" + props.postId;
  }
  if (props.commentId) {
    url += "&comment=" + props.commentId;
  }

  const [iconSize, setIconSize] = useState<number>(32);
  const matches = useMediaQuery('(max-width: 412px)')

  useEffect(() => {
    const handleResize = () => {
      const newSize = matches ? 25 : 32;
      setIconSize(newSize);
    };

    window.addEventListener('resize', handleResize);
    handleResize();

    return () => window.removeEventListener('resize', handleResize);
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
        text: 'Check out this post at DHBWhub:',
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
        <EmailShareButton subject="Check out this post at DHBWhub" url={url}>
          <EmailIcon size={iconSize} round={true}/>
        </EmailShareButton>
        <WhatsappShareButton title="Check out this post at DHBWhub:" url={url}>
          <WhatsappIcon size={iconSize} round={true}/>
        </WhatsappShareButton>
        <LinkedinShareButton title="Check out this post at DHBWhub:" url={url}>
          <LinkedinIcon size={iconSize} round={true}/>
        </LinkedinShareButton>
        <TelegramShareButton title="Check out this post at DHBWhub!" url={url}>
          <TelegramIcon size={iconSize} round={true}/>
        </TelegramShareButton>
        <TwitterShareButton title="Check out this post at DHBWhub:" url={url}>
          <XIcon size={iconSize} round={true}/>
        </TwitterShareButton>
        <RedditShareButton title="Check out this post at DHBWhub:" url={url}>
          <RedditIcon size={iconSize} round={true}/>
        </RedditShareButton>
        <img src={process.env.PUBLIC_URL + '/assets/home/post/more.svg'} onClick={openWindowsShareOptions} className="windows-share-button"
             alt="More"></img>
      </div>
  );
};