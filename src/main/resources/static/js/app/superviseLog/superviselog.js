$(document).ready(function() {
    var head = [
        {
            label: 'ID',
            width: 100,
            sortable: 'default',
            name: 'id'
        }, {
            label: '用户名',
            width: 150,
            sortable: 'default',
            name: 'name'
        }, {
            label: '昵称',
            width: 150
        }, {
            label: '籍贯',
            width: 150
        }, {
            label: '毕业院校',
            minWidth: 200
        }, {
            label: '出生日期',
            width: 120
        }, {
            label: '是否审核',
            width: 100
        }
    ];
    $('select').select();
    $('.grid').Grid({
        thead: head,
        tbody: null,
        height: 400,
        checkbox: {
            single: true
        },
        operator: {
            type: "normal",
            width: 100
        },
        sortCallBack: function (name, index, type) {
            alert(name + "," + index + ',' + type);
        }
    });
    $('.grid').Grid('addLoading');
    $('.pagination').pagination(100, {
        callback: function (page) {
            alert(page);
        },
        display_msg: false
    });
    $('.search-box input[type=radio]').click(function (e) {
        if ($(this).prop('checked')) {
            if ($(this).attr('data-define') === 'define') {
                $('.define-input').show();
            } else {
                $('.define-input').hide();
            }
        }
    });
/*    $.ajax({
        url:'/comm/test1.php',
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:{
            name:'yang',age:25
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend:function(xhr){
            console.log(xhr);
            console.log('发送前');
        },
        success:function(data,textStatus,jqXHR){
            console.log(data);
            console.log(textStatus);
            console.log(jqXHR);
            $('.grid').Grid('setData', tbody, head);
        },
        error:function(xhr,textStatus){
            console.log('错误');
            console.log(xhr);
            console.log(textStatus)
        }
    });*/

    var oper = [
        {
            label: '编辑', onclick: function () {
            alert('编辑')
            }
        },
        {
        label: '删除', onclick: function () {
            alert('删除');
            }
        }
    ]
    var tbody = [
        ["201301", "admin", "熊猫王子", "江苏南京", "南京林业大学", "1982-10-18", "已审核", oper],
        ["201302", "uimaker", "小牛", "山东济南", "山东大学", "1989-10-18", "已审核", oper],
        ["201301", "admin", "熊猫王子", "江苏南京", "南京林业大学", "1982-10-18", "已审核", oper],
        ["201301", "admin", "熊猫王子", "江苏南京", "南京林业大学", "1982-10-18", "已审核", oper],
        ["201302", "uimaker", "小牛", "山东济南", "山东大学", "1989-10-18", "已审核", oper],
        ["201301", "admin", "熊猫王子", "江苏南京", "南京林业大学", "1982-10-18", "已审核", oper],
        ["201301", "admin", "熊猫王子", "江苏南京", "南京林业大学", "1982-10-18", "已审核", oper],
        ["201302", "uimaker", "小牛", "山东济南", "山东大学", "1989-10-18", "已审核", oper],
        ["201301", "admin", "熊猫王子", "江苏南京", "南京林业大学", "1982-10-18", "已审核", oper],
        ["201301", "admin", "熊猫王子", "江苏南京", "南京林业大学", "1982-10-18", "已审核", oper]]

    $('.grid').Grid('setData', tbody, head);
});