import {CommentModel} from "../../../organisms/comment/CommentModel";

export interface EventCommentModel extends CommentModel {
  eventId: number;
}