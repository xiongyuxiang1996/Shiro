var userList;       //用户列表数据
var length;         //数据总条数
var start;          //从第几条数据开始显示
var end;            //到第几条数据结束显示
var pageSize = 50;  //每页显示数据数目
var pageCount;      //总页数

// 解析URL
var parameter = window.location.href.split("?")[1];
var parameterList = parameter.split("&");
var pageNo  = decodeURIComponent(parameterList[0]);     //页码
var state = decodeURIComponent(parameterList[1]);       //状态
var searchword = decodeURIComponent(parameterList[2]);  //查询词
var privilege = decodeURIComponent(parameterList[3]);   //权限
var timeFrom = decodeURIComponent(parameterList[4]);    //开始时间
var timeTo = decodeURIComponent(parameterList[5]);      //结束时间
console.log(parameterList);
$("#pageNo").val(pageNo);
$("#search_input").val(searchword);
$("#privilege").val(privilege);
$("#timeFrom").val(timeFrom);
$("#timeTo").val(timeTo);
$("button#"+state).removeClass("state_unchose").addClass('state_chose');

$(document).ready(function(){
    $("#page").paging({
        nowPage: Number(pageNo),    // 当前页码,默认为1
        pageNum: Number(pageCount), // 总页码
        buttonNum: 9,               //要展示的页码数量，默认为7，若小于5则为5
        callback: function(num){   //回调函数,num为当前页码
            pageNo = num;
            refresh();
        }
    });
});

$.ajax({
    type: "post",
    url: "/user/getUserList",
    async: false,
    data: {},
    success: function(data) {
        console.log(data);
        getUserList(data);
    },
    error: function (data) {
        console.log(data);
    }
});
function getUserList(data) {
    userList = data.data;
    length = userList.length;
    start = (pageNo - 1) * pageSize;
    end = pageNo * pageSize;
    pageCount = Math.ceil(length / pageSize);
    if(end >= length){
        end = length;
    }
    $("#totalcount").html("当前共" + length + "条记录");
    if(length == 0){
        var strhtml = '<tr>';
        strhtml = strhtml + '<td colspan="9">暂无数据</td>';
        $("#userlist").append(strhtml);
    }
    else{
        insertHtml(start,end);
    }
}
function insertHtml(start,end) {
    for (var i = start; i < end; i++) {
        var userinfo = userList[i];
        var no = i + 1;
        var strhtml = '<tr id="' + i + '" arrId="' + i + '">';
        strhtml = strhtml + '<td>' + no + '</td>';
        strhtml = strhtml + '<td>' + userinfo.username + '</td>';
        strhtml = strhtml + '<td>' + userinfo.privilege + '</td>';
        if (userinfo.state == 0) {
            strhtml = strhtml + '<td>未认证</td>';
        }
        else if (userinfo.state == 1) {
            strhtml = strhtml + '<td>已启用</td>';
        }
        else if (userinfo.state == 2) {
            strhtml = strhtml + '<td>已锁定</td>';
        }
        strhtml = strhtml + '<td>' + userinfo.createTime + '</td>';
        strhtml = strhtml + '<td>操作</td>';
        $("#userlist").append(strhtml);
    }
}

// 点击用户状态
$(".state").click(function (e){
    // e.stopPropagation(); //阻止冒泡
    $(this).parent().find("button").removeClass("state_chose").addClass("state_unchose");
    $(this).removeClass("state_unchose");
    $(this).addClass('state_chose');
    state = $(this).attr("id");
    refresh();
    console.log(state)
});

// 点击搜索
$("#search_button").click(function (){
    searchword = $("#search_input").val();
    refresh();
    console.log(searchword)
});

// 页码跳转输入框失去焦点后跳转页面
$("#pageNo").blur( function(){
    // console.log(pageNo);
    // 页码发生改变
    if(pageNo != $(this).val()){
        // 页码小于最小页码
        if($(this).val() < 1){
            pageNo = 1;
        }
        // 页码大于最大页码
        else if($(this).val() > pageCount){
            pageNo = pageCount;
        }
        else{
            pageNo = $(this).val();
        }
        refresh();
    }
});

// 刷新页面
var refresh = function () {
    var privilege = $("#privilege").val();
    var timeFrom = $("#timeFrom").val();
    var timeTo = $("#timeTo").val();
    window.location.href = "?" + pageNo + "&" + state + "&" + searchword + "&" + privilege + "&" + timeFrom + "&" + timeTo;
};