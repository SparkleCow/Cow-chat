import { MessageResponseDto } from "./message-response-dto";

export interface ChatResponseDto {
  id: string;
  name: string;
  participantsId: string[];
  messages: MessageResponseDto[];
}
