<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<!--对于IE8以下的浏览器是不识别的,指定网页的兼容性模式设置-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--360 浏览器就会在读取到这个标签后，立即切换对应的极速核-->
	<meta name="renderer" content="webkit">
	<meta name="google-site-verification" content="oQXrGa_mTgxg7joO0himE0QuFeqOVmm-SDC1H2dzT4c">
	<meta name="baidu-site-verification" content="wibJopuIuI" />
	<!--禁止将页面数字识别为电话号码 及 告诉设备不识别邮箱，点击之后不自动发送，禁止作为邮箱地址！-->
	<meta name="format-detection" content="telephone=no,email=no" />
	<!--当网站添加到主屏幕快速启动方式，可隐藏地址栏，仅针对ios的safari，它表示：允许全屏模式浏览-->
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<!--iphone的私有标签，它指定的iphone中safari顶端的状态条的样式，默认值为default（白色），可以定为black（黑色）和black-translucent（灰色半透明）。
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />-->
	<meta name="keywords" content="">
	<meta name="description" content="">
	<!--计算rem自适应方案的js -->
	<script>
		(function(){var l=window.document;var b=document.documentElement;var g=window;var m=0;var d=0;var e;window.remFlexible=window.remFlexible||{};var f=window.remFlexible;function j(){var n=b.clientWidth;b.style.fontSize=n/10.8+"px"}var i=l.querySelector('meta[name="viewport"]');if(i){i.parentNode.removeChild(i)}var h=g.navigator.appVersion.match(/android/gi);var c=g.navigator.appVersion.match(/iphone/gi);var k=g.devicePixelRatio;if(c){if(k>=3&&(!m||m>=3)){m=3}else{if(k>=2&&(!m||m>=2)){m=2}else{m=1}}}else{m=1}d=1/m;i=document.createElement("meta");i.setAttribute("name","viewport");i.setAttribute("content","initial-scale="+d+", maximum-scale="+d+", minimum-scale="+d+", user-scalable=no");if(b.firstElementChild){b.firstElementChild.appendChild(i)}else{var a=l.createElement("div");a.appendChild(i);l.write(a.innerHTML)}g.addEventListener("resize",function(){clearTimeout(e);e=setTimeout(j,300)},false);g.addEventListener("pageshow",function(n){if(n.persisted){clearTimeout(e);e=setTimeout(j,300)}},false);if(l.readyState==="complete"){j()}else{l.addEventListener("DOMContentLoaded",function(n){j()},false)}j();b.setAttribute("data-dpr",m);f.dpr=m;f.px2rem=function(n){return n/100};f.rem2px=function(n){return n*100}})();
	</script>  
	<!--加载reset css文件-->
	<link rel="stylesheet" type="text/css" href="../css/reset.css"/>
	<script type="text/javascript" src="../lib/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../lib/vue.js"></script>
	<!--传播途径引入文件 结束-->
	<!--传播途径引入文件 结束-->
	<!--加载本页css文件-->
	<link rel="stylesheet" type="text/css" href="../css/snapshot.css" />
	<title>${title}</title>
	<!--传播途径引入文件 结束-->
	<style type="text/css">
		
	</style>
</head>
<body>
	<!--信息详情-->
	<div class="info_main" id="infodetail">
		<div class="infodetail_top">
			<div class="info_sources clearfix">
				<div class="info_sources_icon border_box">
					<template  v-if="sourceType == '博客'">
						<img src="../images/info_blog.png" title="博客"/>
					</template>	
					<template  v-if="sourceType == '评论'">
						<img src="../images/info_comment.png"  title="评论"/>
					</template>	
					<template  v-if="sourceType == '报刊'">
						<img src="../images/info_flat_media.png"  title="报刊"/>
					</template>	
					<template  v-if="sourceType == '论坛'">
						<img src="../images/info_forum.png"  title="论坛"/>
					</template>	
					<template  v-if="sourceType == '长微博'">
						<img src="../images/info_long_micro_blog.png"  title="长微博"/>
					</template>	
					<template  v-if="sourceType == '微博'">
						<img src="../images/info_micro_blog.png"  title="微博"/>
					</template>	
					<template  v-if="sourceType == '网媒'">
						<img src="../images/info_news.png"  title="网媒"/>
					</template>	
					<template  v-if="sourceType == '其他'">
						<img src="../images/info_others.png"  title="其他"/>
					</template>	
					<template  v-if="sourceType == '视频'">
						<img src="../images/info_video.png"  title="视频"/>
					</template>	
					<template  v-if="sourceType == '微信'">
						<img src="../images/info_wechat.png"  title="微信"/>
					</template>	
					<template  v-if="sourceType == 'APP'">
						<img src="../images/info_wechat.png"  title="APP"/>
					</template>	
				</div>
				<div class="info_sources_name">
					<p>{{sourceType}}</p>
					<p>{{time}}</p>
				</div>
				<a :href="url" class="ori_url">查看原文</a>
			</div>
			<div class="info_title_box clearfix">
				<div class="info_title">
					<template  v-if="titmeAttribute == '正'">
						<i the-id="ori_wary" class="zheng" >{{titmeAttribute}}</i>
					</template>	
					<template  v-if="titmeAttribute == '负'">
						<i the-id="ori_wary" class="fu" >{{titmeAttribute}}</i>
					</template>	
					<template  v-if="titmeAttribute == '中'">
						<i the-id="ori_wary" class="zhong" >{{titmeAttribute}}</i>
					</template>	
					<span class="broad_title" the-id="broad_title">{{title}}</span>
				</div>
			</div>
			<div class="commoninfo_box clearfix">
				<span class="common_gray fl">转发{{forwardNum}}</span>
			</div>
			<div class="commoninfo_box next_two">
				<span class="common_gray">首发：{{firstStart}}</span>
			</div>
			<div class="commoninfo_box next_two">
				<span class="common_gray">关键词：</span>
				<template  v-for="item in keyWords">
					<span class="common_blue">{{item}}</span>
				</template>	
			</div>
			<div class="info_paragraph_box">
				<p class="info_paragraph" the-id="broad_content" v-html='content'></p>
			</div>
			<p class="info_tips">特别声明：请以原文为准。传播此页所致后果，本站概不负责。</p>
		</div>
	</div>	
	<#if appName?? && picInfo?? && fbAddress??>
    	<div class="download_box border_box fix_btom ">
    		<div class="logo fl"><img src="${logoPath}"  title="${appName}"/>${appName}</div>
    		<a class="download border_box fr"  id="download" onClick="downloadApp();">下载</a>
    	</div>
    </#if>
</body>
<script>
	
    var evenWrap = new Vue({
        el:'#infodetail',
        data:{
			sourceType: "${sourceType}" ,
			time: "${time}" , 
			titmeAttribute: "${titmeAttribute}" ,  
			title: "${title}" ,
			forwardNum: "${forwardNum}" , 
			firstStart: "${firstStart}" , 
			keyWords: ${keyWords} , 
            url: '${url}', 
            content: '${content}' 
        },	
		methods:{
        },
        mounted:function () {
        }
    });
    
	var userAgent = navigator.userAgent || '';
	var isAndroid = userAgent.indexOf('Android') > -1 || userAgent.indexOf('Adr') > -1;
	var isIOS = !!userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
	
	<#if appName?? && picInfo?? && fbAddress??>
	function downloadApp(){
		var download = document.getElementById("download");
		if(isIOS){
	       download.href="${fbAddress}";
	    }
	    if(isAndroid){
	        download.href="${fbAddress}";
	    }
	}
	</#if>

</script>
</html>