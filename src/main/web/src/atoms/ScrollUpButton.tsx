import React, { useState, useEffect } from 'react';

interface ScrollUpButtonProps {
  scrollUpRef: React.RefObject<HTMLDivElement>;
}

const ScrollUpButton: React.FC<ScrollUpButtonProps> = ({ scrollUpRef }) => {
  const [scrollUp, setScrollUp] = useState(false);

  useEffect(() => {
    if (scrollUp && scrollUpRef.current) {
      scrollUpRef.current.scrollIntoView({ behavior: "smooth" });
    }
    setScrollUp(false);
  }, [scrollUp, scrollUpRef]);

  const handleScrollUpClick = () => {
    setScrollUp(true);
  };

  return (
      <img
          alt={"Up"}
          src={process.env.PUBLIC_URL + '/assets/up.svg'}
          onClick={handleScrollUpClick}
          style={{opacity: "1", transition: "opacity 0.3s", marginTop: "10px", cursor: "pointer"}}
          onMouseOver={(e) => e.currentTarget.style.opacity = "0.7"}
          onMouseOut={(e) => e.currentTarget.style.opacity = "1"}
      />
  );
};

export default ScrollUpButton;
