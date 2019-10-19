# Instructions

To run  test server, you will have to follow steps list on this document.
## Step 1
Install nodejs on you computer, and add the executing files' directory to the **PATH**. 


## Step 2
Install the supporting packages on the list:
+ body-parser
+ express
+ expresss
+ jsonwebtoken
+ mongoose
+ morgan
+ touch

## Step 3
Open an command shell under the server root directory and execute command:
```powershell
npm start
```
After the server has been initialized, we may test it by visiting "http://localhost" in a browser.
Seeing "it works" in your browser presents that the server has been boosted up successfully.

> **Tips:** For more information of Nodejs, you may visit <a href="https://nodejs.org/en/">https://nodejs.org/en/</a>


# Add Request Path and Response
## 1. Define a response data:

Data file is stored under *models* folder.
You may create a file, named *customized.js*, and fill in code like this:
```javascript
//customized.js
module.exports = {
    "data":{
        result:1,
        msg:"success",
        // you may add more members here
    }
}
```
## 2. Define routers:
Request path is defined in *app/handler.js*.
You will have import your customed data and add request handler in module.
```javascript
var template = require("./models/template")
// import customized data
var customizedData = require("./models/customized")
module.exports ={
   "templateApi":["/api/template",function(request,response,next){
        response.send(JSON.stringify(template.templatedata))
   }],
   // add request handler
   "customized":["/api/customized",function(request,response,next){
       // You may add more processing logic here.
        response.send(JSON.stringify(customizedData.data))// send response data
   }]
}
```
## 3. Mapping the router.
In order to get it work, in *index.js*， after line 15, you will have to add statements:
```javascript
...line 15...
apirouters.get(handlers.templateApi[1],handlers.templateApi[1])
apirouters.post(handlers.templateApi[1],handlers.templateApi[1])
```

Then we may get customized data by sending post/get request to address "http://localhost:8080/api/customized".


**Have a happy testing day!**
                      