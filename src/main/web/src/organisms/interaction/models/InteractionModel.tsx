export interface InteractionModel {
  id: number;
  likes: number;
  userLiked: boolean;
  heartClass: string;
  comments: number | null;
  handleLike: () => Promise<void>;
  isHomepage?: boolean;
}