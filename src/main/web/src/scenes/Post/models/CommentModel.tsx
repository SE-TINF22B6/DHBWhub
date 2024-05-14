export interface CommentModel {
  postId: number,
  commentId: number,
  text: string,
  username: string,
  userImage: string | null,
  accountId: number,
  timestamp: number,
  likeAmount: number;
}