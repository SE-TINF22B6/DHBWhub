import {PostCommentModel} from "../../Home/components/post/models/PostCommentModel";

export interface EventDetailModel {
  id: number,
  title: string,
  description: string,
  tags: string[],
  locationProposal: {
    location: string,
    latitude: number,
    longitude: number
  },
  likeAmount: number,
  commentAmount: number,
  startDate: number,
  endDate: number,
  comments: PostCommentModel[]
}