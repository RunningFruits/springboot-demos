<!-- 导航栏 -->
$('.arrow-left').on('click', function () {
    window.location.href = contextPath + '/wechat/scene/detail';
});
$('.buyNotice').on('click', function () {
    window.location.href = contextPath + '/wechat/scene/buyNotice';
});

var data = {
    tickets: [],
    /* 游玩日期 */
    tourDates: [],
    fuClicked: false,
    reachTime: ''
};
$(document).ready(onLoad);

<!-- 加减计数 -->
var MAX = 99, MIN = 1;
$('.weui-count__decrease').click(function (e) {
    var $input = $(e.currentTarget).parent().find('.weui-count__number');
    var number = parseInt($input.val() || "0") - 1;
    if (number < MIN) number = MIN;
    $input.val(number);

    // console.log("===== weui-count__decrease ====");
    $("#allprice").html((options.ticketPrice * number).toFixed(2));
});
$('.weui-count__increase').click(function (e) {
    var $input = $(e.currentTarget).parent().find('.weui-count__number');
    var number = parseInt($input.val() || "0") + 1;
    if (number > MAX) number = MAX;
    $input.val(number);

    // console.log("===== weui-count__increase ====");
    $("#allprice").html((options.ticketPrice * number).toFixed(2));
});


function onLoad() {
    data.tourDates = getDates(7);

    buildTourDateBox(data.tourDates);

    $("#ticket-title").html(options.ticketName);
    $("#ticketPrice").html(options.ticketPrice);
    $("#userNameInput").val(options.userName);
    $("#idCardInput").val(options.idCard);
    $("#phoneInput").val(options.phone);
    $("#allprice").html(options.ticketPrice);
    //手机验证码
    // $("#verifyCodeInput").val('');
    console.log($("#verifyCodeInput").val());
    //是否已阅读并同意
    // console.log($("#weui-agree").is(":checked"));
    $("#weui-agree").prop("checked", true);

}

function buildTourDateBox(tourDates) {
    var dateBox = function (i, str) {
        return '<div class="tour-date-box-child' + (i == 0 ? '' : '-white') + '">' +
            '      <div class="tour-date-box-child-content">' +
            '          ' + str + '' +
            '      </div>' +
            '</div>';
    };

    $("#tour-date-box").empty();
    for (var i in tourDates) {
        $("#tour-date-box").append(dateBox(i, tourDates[i].week + tourDates[i].year + tourDates[i].month + tourDates[i].day));
    }

    $("#tour-date-box").on("click", ".tour-date-box-child-white", function () {
        $("#tour-date-box").find('.tour-date-box-child').addClass('tour-date-box-child-white').removeClass('tour-date-box-child');
        $(this).addClass('tour-date-box-child').removeClass('tour-date-box-child-white');
        var index = $(this).index();
        console.log(index);
        console.log(data.tourDates[index]);
    });
}

function getDates(days) {
    var todate = getCurrentMonthFirst();
    var dateArry = [];
    for (var i = 0; i < days; i++) {
        var dateObj = dateLater(todate, i);
        dateArry.push(dateObj)
    }
    return dateArry;
}

function dateLater(dates, later) {
    var dateObj = {};
    var show_day = new Array('周日', '周一', '周二', '周三', '周四', '周五', '周六');
    var date = new Date(dates);
    date.setDate(date.getDate() + later);
    var day = date.getDay();
    dateObj.year = date.getFullYear();
    dateObj.month = ((date.getMonth() + 1) < 10 ? ("0" + (date.getMonth() + 1)) : date.getMonth());
    dateObj.day = (date.getDate() < 10 ? ("0" + date.getDate()) : date.getDate());
    dateObj.week = show_day[day];
    return dateObj;
}

function getCurrentMonthFirst() {
    var date = new Date();
    var todate = date.getFullYear() + "-" + ((date.getMonth() + 1) < 10 ? ("0" + (date.getMonth() + 1)) : date.getMonth()) + "-" + (date.getDate() < 10 ? ("0" + date.getDate()) : date.getDate());
    return todate;
}

function isBlank(str) {
    if (str == null || str == undefined || str == '') {
        return true;
    }
    str = str.replace(/(^\s*)|(\s*$)/g, "");
    if (str.length == 0) {
        return true;
    }
    return false;
}

function isPhone(str) {
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(str)) {
        return false;
    } else {
        return true;
    }
}

//手机号输入框失去焦点时
$('#userNameInput').on('blur', function () {
    var userName = $(this).val();
    if (isBlank(userName)) {
        $.alert({
            title: '用户名不能为空',
            text: '',
            onOK: function () {
            }
        });
        return;
    }
});

$('#weui-agree').on('click', function () {
    console.log($(this).is(":checked"));
});

//身份证号输入框失去焦点时
$('#idCardInput').on('blur', function () {
    var that = this;
    var idCardValue = $(this).val();
    // console.log("========= 身份证号 ===========");
    // console.log(idCardValue);
    if (!isBlank(idCardValue) && !isIdCard(idCardValue)) {
        $.alert({
            title: '身份证号格式错误',
            text: '',
            onOK: function () {
                $(that).val('');
            }
        });
        return;
    }
});

