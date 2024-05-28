import config from "../config/config";
import {getJWT} from "./AuthService";

const sendReportToBackend = (reportReason: string, reportDescription: string, postId: number, authorId: number | null, userId: number): void => {
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  const report = {
    reportReason: reportReason,
    reportDescription: reportDescription,
    postId: postId,
    authorId: authorId,
    userId: userId,
  };

  fetch(config.apiUrl + 'report', {
    method: 'POST',
    headers: headersWithJwt,
    body: JSON.stringify(report),
    credentials: 'include',
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.json();
  })
  .then(data => {
    console.log(data);
    alert('Report has been sent!');
  })
  .catch(error => {
    console.error('Fetch error:', error);
    alert('Error sending the report');
  });
};

const ReportService = {
  sendReportToBackend,
};

export default ReportService;