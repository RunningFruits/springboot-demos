/**
 * 
 */

requirejs.config({
	baseUrl:contextPath,
    paths: {
        jquery: 'wxpage/thirdplugin/jquery.min',
        weui: 'wxpage/thirdplugin/jquery-weui.min',
        domReady: 'wxpage/thirdplugin/domReady',
        csrf: 'wxpage/thirdplugin/csrf',
        constant: 'wxpage/thirdplugin/messageUtil',
        until:'wxpage/thirdplugin/until',
        async:'wxpage/thirdplugin/async',
		jweixin: 'wxpage/thirdplugin/jweixin-1.2.0',
		BMap: 'http://api.map.baidu.com/api?v=2.0&ak=FGPuiHQ9DBEluA5xrqrtuQobSMoZmocp',
		LoadPageFront:'../wxpage/thirdplugin/LoadPageFront'
	},
	shim:{
		 csrf:{
			 deps:['jquery'],
			 exports:"Csrf"
		 },
		 BMap: {
		     deps: ['jquery'],
		     exports: 'BMap'
		 },
		 weui: {
		     deps: ['jquery'],
		     exports: 'jquery-weui.min'
		 },
		 LoadPageFront: {
		     deps: ['jquery','weui'],
		     exports: 'LoadPageFront'
		 }
		 
	},
	waitSeconds:0,
	urlArgs: "v=" +  (new Date()).getTime()
});