//手机号输入框失去焦点时
$('#phoneInput').on('blur', function () {
    var that = this;
    var phone = $(this).val();
    if (!isBlank(phone) && !isPhone(phone)) {
        $.alert({
            title: '手机号格式错误',
            text: '',
            onOK: function () {
                $(that).val('');
            }
        });
        return;
    }
});

//验证码输入框失去焦点时
$('#verifyCodeInput').on('blur', function () {
    var that = this;
    var verifyCode = $(this).val();
    if (isBlank(verifyCode)) {
        $.alert({
            title: '未填写验证码',
            text: '',
            onOK: function () {
                $(that).val('');
            }
        });
        return;
    }
});

//发送短信二维码
$("#sendQrCode").on('click', function () {
    console.log("====== 发送短信验证码 ======");
    var phone = $('#phoneInput').val();
    if (!isBlank(phone) && isPhone(phone)) {
        $.ajax({
            url: contextPath + "/sms/sendQrCode",
            dataType: "json",
            type: "post",
            data: {
                phone: phone
            },
            complete: function (XHR, TS) {
                var returnObj = eval('(' + XHR.responseText + ')');
                if (returnObj.code != 200) {
                } else {

                }
            }
        });
    }
});

//付款
$('#fu').on('click', function () {

    var dateItem = getTourDateItem();
    var dateString = dateItem.year + '-' + dateItem.month + '-' + dateItem.day + ' ' + dateItem.week;

    var userName = $("#userNameInput").val();
    var idCard = $("#idCardInput").val();
    var phone = $("#phoneInput").val();
    var ticketNum = $("#chooseTicketNum").val();

    var keyStyle = "display:inline-block;width:5em;font-size:16px;font-family:PingFang-SC-Bold;font-weight:bold;color:rgba(51,51,51,1);";
    var valueStyle = "display:inline-block;font-size:16px;font-family:PingFang-SC-Bold;font-weight:bold;color:rgba(102,102,102,1);";

    var titleHeadStyle = "font-size:17px;font-family:PingFang-SC-Bold;font-weight:bold;color:rgba(51,51,51,1);";
    var titleStyle = "display:inline-block;font-size:12px;font-family:PingFang-SC-Bold;font-weight:bold;color:rgba(102,102,102,1);";
    var title = '<div style="' + titleHeadStyle + '">' + options.ticketName + ' <span style="' + titleStyle + '">' + dateString + '</span></div>';
    var content =
        '<div style="display:inline-block;text-align:left">' +
        '<div><span style="' + keyStyle + '">姓名</span><span style="' + valueStyle + '">' + userName + '</span></div>' +
        '<div><span style="' + keyStyle + '">身份证</span><span style="' + valueStyle + '">' + idCard + '</span></div>' +
        '<div><span style="' + keyStyle + '">手机号</span><span style="' + valueStyle + '">' + phone + '</span></div>' +
        '<div><span style="' + keyStyle + '">数量</span><span style="' + valueStyle + '">' + ticketNum + '张</span></div>' +
        "</div>";
    $.modal({
        title: title,
        text: content,
        buttons: [
            {
                text: "支付", onClick: function () {

                    var params = getPayParams();

                    //检查表单
                    var isPass = checkForm();
                    if (!isPass) {
                        return;
                    }
                    //防多次点击
                    if (data.fuClicked) {
                        return;
                    } else {
                        data.fuClicked = true
                    }

                    // if (options.activityPrice && options.activityType == 1) {
                    //     params.activityType = 1;
                    // } else {
                    //     params.activityType = 2;
                    // }
                    params.couponId = [0];
                    params.couponType = 3;

                    console.log("====== 购票参数 =======");
                    console.log(params);

                    if (options.isFreeTicket) {
                        freeRequest(params);
                    } else {
                        payRequest(params);
                    }
                }
            },
            {
                text: "取消", className: "default", onClick: function () {

                }
            }
        ]
    });

});


//构建并返回支付参数
function getPayParams() {
    var useTime = getTourDate();
    //构建付款参数 或 申请参数
    var params = {
        openId: options.openid,
        // totalFee: parseFloat($("#allprice").html()),
        userId: options.userId,
        idCard: $("#idCardInput").val(),
        nickName: $("#userNameInput").val(),
        companyId: options.companyId,
        phone: $("#phoneInput").val(),

        useTime: new Date(useTime).getTime(),

        ticketId: options.ticketId,
        ticketNum: $('#chooseTicketNum').val(),

        type: options.type,
        formId: ""
    };
    return params;
}

//免费票发起申请
function freeRequest(params) {
    // console.log("======= 免费票参数 ========");
    // console.log(params);
    $.ajax({
        type: "post",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            "platform": 1
        },
        async: false,
        data: JSON.stringify(params),
        dataType: "json",
        url: domain + "/order/freeTicket",
        success: function (res) {
            var code = parseInt(res.data.code);
            if (code == 200) {
                $.alert({
                    title: '预订成功',
                    text: '',
                    onOK: function () {
                    }
                });
            } else {
                $.alert({
                    title: '预定失败',
                    text: '',
                    onOK: function () {

                    }
                });
            }
        },
        error: function (msg) {
        }
    });
}


