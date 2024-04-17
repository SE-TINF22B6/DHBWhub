import React from 'react';
import './Tag.css';
import {Link} from "react-router-dom";


interface TagProps {
  name: string
  index: number;
}

export const Tag : React.FC<TagProps> = (props: TagProps) => {
  let {name, index} = props;

  return (
      <Link key={index} to={`/tag/?name=${name.toLowerCase().replace(' ', '-')}`} className="tag-button">
        <div className="tag">{name}</div>
      </Link>
  );
};
