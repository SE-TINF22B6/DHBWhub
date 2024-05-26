import LikeService from '../LikeService';
import fetchMock from 'fetch-mock';
import config from "../../config/config";
import {getJWT} from "../AuthService";

describe('LikeService', (): void => {
  const jwt: string | null = getJWT();
  const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
  };

  describe('handleLike', (): void => {
    const postId = 123;
    const initialLikes = 10;

    afterEach(() => {
      fetchMock.restore();
      localStorage.clear();
    });

    it('should handle a like correctly', async (): Promise<void> => {
      const setLikes = jest.fn();
      const setUserLiked = jest.fn();
      const setHeartClass = jest.fn();

      fetchMock.post(config.apiUrl + `increase-likes/${postId}`, {
        status: 200,
      });

      await LikeService.handleLike(
          postId,
          false,
          initialLikes,
          setLikes,
          setUserLiked,
          setHeartClass
      );

      expect(setLikes).toHaveBeenCalledWith(initialLikes + 1);
      expect(setUserLiked).toHaveBeenCalledWith(true);
      expect(setHeartClass).toHaveBeenCalledWith('heart-filled');
      expect(localStorage.getItem(`liked_${postId}`)).toBe('true');
      expect(fetchMock.called(config.apiUrl + `increase-likes/${postId}`)).toBe(true);
      expect(fetchMock.lastOptions(config.apiUrl + `increase-likes/${postId}`)).toEqual({
        method: 'POST',
        headers: headersWithJwt
      });
    });

    it('should handle an unlike correctly', async (): Promise<void> => {
      const setLikes = jest.fn();
      const setUserLiked = jest.fn();
      const setHeartClass = jest.fn();

      fetchMock.post(config.apiUrl + `decrease-likes/${postId}`, {
        status: 200,
      });

      await LikeService.handleLike(
          postId,
          true,
          initialLikes,
          setLikes,
          setUserLiked,
          setHeartClass
      );

      expect(setLikes).toHaveBeenCalledWith(initialLikes - 1);
      expect(setUserLiked).toHaveBeenCalledWith(false);
      expect(setHeartClass).toHaveBeenCalledWith('heart-empty');
      expect(localStorage.getItem(`liked_${postId}`)).toBe(null);
      expect(fetchMock.called(config.apiUrl + `decrease-likes/${postId}`)).toBe(true);
      expect(fetchMock.lastOptions(config.apiUrl + `decrease-likes/${postId}`)).toEqual({
        method: 'POST',
        headers: headersWithJwt
      });
    });

    it('should handle errors correctly', async (): Promise<void> => {
      const setLikes = jest.fn();
      const setUserLiked = jest.fn();
      const setHeartClass = jest.fn();

      fetchMock.post(config.apiUrl + `decrease-likes/${postId}`, {
        status: 500,
        body: { error: 'Internal server error' },
      });

      expect(async (): Promise<void> => {
        await LikeService.handleLike(
            postId,
            false,
            initialLikes,
            setLikes,
            setUserLiked,
            setHeartClass
        );
      }).not.toThrow();

      expect(setLikes).not.toHaveBeenCalled();
      expect(setUserLiked).not.toHaveBeenCalled();
      expect(setHeartClass).not.toHaveBeenCalled();
      expect(localStorage.getItem(`liked_${postId}`)).toBe(null);
    });
  });
});
