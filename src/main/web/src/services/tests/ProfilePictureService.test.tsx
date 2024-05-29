import ProfilePictureService from '../ProfilePictureService';
import { getJWT, getUserId, isUserLoggedIn } from '../AuthService';
import config from '../../config/config';

jest.mock('../AuthService', () => ({
  getJWT: jest.fn(),
  getUserId: jest.fn(),
  isUserLoggedIn: jest.fn(),
}));

global.console.log = jest.fn();
global.console.error = jest.fn();
global.fetch = jest.fn();

describe('fetchUserImage', (): void => {
  const setUserImage = jest.fn();

  beforeEach((): void => {
    jest.clearAllMocks();
  });

  it('should fetch user image successfully when user is logged in', async (): Promise<void> => {
    const mockData = { image: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz3' };
    const mockResponse = {
      ok: true,
      json: jest.fn().mockResolvedValue(mockData),
    };
    (getJWT as jest.Mock).mockReturnValue('mock-jwt');
    (getUserId as jest.Mock).mockReturnValue(1);
    (isUserLoggedIn as jest.Mock).mockReturnValue(true);
    (global.fetch as jest.Mock).mockResolvedValue(mockResponse);

    await ProfilePictureService.fetchUserImage(setUserImage);

    expect(isUserLoggedIn).toHaveBeenCalled();
    expect(getJWT).toHaveBeenCalled();
    expect(getUserId).toHaveBeenCalled();
    expect(global.fetch).toHaveBeenCalledWith(`${config.apiUrl}picture/find/1`, {
      headers: {
        ...config.headers,
        'Authorization': 'Bearer mock-jwt',
      },
    });
    expect(setUserImage).toHaveBeenCalledWith(mockData);
  });

  it('should not fetch user image if user is not logged in', async (): Promise<void> => {
    (isUserLoggedIn as jest.Mock).mockReturnValue(false);

    await ProfilePictureService.fetchUserImage(setUserImage);

    expect(isUserLoggedIn).toHaveBeenCalled();
    expect(global.fetch).not.toHaveBeenCalled();
    expect(setUserImage).not.toHaveBeenCalled();
    expect(console.log).toHaveBeenCalledWith("User is not logged in: cannot fetch user image.");
  });

  it('should handle fetch failure', async (): Promise<void> => {
    const mockResponse = {
      ok: false,
      statusText: 'Not Found',
    };
    (getJWT as jest.Mock).mockReturnValue('mock-jwt');
    (getUserId as jest.Mock).mockReturnValue(1);
    (isUserLoggedIn as jest.Mock).mockReturnValue(true);
    (global.fetch as jest.Mock).mockResolvedValue(mockResponse);

    await ProfilePictureService.fetchUserImage(setUserImage);

    expect(isUserLoggedIn).toHaveBeenCalled();
    expect(global.fetch).toHaveBeenCalledWith(`${config.apiUrl}picture/find/1`, {
      headers: {
        ...config.headers,
        'Authorization': 'Bearer mock-jwt',
      },
    });
    expect(setUserImage).not.toHaveBeenCalled();
    expect(console.log).toHaveBeenCalledWith(new Error("Failed to fetch user image"));
  });

  it('should handle fetch error', async () => {
    const mockError = new Error('Network Error');
    (getJWT as jest.Mock).mockReturnValue('mock-jwt');
    (getUserId as jest.Mock).mockReturnValue(1);
    (isUserLoggedIn as jest.Mock).mockReturnValue(true);
    (global.fetch as jest.Mock).mockRejectedValue(mockError);

    await ProfilePictureService.fetchUserImage(setUserImage);

    expect(isUserLoggedIn).toHaveBeenCalled();
    expect(global.fetch).toHaveBeenCalledWith(`${config.apiUrl}picture/find/1`, {
      headers: {
        ...config.headers,
        'Authorization': 'Bearer mock-jwt',
      },
    });
    expect(setUserImage).not.toHaveBeenCalled();
    expect(console.error).toHaveBeenCalledWith("Error fetching user image:", mockError);
  });
});
