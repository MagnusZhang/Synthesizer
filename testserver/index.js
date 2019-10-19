var express = require('express')
var app = express()
var bodyParser = require('body-parser')
var port = process.env.PORT || 8080; // starting port
var apirouters = express.Router()
var handlers = require("./app/handlers")
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: false}))
//register post handler
apirouters.get("/",function(request,response,next){
    response.send("it works")
})

apirouters.get(handlers.templateApi[0],handlers.templateApi[1])
apirouters.post(handlers.templateApi[0],handlers.templateApi[1])

app.use(apirouters)
app.listen(port)