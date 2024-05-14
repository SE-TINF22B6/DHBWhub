export interface PostDetailModel {
  id: number;
  title: string;
  description: string;
  tags: string[];
  likeAmount: number;
  commentAmount: number;
  timestamp: number;
  image: null | string;
  accountId: number;
  username: string;
  userImage: null | string;
  setScrollToComments: (scrollToComments: boolean) => void;
}