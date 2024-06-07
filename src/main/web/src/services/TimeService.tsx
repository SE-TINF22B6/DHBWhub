export const timeDifference = (timestamp: Date): string => {
  let postTime: number = (timestamp).getTime();
  if (postTime.toString().length > 13) {
    const postTimeStr: string = postTime.toString();
    postTime = parseInt(postTimeStr.substring(0, 13));
  }
  const currentTime: number = new Date().getTime();

  const difference: number = currentTime - postTime;

  const seconds: number = Math.floor(difference / 1000);
  const minutes: number = Math.floor(seconds / 60);
  const hours: number = Math.floor(minutes / 60);
  const days: number = Math.floor(hours / 24);

  if (days > 0) {
    return `${days} day${days > 1 ? 's' : ''} ago`;
  } else if (hours > 0) {
    return `${hours} hour${hours > 1 ? 's' : ''} ago`;
  } else if (minutes > 0) {
    return `${minutes} minute${minutes > 1 ? 's' : ''} ago`;
  } else {
    return 'Just now';
  }
};