const shortenDescription = (text: string, maxLength: number): string => {
  if (text.length <= maxLength) {
    return text;
  }

  let shortenedText = text.slice(0, maxLength);
  let lastSpace = shortenedText.lastIndexOf(' ');

  if (lastSpace === -1 || lastSpace === maxLength - 1) {
    return shortenedText.trim() + '...';
  }

  return shortenedText.slice(0, lastSpace).trim() + '...';
};

const DescriptionService = {
  shortenDescription,
};

export default DescriptionService;