//非免费票发起支付
function payRequest(params) {
    console.log("===== 非免费票发起支付 ======");
    $.ajax({
        type: "post",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            "platform": 1
        },
        async: false,
        data: JSON.stringify(params),
        dataType: "json",
        url: domain + "/order",
        success: function (res) {
            console.log("======== 发起创建订单 ========");
            console.log(res);
            requestPayment(res);
        },
        error: function (msg) {
            console.log("======== 报错信息 ========");
            console.log(msg.responseText)
        }
    });
}

function requestPayment(res) {
    var code = parseInt(res.data.code);
    if (code != 200) {
        console.log(res.data.msg);
        return;
    }
    var payargs = res.data.data;
    onBridgeReady('wxc84679c3bcc8d70f', payargs.timeStamp, payargs.nonceStr, payargs.package, payargs.paySign);
}


//检查 时间、票、身份证、姓名、手机号
function checkForm() {
    var inName = $("#userNameInput").val();
    inName = inName ? inName.replace(/\s+/g, '') : '';
    //检查姓名
    if (!isBlank(inName)) {
        $("#userNameInput").val(inName);
    } else {
        showToast('姓名不能为空');
        return false;
    }

    var phone = $("#phoneInput").val();
    if (!isPhone(phone)) {
        showToast('手机号格式错误');
        return false;
    }

    var chooseTicketNum = parseInt($('#chooseTicketNum').val());

    //防止未选择就提交
    if (chooseTicketNum == 0) {
        showToast('未选择票');
        return false;
    }

    //如果cardStatus为1
    if (options.cardStatus == 1) {
        var idCard = $("#idCardInput").val();
        if (isBlank(idCard)) {
            showToast("未填写身份证");
            return false;
        }
        if (!isIdCard(idCard)) {
            showToast("身份证号格式错误");
            $("#idCardInput").val('');
            return false;
        }
    }

    var useTime = getTourDate();
    if (!useTime) {
        showToast('未选择日期');
        return false;
    }

    var verifyCode = $("#verifyCodeInput").val();
    if (isBlank(verifyCode)) {
        showToast('未填写验证码');
        return false;
    }

    var isAgree = $("#weui-agree").is(":checked");
    if (!isAgree) {
        showToast('未同意购买协议');
        return false;
    }
    return true;
}

function isIdCard(code) {
    var regIdNo = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (!code || !regIdNo.test(code)) {
        return false;
    }
    return true;
}

function showToast(content) {
    $.alert({
        title: content,
        text: '',
        onOK: function () {

        }
    });
}


// //获取当前选中的游玩日期格式 yyyy-MM-dd
function getTourDate() {
    var index = $("#tour-date-box").find(".tour-date-box-child").index();
    var tourDate = data.tourDates[index];
    var date = tourDate.year + "-" + tourDate.month + "-" + tourDate.day;

    // console.log(date);
    return date;
}

function getTourDateItem() {
    var index = $("#tour-date-box").find(".tour-date-box-child").index();
    return data.tourDates[index];
}

//
// //点击微信支付
// function payByWeChat(outTradeNo) {
//     $.ajax({
//         data: {outTradeNo: outTradeNo},
//         dataType: "json",
//         type: "post",
//         async: false,
//         url: contextPath + "/payWeiXin/doWeiXinRequest",
//         success: function (msg) {
//             if (msg.result.msg == "成功") {
//                 var content = msg.data;
//                 var appId = content.appId;
//                 var timeStamp = content.timeStamp;
//                 var nonceStr = content.nonceStr;
//                 var packages = content.packages;
//                 var paySign = content.sign;
//                 pay(appId, timeStamp, nonceStr, packages, paySign);
//             }
//         },
//         error: function (msg) {
//
//         }
//     });
// }

//h5唤起微信支付
function onBridgeReady(appId, timeStamp, nonceStr, packages, paySign) {
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId": appId,     //公众号名称，由商户传入
            "timeStamp": timeStamp,         //时间戳，自1970年以来的秒数
            "nonceStr": nonceStr, //随机串
            "package": packages,
            "signType": "MD5",         //微信签名方式：
            "paySign": paySign //微信签名
        },
        function (res) {
            console.log(res.err_code + res.err_desc);
            console.log(res.err_msg);
            if (res.err_msg == "get_brand_wcpay_request:ok") {
                // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回 ok
                // 但并不保证它绝对可靠。
                $.toast("支持成功");
                window.location.href = contextPath + "/wechat/list";
            } else if (res.err_msg === 'get_brand_wcpay_request:cancel') {
                $.toast("取消支付");
            }
        }
    );
}

function pay(appId, timeStamp, nonceStr, packages, paySign) {
    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    } else {
        onBridgeReady(appId, timeStamp, nonceStr, packages, paySign);
    }
}
