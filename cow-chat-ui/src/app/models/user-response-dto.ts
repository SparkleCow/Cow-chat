export interface UserResponseDto{
  id: string;
  username: string;
  email: string;
  imagePath: string;
  lastSeen: string;
  isOnline: boolean;
  presignedUrl: string;
}
