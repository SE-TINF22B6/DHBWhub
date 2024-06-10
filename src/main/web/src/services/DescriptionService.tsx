export const shortenDescription = (text: string, maxLength: number): string => {
  try {
    if (text.length === 0) {
      console.error("empty Title" + text)
    }
    if (text.length <= maxLength) {
      return text;
    }
    let shortenedText: string = text.slice(0, maxLength);
    let lastSpace: number = shortenedText.lastIndexOf(' ');

    if (lastSpace === -1 || lastSpace === maxLength - 1) {
      return shortenedText.trim() + '...';
    }
    return shortenedText.slice(0, lastSpace).trim() + '...';
  } catch (error) {
    console.error(error);

  }

 return text;

};