import React, {useState} from 'react';
import './Tag.css';
import {Link} from "react-router-dom";

interface TagProps {
  name: string;
  index: number;
  isEventTag: boolean;
}

export const Tag : React.FC<TagProps> = (props: TagProps) => {
  let {name, index, isEventTag} = props;

  return (
      <Link key={index} to={`/tag/?name=${name.toLowerCase().replace(' ', '-')}`} className="tag-button">
        <div className={isEventTag ? "event-tag" : "tag"}>{name}</div>
      </Link>
  );
};
