package com.sparklecow.cowchat.message;

import com.sparklecow.cowchat.common.BaseAuditing;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats")
@Builder
public class Message extends BaseAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String content;

    private MessageType messageType;

    private String filePath;


}
