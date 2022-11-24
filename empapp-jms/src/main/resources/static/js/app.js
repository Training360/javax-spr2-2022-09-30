window.onload = function() {
    const source = new EventSource("api/gps");
    source.addEventListener("gps", function(event) {
        const data = JSON.parse(event.data);
        const div = document.querySelector("#messages-div");
        div.innerHTML += `<p>Vehicle: ${data.vehicleId}, Coords: ${data.coordinates.lat}, ${data.coordinates.lon}</p>`;
    });
}