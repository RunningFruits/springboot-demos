
jQuery(function ($) {
    $.ajax({
        type: 'post',
        url: '/tour/company/getCityList',
        data: {
            'limit': 10,
            'page': 1,
            'city': '乌兰察布',
            'search': null
        },
        success: function (res) {
            console.log(res)
            $.each(res, function (i, obj) {
                var content = '<div>' +
                    ' <div class="singo">\n' +
                    '<input type="hidden" class="companyId" value="' + obj.companyId + '">' +
                    '        <div class="left">\n' +
                    '            <div style="position: absolute;z-extra: 2" >荐</div>\n' +
                    '            <div><img src="' + obj.photo + '"></div>\n' +
                    '        </div> ' +
                    '<div class="right">' +
                    '<div class="name">' + obj.name + '</div>' +
                    '<div class="remark">' + obj.remark + '</div>' +
                    '            <div class="monkey"><span>￥</span> <span class="cost">' + obj.cost + '</span></div>\n' +
                    '            <div style="display: flex;width: 100%;margin-top: 10px;justify-content: flex-start" >' +
                    '                <div  class="comment"">评分：' + obj.score + " " + "评论：" + obj.commentNum + '+</div>\n' +
                    '                <div class="distance1">9 km</div>' +
                    '            </div>\n' +
                    '        </div>' +
                    '        </div>' +
                    '<div class="link-top"></div>' +
                    '        </div>';
                $(".big").append(content)
            });
        }
    });
    $.ajax({
        type: 'get',
        url: '/tour/advertisement/getAdvertisementTop5',
        data: {
            'type': 1
        },
        success: function (res) {
            console.log(res)
            $.each(res.data, function (i, obj) {
                var content = '<div class="swiper-slide"><img\n' +
                    '                src="' + obj.picture + '"' +
                    '                alt=""></div>'
                $(".swiper-wrapper").append(content)
            });
            $(".swiper-container").append('<div class="swiper-pagination"></div>')
        }
    });

    var companyId;
    $("body").on("click", ".singo", function (e) {
        companyId = $(this).find(".companyId").val();
        window.location.href = contextPath + '/wxpage/wxpage/detail?companyId=' + companyId
    })
});
