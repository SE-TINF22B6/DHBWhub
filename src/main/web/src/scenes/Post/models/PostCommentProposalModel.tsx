import {CommentProposalModel} from "../../../organisms/comment/model/CommentProposalModel";

export interface PostCommentProposalModel extends CommentProposalModel {
  postId: number;
}