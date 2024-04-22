const sendReportToBackend = (reportReason: string, reportDescription: string, postId: number, authorId: number, userId: number): void => {
  const report = {
    reportReason: reportReason,
    reportDescription: reportDescription,
    postId: postId,
    authorId: authorId,
    userId: userId,
  };

  fetch("http://localhost:8080/report", {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
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
  })
  .catch(error => {
    console.error('Fetch error:', error);
  });
};

const ReportService = {
  sendReportToBackend,
};

export default ReportService;