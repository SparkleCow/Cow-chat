package com.sparklecow.cowchat.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {

    @Query("""
    SELECT c
    FROM Chat c
    JOIN c.participants p
    WHERE p.id IN (:senderId, :recipientId)
    GROUP BY c
    HAVING COUNT(DISTINCT p.id) = 2
    """)
    Optional<Chat> findChatBetweenUsers(@Param("senderId") String senderId,
                                        @Param("recipientId") String recipientId);
}
