$('.arrow-left').on('click', function () {
    window.location.href = contextPath + '/wechat/wxpage/list';
});

$("#sceneIntroduce").on('click', function () {
    var companyId = options.companyId;
    window.location.href = contextPath + '/wechat/wxpage/introduce?companyId='+companyId;
});

$("#sceneBuyNotice").on('click', function () {
    var companyId = options.companyId;
    window.location.href = contextPath + '/wechat/wxpage/buyNotice?companyId='+companyId;
});
