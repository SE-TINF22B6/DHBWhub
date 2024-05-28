export interface PostProposalModel {
  title: string;
  description: string;
  tags: string[] | null;
  timestamp: number;
  image: null | string;
  accountId: number;
}