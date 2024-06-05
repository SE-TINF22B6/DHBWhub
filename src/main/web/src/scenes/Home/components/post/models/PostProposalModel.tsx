export interface PostProposalModel {
  title: string;
  description: string;
  tags: string[] | null;
  timestamp: number;
  postImage: null | string;
  accountId: number;
}