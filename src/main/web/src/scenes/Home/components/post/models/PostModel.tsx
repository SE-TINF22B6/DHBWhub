export interface PostModel {
  id: number;
  title: string;
  description: string;
  tags: string[] | null;
  likeAmount: number;
  commentAmount: number;
  timestamp: number;
  postImage: null | string;
  accountId: number;
  username: string;
}