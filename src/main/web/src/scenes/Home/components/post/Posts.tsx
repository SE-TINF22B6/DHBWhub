import React from "react";
import "./Posts.css";
import { Post } from "./Post";

interface PostsProps {
  sortOption: string;
}
export const Posts: React.FC<PostsProps> = ({ sortOption }) => {

  const dummyPosts = [
    {
      postId: 1,
      title: "Wie man erfolgreich Leads generiert!",
      description: "Wie generiert man erfolgreich Leads und treibt sein Geschäft voran? Entdecken Sie bewährte Strategien, um " +
          "qualitativ hochwertige Leads zu generieren und Ihr Geschäft voranzutreiben. Wie generiert man erfolgreich Leads und treibt " +
          "sein Geschäft voran? Entdecken Sie bewährte Strategien, um " +
          "qualitativ hochwertige Leads zu generieren und Ihr Geschäft voranzutreiben",
      tags: ["Marketing", "BWL", "Dozenten"],
      authorUsername: "Dietmar Bohn",
      authorId: 1,
      postCreatedTimestamp: "2023-11-06T12:34:30",
      initialLikes: 190,
      initialComments: 3,
      imageSrc: "/assets/home/post/image-example.svg"
    },
    {
      postId: 2,
      title: "Diese Future Skills sind essenziell!",
      description: "Future Skills für eine KI geprägte Welt - wo stehen wir im Bildungssystem bei den beiden Megatrends: Future " +
          "Skills und KI. Future Skills für eine KI geprägte Welt - wo stehen wir im Bildungssystem bei den beiden Megatrends: Future " +
          "Skills und KI",
      tags: ["Dirk Dual", "Zukunft"],
      authorUsername: "Ulf-Daniel Ehlers",
      authorId: 2,
      postCreatedTimestamp: "2023-11-09T22:00:30",
      initialLikes: 13,
      initialComments: 0,
      imageSrc: "/assets/home/post/image-example-2.svg"
    },
    {
      postId: 3,
      title: "Gymfinder: So findet ihr die besten Gyms!",
      description: "Entdeckt die besten Gyms in eurer Nähe! Mit der neuen Website Gymfinder findet ihr mühelos das ideale Fitnessstudio, das zu euren Bedürfnissen passt. Egal, ob ihr nach hochmodernen Trainingsgeräten, qualifizierten Trainern oder besonderen Kursangeboten sucht, Gymfinder bietet eine umfassende Übersicht über die besten Fitnessstudios in eurer Nähe. Durchsucht die umfangreiche Datenbank, vergleicht die Ausstattung und Dienstleistungen der Gyms und findet so das perfekte Studio für eure individuellen Ziele.",
      tags: ["Sport", "Freizeit", "Gym"],
      authorUsername: "Markus Rühl",
      authorId: 3,
      postCreatedTimestamp: "2023-11-16T22:00:30",
      initialLikes: 187,
      initialComments: 0,
      imageSrc: "/assets/home/post/image-example-3.svg"
    },
  ];

  const sortedPosts = () => {
    switch (sortOption) {
      case "newest":
        return dummyPosts.sort((a, b) => Date.parse(b.postCreatedTimestamp) - Date.parse(a.postCreatedTimestamp));
      case "following":
        // TODO: Implementiere Logik für "Following" Sortierung
        return dummyPosts;
      case "popular":
        return dummyPosts.sort((a, b) => b.initialLikes - a.initialLikes);
      default:
        return dummyPosts;
    }
  };

  return (
      <div className="posts">
        {sortedPosts().map(post => (
            <Post
                key={post.postId}
                postId={post.postId}
                title={post.title}
                description={post.description}
                tags={post.tags}
                authorUsername={post.authorUsername}
                authorId={post.authorId}
                postCreatedTimestamp={post.postCreatedTimestamp}
                initialLikes={post.initialLikes}
                initialComments={post.initialComments}
                imageSrc={post.imageSrc}
            />
        ))}
      </div>
  );
};