import {CommentModel} from "./CommentModel";

export interface PostThreadModel {
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
  userImage: string;
  comments: CommentModel[];
}