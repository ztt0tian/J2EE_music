function collectornot() {
    var location = (window.location+'').split('/');
    var basePath = location[0]+'//'+location[2]+'/'+location[3];
    var obj = document.getElementById("collect-image");
    // alert(basePath)
    // alert(obj.getAttribute("src"))
    // alert(basePath+"/image/love.png")
    if (obj.getAttribute("src") == basePath+"/image/love.png") {
        //收藏
        obj.setAttribute("src", basePath+"/image/loveyes.png");
    }
    else {
        //取消收藏
        obj.setAttribute("src", basePath+"/image/love.png");
    }
}
function collectornot1() {
    var location = (window.location+'').split('/');
    var basePath = location[0]+'//'+location[2]+'/'+location[3];
    var obj = document.getElementById("collect");
    // alert(basePath)
    // alert(obj.getAttribute("src"))
    // alert(basePath+"/image/love.png")
    var login_user = '<%=request.getAttribute("login_user");%>' ;
    alert(login_user);
    if (obj.getAttribute("src") == basePath+"/image/collection.png") {
        //收藏
        obj.setAttribute("src", basePath+"/image/collection_suc.png");
    }
    else {
        //取消收藏
        obj.setAttribute("src", basePath+"/image/collection.png");
    }
}