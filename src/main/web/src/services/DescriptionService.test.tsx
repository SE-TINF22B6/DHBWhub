import DescriptionService from './DescriptionService';

describe('DescriptionService', () => {
  describe('shortenDescription', () => {
    it('should return the original text if it is shorter than the max length', () => {
      const text = 'This is a short description.';
      const maxLength = 30;
      const result = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe(text);
    });

    it('should shorten the text and add an ellipsis if it is longer than the max length', () => {
      const text = 'This is a long description that needs to be shortened.';
      const maxLength = 30;
      const result = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe('This is a long description...');
    });

    it('should shorten the text at the last space before the max length', () => {
      const text = 'This is a long description with multiple words.';
      const maxLength = 30;
      const result = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe('This is a long description...');
    });

    it('should not add an ellipsis if the text is exactly the max length', () => {
      const text = 'Description with 30 characters';
      const maxLength = 30;
      const result = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe(text);
    });

    it('should shorten the text without cutting off a word', () => {
      const text = 'This is a long description with multiple words.';
      const maxLength = 32;
      const result = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe('This is a long description with...');
    });
  });
});
