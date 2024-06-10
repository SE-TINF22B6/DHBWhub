export const shortenDescription = (text: string, maxLength: number): string => {

    if (text.length <= maxLength) {
        return text;
    }
    let shortenedText: string = text.slice(0, maxLength);
    let lastSpace: number = shortenedText.lastIndexOf(' ');

    if (lastSpace === -1 || lastSpace === maxLength - 1) {
        return shortenedText.trim() + '...';
    }
    return shortenedText.slice(0, lastSpace).trim() + '...';
};