/*! -v1.0 项目描述  By Linyer */
layui.config({base:"../../src/js/lib/"}).extend({zyupload:"zyupload"}),layui.use(["jquery","form","upload","zyupload"],function(){var o=layui.jquery;layui.form();layui.upload({url:"",success:function(o){console.log(o)}}),o(function(){o("#zyupload").zyUpload({itemWidth:"120px",itemHeight:"150px",url:"",fileType:["jpg","png"],fileSize:512e5,multiple:!0,dragDrop:!0,tailor:!1,del:!0,finishDel:!0,onSelect:function(o,n){console.info("当前选择了以下文件："),console.info(o)},onDelete:function(o,n){console.info("当前删除了此文件："),console.info(o.name)},onSuccess:function(o,n){console.info("此文件上传成功："),console.info(o.name),console.info("此文件上传到服务器地址："),console.info(n)},onFailure:function(o,n){console.info("此文件上传失败："),console.info(o.name)},onComplete:function(o){console.info("文件上传完成"),console.info(o)}})})});