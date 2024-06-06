import {CommentModel} from "../../../../../organisms/comment/model/CommentModel";

export interface PostCommentModel extends CommentModel {
  postId: number;
}