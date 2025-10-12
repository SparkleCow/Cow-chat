import { MessageType } from "./message-request-dto";
export interface MessageResponseDto {
  id: string;
  content: string;
  messageType: MessageType;
  filePath?: string | null;
  chatId: string;
  senderId: string;
  recipientIds: string[];
  timestamp: string;
  delivered: boolean;
  read: boolean;
}
