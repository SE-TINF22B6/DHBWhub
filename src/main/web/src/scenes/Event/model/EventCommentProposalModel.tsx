import {CommentProposalModel} from "../../../organisms/comment/model/CommentProposalModel";

export interface EventCommentProposalModel extends CommentProposalModel {
  eventId: number;
}