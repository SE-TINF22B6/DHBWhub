export interface CommentModel {
  commentId: number;
  text: string;
  authorUsername: string | null;
  authorImage: string | null;
  accountId: number;
  timestamp: number;
  likeAmount: number;
}