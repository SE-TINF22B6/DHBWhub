import ReportService from '../ReportService';
import fetchMock from 'fetch-mock';
import config from "../../config/config";

describe('ReportService', (): void => {
  describe('sendReportToBackend', (): void => {
    afterEach(() => {
      fetchMock.restore();
    });

    it('should send a report to the backend successfully', async (): Promise<void> => {
      const reportReason = 'Spam';
      const reportDescription = 'This post is spam';
      const postId = 123;
      const authorId = 456;
      const userId = 789;

      fetchMock.post(config.apiUrl + 'report', {
        status: 200,
        body: { message: 'Report submitted successfully' },
      });

      ReportService.sendReportToBackend(
          reportReason,
          reportDescription,
          postId,
          authorId,
          userId
      );

      expect(fetchMock.called(config.apiUrl + 'report')).toBe(true);
      expect(fetchMock.lastOptions(config.apiUrl + 'report')).toEqual({
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          reportReason,
          reportDescription,
          postId,
          authorId,
          userId,
        }),
        credentials: 'include',
      });
    });

    it('should handle a failed response from the backend', async (): Promise<void> => {
      const reportReason = 'Spam';
      const reportDescription = 'This post is spam';
      const postId = 123;
      const authorId = 456;
      const userId = 789;

      fetchMock.post(config.apiUrl + 'report', {
        status: 500,
        body: { error: 'Internal server error' },
      });

      await expect(async (): Promise<void> => {
        ReportService.sendReportToBackend(
            reportReason,
            reportDescription,
            postId,
            authorId,
            userId
        );
      }).rejects.toThrow('Network response was not ok');
    });
  });
});
