import DescriptionService from './services/DescriptionService';

describe('DescriptionService', (): void => {
  describe('shortenDescription', (): void => {
    it('should return the original text if it is shorter than the max length', (): void => {
      const text: string  = 'This is a short description.';
      const maxLength: number = 30;
      const result: string  = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe(text);
    });

    it('should shorten the text and add an ellipsis if it is longer than the max length', (): void => {
      const text: string  = 'This is a long description that needs to be shortened.';
      const maxLength: number = 30;
      const result: string  = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe('This is a long description...');
    });

    it('should shorten the text at the last space before the max length', (): void => {
      const text: string  = 'This is a long description with multiple words.';
      const maxLength: number = 30;
      const result: string  = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe('This is a long description...');
    });

    it('should not add an ellipsis if the text is exactly the max length', (): void => {
      const text: string  = 'Description with 30 characters';
      const maxLength: number = 30;
      const result: string  = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe(text);
    });

    it('should shorten the text without cutting off a word', (): void => {
      const text: string  = 'This is a long description with multiple words.';
      const maxLength: number = 32;
      const result: string = DescriptionService.shortenDescription(text, maxLength);
      expect(result).toBe('This is a long description with...');
    });
  });
});
