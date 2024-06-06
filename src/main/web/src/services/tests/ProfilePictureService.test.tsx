import ProfilePictureService from '../ProfilePictureService';
import config from "../../config/config";
import { getJWT, getUserId, isUserLoggedIn } from "../AuthService";

jest.mock('../AuthService', () => ({
  getJWT: jest.fn(),
  getUserId: jest.fn(),
  isUserLoggedIn: jest.fn()
}));

global.fetch = jest.fn();

describe('ProfilePictureService', () => {
  beforeEach((): void => {
    jest.clearAllMocks();
  });

  it('should fetch user image if user is logged in', async (): Promise<void> => {
    const mockImageData = 'mockImageData';
    const mockResponse = {
      ok: true,
      json: jest.fn().mockResolvedValue({ imageData: mockImageData })
    };
    (getJWT as jest.Mock).mockReturnValue('mockJwt');
    (getUserId as jest.Mock).mockReturnValue(1);
    (isUserLoggedIn as jest.Mock).mockReturnValue(true);
    (fetch as jest.Mock).mockResolvedValue(mockResponse);

    const result: string | null = await ProfilePictureService.fetchUserImage();

    expect(fetch).toHaveBeenCalledWith(`${config.apiUrl}picture/find/1`, {
      headers: {
        ...config.headers,
        'Authorization': 'Bearer mockJwt'
      }
    });
    expect(localStorage.getItem('userImage')).toBe(mockImageData);
    expect(result).toBe(mockImageData);
  });

  it('should return null if user is not logged in', async (): Promise<void> => {
    (isUserLoggedIn as jest.Mock).mockReturnValue(false);

    const result: string | null = await ProfilePictureService.fetchUserImage();

    expect(fetch).not.toHaveBeenCalled();
    expect(result).toBeNull();
  });

  it('should return null and log error if fetch fails', async (): Promise<void> => {
    const consoleErrorSpy = jest.spyOn(console, 'error').mockImplementation();
    (getJWT as jest.Mock).mockReturnValue('mockJwt');
    (getUserId as jest.Mock).mockReturnValue(1);
    (isUserLoggedIn as jest.Mock).mockReturnValue(true);
    (fetch as jest.Mock).mockRejectedValue(new Error('Fetch failed'));

    const result: string | null = await ProfilePictureService.fetchUserImage();

    expect(fetch).toHaveBeenCalledWith(`${config.apiUrl}picture/find/1`, {
      headers: {
        ...config.headers,
        'Authorization': 'Bearer mockJwt'
      }
    });
    expect(consoleErrorSpy).toHaveBeenCalledWith("Error fetching user image:", new Error('Fetch failed'));
    expect(result).toBeNull();

    consoleErrorSpy.mockRestore();
  });

  it('should return null if response is not ok', async (): Promise<void> => {
    const mockResponse = {
      ok: false
    };
    const consoleLogSpy = jest.spyOn(console, 'log').mockImplementation();
    (getJWT as jest.Mock).mockReturnValue('mockJwt');
    (getUserId as jest.Mock).mockReturnValue(1);
    (isUserLoggedIn as jest.Mock).mockReturnValue(true);
    (fetch as jest.Mock).mockResolvedValue(mockResponse);

    const result: string | null = await ProfilePictureService.fetchUserImage();

    expect(fetch).toHaveBeenCalledWith(`${config.apiUrl}picture/find/1`, {
      headers: {
        ...config.headers,
        'Authorization': 'Bearer mockJwt'
      }
    });
    expect(consoleLogSpy).toHaveBeenCalledWith(new Error("Failed to fetch user image"));
    expect(result).toBeNull();

    consoleLogSpy.mockRestore();
  });
});
