import config from "../config/config";
import {getJWT, getUserId} from "./AuthService";

export const sendReportToBackend = (reportReason: string, reportDescription: string, postId: number, authorId: number | null, type: string,
                                    commentId?: number | null, ): void => {
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };
  const userId: number | null = getUserId();

  const report = {
    reportReason: reportReason,
    reportDescription: reportDescription,
    postId: postId,
    authorId: authorId,
    userId: userId,
    commentId: commentId,
    type: type
  };

  console.log('Report:', JSON.stringify(report));

  fetch(config.apiUrl + 'post/report', {
    method: 'POST',
    headers: headersWithJwt,
    body: JSON.stringify(report),
  })
  .then(response => {
    if (!response.ok) {
      console.log(response);
      throw new Error('Error sending report: ' + response.statusText);
    }
    alert('Report of ' + type + ' ' + postId + ' sent successfully');
  });
};