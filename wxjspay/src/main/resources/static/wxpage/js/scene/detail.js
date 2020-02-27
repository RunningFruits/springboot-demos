$('.arrow-left').on('click', function () {
    window.location.href = contextPath + '/wechat/scene/list';
});

$("#sceneIntroduce").on('click', function () {
    var companyId = options.companyId;
    window.location.href = contextPath + '/wechat/scene/introduce?companyId='+companyId;
});

$("#sceneBuyNotice").on('click', function () {
    var companyId = options.companyId;
    window.location.href = contextPath + '/wechat/scene/buyNotice?companyId='+companyId;
});
