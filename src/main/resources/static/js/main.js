'use strict';
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var chatsArea = document.querySelector('#chatsArea');
var connectingElement = document.querySelector('.connecting');
var stompClient = null;
var username = null;
var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];
function connect(event) {
    username = document.querySelector('#name').value.trim();
    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);
    // Tell your username to the server
    fetch("chat/history",
        {
            method: "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .then( response => {
            if (response.status !== 200) {

                return Promise.reject();
            }
            return response.json();
        })
        .then(historyMessage => {
            historyMessage.forEach(function(item, historyMessage){
                 drawMessage(item);
            });
        })
        .catch(() => console.log('Error messages'));
    fetch("chats",
                    {
                        method: "POST",
                        body: JSON.stringify({userName:username}),
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        }
                    })
                    .then( response => {
                        if (response.status !== 200) {

                            return Promise.reject();
                        }
                        return response.json();
                    })
                    .then(chats => {
                        chats.forEach(function(item, chats){
                             drawChatsArea(item);
                        });
                    })
                    .catch(() => console.log('Error chats'));

    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}
function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}
function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    drawMessage(message);
}
function drawChatsArea(chat){

    var chatsElement = document.createElement('li');
     chatsElement.classList.add('.area-message')
    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode (chat.chatName[0]);
    avatarElement.appendChild (avatarText);
    avatarElement.style['background-color'] = getAvatarColor(chat.chatName);
    chatsElement.appendChild(avatarElement);
    var chatNameElement = document.createElement('span');
    var chatNameText = document.createTextNode(chat.chatName);
    chatNameElement.appendChild(chatNameText);
    chatsElement.appendChild(chatNameElement);
    chatsArea.appendChild(chatsElement);
    chatsArea.scrollTop = chatsArea.scrollHeight;
}
function drawMessage(message) {
    var messageElement = document.createElement('li');
    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);
        messageElement.appendChild(avatarElement);
        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    if (message.type === 'CHAT') {
        var select = document.createElement('select');
        var option1 = document.createElement('option');
        var option2 = document.createElement('option');
        var textInOption = document.createTextNode('li1');
        var textInOption1 = document.createTextNode('li2');
        option1.appendChild(textInOption);
        option2.appendChild(textInOption1);
        select.appendChild(option1);
        select.appendChild(option2);
        messageElement.appendChild(select);
    }
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}
function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}
usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
