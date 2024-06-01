import {PostCommentModel} from "../../Home/components/post/models/PostCommentModel";

export interface PostThreadModel {
  id: number;
  title: string;
  description: string;
  tags: string[];
  likeAmount: number;
  commentAmount: number;
  timestamp: number;
  postImage: null | string;
  accountId: number;
  username: string;
  userImage: null | string;
  comments: PostCommentModel[];
}