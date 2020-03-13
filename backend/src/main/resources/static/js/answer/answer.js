var pageCurr;
var form;

$(function () {

    layui.use('table', function () {
        var table = layui.table;
        form = layui.form;

        tableIns = table.render({
            elem: '#dataList',
            url: '/answer/getList',
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
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[
                {type: 'numbers'}
                , {field: 'id', hide: true}
                , {field: 'openid', title: 'openid', align: 'center'}
                , {field: 'parentName', title: '家长名称', align: 'center'}
                , {field: 'parentPhone', title: '家长手机', align: 'center'}
                , {field: 'childName', title: '孩子名称', align: 'center'}
                , {field: 'score', title: '分数', align: 'center'}
                , {
                    field: 'createTime',
                    title: '创建时间',
                    templet: "<div>{{layui.util.toDateString(d.createTime, 'yyyy年MM月dd日 HH:mm:ss')}}</div>",
                    align: 'center'
                }
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#optBar'}
            ]],
            done: function (res, curr, count) {
                $("[data-field='roleStatus']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("有效")
                    } else if ($(this).text() == '0') {
                        $(this).text("失效")
                    }
                });
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
                url: '/answer/exportExcel',
                dataType: 'json',
                data: {
                    field: JSON.stringify(data.field)
                },
                success: function (res) {

                    debugger
                    layer.close(loading);
                    layer.msg(res.msg);
                    // 假如返回的 res.data 是需要导出的列表数据
                    console.log(res.data);//

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
                    debugger
                    layer.close(loading);
                    layer.msg(res.msg);
                }
            });
        });

    });

});


//重新加载table
function load(obj) {
    console.log("===== load ======");
    console.log(obj);

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
            $.post("/answer/delById", {"id": id}, function (data) {
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
