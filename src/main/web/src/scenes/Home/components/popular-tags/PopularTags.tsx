import React, {useEffect, useState} from 'react';
import {Tag} from "../../../../atoms/Tag";
import config from "../../../../config/config";
import './PopularTags.css';

export const PopularTags = () => {
  const [popularTags, setPopularTags] = useState<string[]>();

  useEffect((): void => {
    const fetchPopularTags = async (): Promise<void> => {
      try {
        const response: Response = await fetch(config.apiUrl + "post/popular-tags", {
          headers: config.headers
        });
        if (response.ok) {
          const popularTags = await response.json();
          setPopularTags(popularTags);
        } else {
          console.log(new Error("Failed to fetch popular tags"));
        }
      } catch (error) {
        console.error("Error fetching popular tags:", error);
      }
    };
    fetchPopularTags();
  }, []);

  if (popularTags?.length === 0 || popularTags === undefined) {
    return null;
  }

  return (
      <div className="popular-tags">
        <div className="component-headline">Popular tags</div>
        {popularTags && (
            <div className="popular-tags-list">
              {popularTags.slice(0, 7).map((tag: string, index: number) =>
                  <Tag name={popularTags[index]} key={index} index={index} isEventTag={false}/>
              )}
            </div>
        )}
      </div>
  );
};