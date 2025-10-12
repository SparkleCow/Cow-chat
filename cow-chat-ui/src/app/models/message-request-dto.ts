export interface MessageRequestDto {
  chatId: string;
  senderId: string;
  content: string;
  messageType: MessageType;
  filePath: string;
  recipientIds: string[];
}

export enum MessageType {
  TEXT = 'TEXT',
  IMAGE = 'IMAGE',
  DOCUMENT = 'DOCUMENT'
}
