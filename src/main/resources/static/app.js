// src/main/resources/static/js/app.js
document.addEventListener('DOMContentLoaded', function() {
    const websocket = new WebSocket("ws://localhost:8081/ws/chat");
    const messageInput = document.getElementById("message");
    const chatArea = document.getElementById("chatArea");
    const sendButton = document.getElementById("send");

    websocket.onmessage = function(event) {
        chatArea.innerHTML += '<div>' + event.data + '</div>';
    };

    sendButton.onclick = function() {
        if(messageInput.value) {
            websocket.send(messageInput.value);
            messageInput.value = '';
        }
    };
});
