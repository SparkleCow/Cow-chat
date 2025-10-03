package com.sparklecow.cowchat.message;

import com.sparklecow.cowchat.chat.Chat;
import com.sparklecow.cowchat.common.BaseAuditing;
import com.sparklecow.cowchat.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
@Builder
public class Message extends BaseAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    private LocalDateTime timestamp;

    private boolean delivered;

    private boolean read;
}
