$('.arrow-left').on('click', function () {
    window.location.href = contextPath + '/wxpage/wxpage/list';
});

$("#sceneIntroduce").on('click', function () {
    var companyId = options.companyId;
    window.location.href = contextPath + '/wxpage/wxpage/introduce?companyId='+companyId;
});

$("#sceneBuyNotice").on('click', function () {
    var companyId = options.companyId;
    window.location.href = contextPath + '/wxpage/wxpage/buyNotice?companyId='+companyId;
});
