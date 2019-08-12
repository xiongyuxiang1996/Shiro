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

$("#pageNo").val(pageNo);
$(document).ready(function(){
    $("#page").paging({
        nowPage: Number(pageNo),    // 当前页码,默认为1
        pageNum: Number(pageCount), // 总页码
        buttonNum: 9,               //要展示的页码数量，默认为7，若小于5则为5
        callback: function(num){   //回调函数,num为当前页码
            console.log(num);
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
