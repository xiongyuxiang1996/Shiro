if (window != top){
    top.location.href = location.href;
}

var info = $(".loginInfo").val();
if(info == null){
    $(".loginInfo").css("display","none");
}