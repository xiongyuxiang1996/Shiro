var permissionList;       //用户列表数据
var length;         //数据总条数
var start;          //从第几条数据开始显示
var end;            //到第几条数据结束显示
var pageSize = 50;  //每页显示数据数目
var pageCount;      //总页数

// 解析URL
var parameter = window.location.href.split("?")[1];
var parameterList = parameter.split("&");
var pageNo  = decodeURIComponent(parameterList[0]);     //页码
var state  = decodeURIComponent(parameterList[1]);      //状态
console.log(parameterList);
$("#pageNo").val(pageNo);
$("button#" + state).removeClass("state_unchose").addClass('state_chose');

// 分页插件
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
    url: "/permission/getPermissionList",
    async: false,
    data: {
        state: state
    },
    success: function(data) {
        console.log(data);
        getPermissionList(data);
    },
    error: function (data) {
        console.log(data);
    }
});
function getPermissionList(data) {
    permissionList = data.data;
    length = permissionList.length;
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
        $("#permissionlist").append(strhtml);
    }
    else{
        insertHtml(start,end);
    }
}
function insertHtml(start,end) {
    for (var i = start; i < end; i++) {
        var permissioninfo = permissionList[i];
        var no = i + 1;
        var strhtml = '<tr id="' + i + '" arrId="' + i + '">';
        strhtml = strhtml + '<td>' + no + '</td>';
        strhtml = strhtml + '<td>' + permissioninfo.permissionName + '</td>';
        strhtml = strhtml + '<td>' + permissioninfo.url + '</td>';
        strhtml = strhtml + '<td>' + permissioninfo.resourceType + '</td>';
        if (permissioninfo.available == 0) {
            strhtml = strhtml + '<td>已启用</td>';
        }
        else if (permissioninfo.state == 1) {
            strhtml = strhtml + '<td>已冻结</td>';
        }
        strhtml = strhtml + '<td>操作</td>';
        $("#permissionlist").append(strhtml);
    }
}

// 点击角色状态
$(".state").click(function (e){
    // e.stopPropagation(); //阻止冒泡
    $(this).parent().find("button").removeClass("state_chose").addClass("state_unchose");
    $(this).removeClass("state_unchose");
    $(this).addClass('state_chose');
    state = $(this).attr("id");
    refresh();
});

// 页码跳转输入框失去焦点后跳转页面
$("#pageNo").blur( function(){
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
    window.location.href = "?" + pageNo + "&" + state;
};