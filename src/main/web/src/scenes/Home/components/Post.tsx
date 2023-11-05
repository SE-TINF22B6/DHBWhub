import React from 'react';
import './Post.css';

interface PostProps {
  title: string;
  shortDescription: string;
  tags: string[];
  author: string;
  date: string;
  likes: number;
  comments: number;
  imageSrc: string;
}

export const Post: React.FC<PostProps> = (props: PostProps) => {
  const {
    title,
    shortDescription,
    tags,
    author,
    date,
    likes,
    comments,
    imageSrc,
  } = props;

  return (
      <section>
        <div className="post-background" />
        <div className="post">
          <div className="picture"></div>
          <div className="menu-button">
            <div className="ellipse-2" />
            <div className="ellipse-3"/>
            <div className="ellipse-4"/>
          </div>
          <div className="interaction-stats">
            <div className="stats">
              <div className="likes">{likes} likes</div>
              <div className="comments">{comments} comments</div>
            </div>
          </div>
          <div className="interaction-symbols">
            <div className="heart-symbol"/>
            <div className="comment-symbol"/>
          </div>
          <div className="overlap-9">
            <div className="seperator"/>
            <div className="post-infos">
              <p className="title">{title}</p>
              <div className="tags-2">
                <div className="tag-2">
                  <div className="overlap-group-4">
                    <div className="tag1">{tags[0]}</div>
                  </div>
                </div>
                <div className="tag-2">
                  <div className="overlap-10">
                    <div className="label-3">{tags[1]}</div>
                  </div>
                </div>
                <div className="tag-3">
                  <div className="overlap-11">
                    <div className="tag3">{tags[2]}</div>
                  </div>
                </div>
              </div>
              <p className="short-description">{shortDescription}</p>
              <p className="author-date">{author} · {date}</p>
            </div>
          </div>
        </div>
      </section>
  );
};