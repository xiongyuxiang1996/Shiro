// 目录

//目录折叠展开
$(".list1").click(function (e){
    e.stopPropagation(); //阻止冒泡
    //判断目录是否展开
    if($(this).parent().find("ul").is(':visible')){
        //展开时隐藏
        $(this).parent().find("ul").slideUp(500);
        $(this).find(".pic_list1_arrow").css("transform","rotate(0deg)");
    }
    else{
        //隐藏时展开
        $(this).parent().find("ul").slideDown(500);
        $(this).find(".pic_list1_arrow").css("transform","rotate(180deg)");
    }
});

//点击目录
$(".list2 li").bind("click",function(){
    //清除所有二级目录颜色
    $("#list_menu li").find("ul li").removeClass("selected_level2");
    //该点击二级目录颜色加深
    $(this).addClass('selected_level2');
    //清除所有一级目录颜色
    $("#list_menu li").siblings().find(".list1").removeClass("selected_level1");
    //该点击二级目录的一级目录颜色加深
    $(this).parent().parent().parent().find(".list1").addClass('selected_level1');
});