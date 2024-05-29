import {CommentModel} from "../../../../../organisms/comment/CommentModel";

export interface PostCommentModel extends CommentModel {
  postId: number;
}