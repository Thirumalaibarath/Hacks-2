const express = require('express'); 
const socket = require('socket.io'); 
const app = express();
var PORT = process.env.PORT || 3000;
const server = app.listen(PORT); 


console.log('Server is running');
const io = socket(server);

let principle_array = [["#FF5733","#FF5733","#FF5733"],["#FF5733","#FFFFFF","#FFFFFF"],["#FFFFFF","#FFFFFF","#FFFFFF"]]


io.on('connection', (socket) => {

    console.log("New socket connection: " + socket.id)


    // socket.on('counter', () => {
    //     io.emit('current_color', principle_array);
    // })

    function emitter()
    {
        io.emit('counter', principle_array);
    }
   

    setInterval(emitter,1000)

    socket.on('message',(data) =>{
        console.log(data)
    })

})


