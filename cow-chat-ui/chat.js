  let stompClient = null;
  const subscriptions = {}; // guardar subscripciones por chatId

  function connect() {
    const socket = new SockJS("http://localhost:8080/chat");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
      console.log("Conectado: " + frame);
    });
  }

  // suscribirse dinámicamente a un chat
  function subscribeToChat(chatId) {
    if (!subscriptions[chatId]) {
      const sub = stompClient.subscribe(`/topic/chat.${chatId}`, function (msg) {
        showMessage(JSON.parse(msg.body), chatId);
      });
      subscriptions[chatId] = sub;
      console.log("Suscrito al chat:", chatId);
    }
  }

  function sendMessage(chatId) {

    if(!subscriptions[chatId]) {
      subscribeToChat(chatId);
    }

    const input = document.getElementById("messageInput");
    const message = {
      chatId: chatId,
      senderId: "user1",
      content: input.value,
    };

    stompClient.send(`/app/chat.${chatId}.send`, {}, JSON.stringify(message));
    input.value = "";
  }

  function showMessage(message, chatId) {
    const li = document.createElement("li");
    li.innerText = message.content;
    document.getElementById("messages").appendChild(li);
  }

  // iniciar la conexión una vez
  connect();