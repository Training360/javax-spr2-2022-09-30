window.onload = function() {

    const socket = new SockJS("/websocket-endpoint");
    const client = Stomp.over(socket);
    
    document.querySelector("#message-button").onclick = function() {
        const text = document.querySelector("#message-input").value;
        const message = {"text": text};
        client.send("/app/messagesDestination", {}, JSON.stringify(message));
    }

    client.connect({}, function(frame) {
        client.subscribe("/broker/employeesTopic", function(message) {
            const text = JSON.parse(message.body).text;
            document.querySelector("#messages-id").innerHTML += `<p>${text}</p>`
        });
    });

}