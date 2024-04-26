import React from 'react';
import './PopularTags.css';
import {Tag} from "../../../../atoms/Tag";

export const PopularTags = () => {
  const tags: string[] = ['Freizeit', 'BWL', 'Dozenten', 'Klausuren', 'Informatik', 'Sport', 'Veranstaltungen', 'Projekte'];

  return (
      <div className="popular-tags">
        <div className="component-headline">Popular tags</div>
        <div className="popular-tags-list">
          {tags.slice(0, 7).map((tag: string, index: number) =>
              <Tag name={tags[index]} key={index} index={index} isEventTag={false}/>
          )}
        </div>
      </div>
  );
};