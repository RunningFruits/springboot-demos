var pageCurr;
var form;

$(function () {

    layui.use(['table'], function () {
        var table = layui.table;
        form = layui.form;

        tableIns = table.render({
            elem: '#dataList',
            url: '/sys/user/page',
            method: 'post', //默认：get请求
            cellMinWidth: 80,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'count', //数据总数的字段名称，默认：count
                dataName: 'rows' //数据列表的字段名称，默认：data
            },
            cols: [[
                 {field: 'id', hide: true}
                , {field: 'userId', title: 'userId', align: 'center'}
                , {field: 'userName', title: '用户名', align: 'center'}
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#optBar'}
            ]],
            done: function (res, curr, count) {
                console.log("====== res ========");
                console.log(res);

                console.log("====== curr ========");
                console.log(curr);

                pageCurr = curr;
            }
        });


        //监听工具条
        table.on('tool(dataTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                //删除
                delById(data, data.id);
            }
        });

        form.on('submit(exportExcel)', function (data) {

            var loading = layer.load(1, {shade: [0.3, '#fff']});
            var $ = layui.jquery;
            var excel = layui.excel;
            $.ajax({
                url: '/sys/user/exportExcel',
                dataType: 'json',
                data: {
                    field: JSON.stringify(data.field)
                },
                success: function (res) {
                    layer.close(loading);
                    layer.msg(res.msg);


                    // 1. 数组头部新增表头
                    res.data.unshift({
                        openid: 'openid',
                        parentName: '家长名称',
                        parentPhone: '家长手机',
                        childName: '孩子名称',
                        score: '分数',
                        createTime: '创建时间'
                    });

                    // 3. 执行导出函数，系统会弹出弹框
                    excel.exportExcel({
                        sheet1: res.data
                    }, '家长回答分数报表.xlsx', 'xlsx');
                },
                error: function (res) {
                    layer.close(loading);
                    layer.msg(res.msg);
                }
            });
        });

    });

});


//重新加载table
function load(obj) {

    tableIns.reload({
        where: obj.f
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

//删除
function delById(obj, id) {

    if (null != id) {
        layer.confirm('您确定要删除吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.post("/sys/user/delete", {"id": id}, function (data) {
                if (data.code == 1) {
                    layer.alert(data.msg, function () {
                        layer.closeAll();
                        load(obj);
                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function () {
            layer.closeAll();
        });
    }

}
