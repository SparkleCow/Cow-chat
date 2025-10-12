export interface MessageResponseDto {
  id: string;
  content: string;
  messageType: string;
  filePath: string | null;
  senderId: string;
  recipientIds: string[];
  timestamp: string;
  delivered: boolean;
  read: boolean;
}
