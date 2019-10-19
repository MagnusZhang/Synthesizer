var template = require("./models/template")
module.exports ={
   "templateApi":["/api/template",function(request,response,next){
        response.send(JSON.stringify(template.templatedata))
   }]
}