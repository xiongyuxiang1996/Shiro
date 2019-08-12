//点击用户头像
$("#quit").click(function (e){
    e.stopPropagation(); //阻止冒泡
    if($("#quitlist").is(':visible')){
        $("#quitlist").slideUp(300);
    }
    else{
        $("#quitlist").slideDown(300);
        $(document).one("click",function(){
            $("#quitlist").slideUp(300);
        });
    }
});
var iframe = document.getElementById("iframe");
iframe.onload = function() {
    iframe.contentDocument.onclick = function () {
        $("#quitlist").slideUp(300);
    }
};

//个人信息
$("#userinfo").bind("click",function(){
    // $("#iframe").attr("src","/ysfwpt/ysfwpt_home_userinfo?" + username);
});

//修改密码
$("#changepsw").bind("click",function(){
    // $("#iframe").attr("src","/ysfwpt/ysfwpt_home_password?" + username);
});

//退出
$("#quitlogin").click(function (){
    window.location.href = "/logout";
});