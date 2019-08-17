var tree_id = "treeList";
var zNodes = [];
var permissionList;

// 解析URL
var parameter = window.location.href.split("?")[1];
var parameterList = parameter.split("&");
var state  = decodeURIComponent(parameterList[0]);      //状态
console.log(parameterList);
$("button#" + state).removeClass("state_unchose").addClass('state_chose');

$.ajax({
    type: "post",
    url: "/permission/getPermissionList",
    async: false,
    data: {
        state: state
    },
    success: function(data) {
        console.log(data);
        permissionList = data.data;
        if(permissionList.length == 0){
            $("#treeList").css("display","none");
            var strhtml = '<div id="">暂无数据</div>';
            $("#permissiondata").append(strhtml);
        }
        else{
            for(var i = 0;i < permissionList.length;i ++){
                zNodes.push({id:permissionList[i].permissionId, pId:permissionList[i].parentId, name: permissionList[i].permissionName, open:true});
            }
        }
    },
    error: function (data) {
        console.log(data);
    }
});

// 点击角色状态
$(".state").click(function (e){
    // e.stopPropagation(); //阻止冒泡
    $(this).parent().find("button").removeClass("state_chose").addClass("state_unchose");
    $(this).removeClass("state_unchose");
    $(this).addClass('state_chose');
    state = $(this).attr("id");
    refresh();
});

// 树型插件
$(document).ready(function(){
    $.fn.zTree.init($("#" + tree_id), setting, zNodes);
});

var setting = {
    view: {
        selectedMulti: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    edit: {
        enable: true,
        drag: false,
        showRenameBtn: false
    },
    callback: {
        onClick: onClick,
        onRemove: onRemove
    }
};

function onClick(event, treeId, treeNode) {
    console.log("单击");
    console.log(treeNode.id);
}
function onRemove(event, treeId, treeNode) {
    $.ajax({
        type: "post",
        url: "/permission/deletePermission",
        async: false,
        data: {
            permissionId: treeNode.id
        },
        success: function(data) {
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}

// 刷新页面
var refresh = function () {
    window.location.href = "?" + state;
};