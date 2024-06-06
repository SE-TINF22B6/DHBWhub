import {CommentModel} from "../../../organisms/comment/model/CommentModel";

export interface EventCommentModel extends CommentModel {
  eventId: number;
}