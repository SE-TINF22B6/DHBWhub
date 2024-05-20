import TimeService from './TimeService';

describe('TimeService', () => {
  describe('timeDifference', () => {
    it('should return "Just now" for a timestamp within the last minute', () => {
      const timestamp = new Date(Date.now() - 10000).toISOString(); // 10 seconds ago
      expect(TimeService.timeDifference(timestamp)).toBe('Just now');
    });

    it('should return the correct minute difference for a timestamp within the last hour', () => {
      const timestamp = new Date(Date.now() - 300000).toISOString(); // 5 minutes ago
      expect(TimeService.timeDifference(timestamp)).toBe('5 minutes ago');
    });

    it('should return the correct hour difference for a timestamp within the last day', () => {
      const timestamp = new Date(Date.now() - 3600000).toISOString(); // 1 hour ago
      expect(TimeService.timeDifference(timestamp)).toBe('1 hour ago');
    });

    it('should return the correct day difference for a timestamp older than a day', () => {
      const timestamp = new Date(Date.now() - 86400000 * 2).toISOString(); // 2 days ago
      expect(TimeService.timeDifference(timestamp)).toBe('2 days ago');
    });
  });
});
