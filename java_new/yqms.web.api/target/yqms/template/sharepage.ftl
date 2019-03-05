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
	<link rel="stylesheet" type="text/css" href="../css/eventdetail_new.css" />
	<title>${ktName}</title>
	<!--传播途径引入文件 结束-->
	<style type="text/css">
		[v-cloak]{
			display: none !important;
		}
		/*分享页面--编辑按钮隐藏*/
		.edit_icon {
			display: none !important;
		}
		.event_top_edit {
			display: none !important;
		}
	</style>
</head>
<body>
<div class="main_page" id="wrap" v-cloak>
	<!--导航-->
	<div class="event_navs" the-id="event_navs">
		<ul>
			<li v-for="(item,index) in eventList" @click="tab(item,index)" :class="{'act':number==index}">{{item.name}}</li>
		</ul>
	</div>
	<div class="event_navs_button" @click="evenav()"></div>
	<!--头部-->
	<div class="event_top_box border_box">
		<!--<div class="choice_time_box fl border_box">-->
		<!--<input type="hidden" value="" beginTime="<{$beginTime}>" endTime="<{$endTime}>" eventId="<{$eventId}>" sharerid="<{$sharerid}>" username="<{$username}>">-->
		<!--<div class="choice_time">-->
		<!--<input type="text" name="starttime" readonly id="selectstart" value="<{$beginTime|strtotime|date='Y-m-d H:i:s',###}>"  the-id='startTime' the-parent='starttimep' onfocus="setudSelectStart('yyyy-MM-dd HH:mm:ss')"/>-->
		<!--<span>-</span>-->
		<!--<input type="text" name="closetime" readonly value="<{$endTime|strtotime|date='Y-m-d H:i:s',###}>" id="selectend"  the-id='endTime' the-parent='endtimep' onfocus="setudSelectEnd('yyyy-MM-dd HH:mm:ss')" />-->
		<!--<i></i>-->
		<!--</div>-->
		<!--</div>-->
		<div class="event_count fl">
			<span  class="total_count" the-id="allinfo1">${eventCount} 条</span>
		</div>
		<div class="event_top_edit fr">编辑</div>
	</div>

	<!--事件概述-->
	<div class="event_list whitebg" the-id="whitebg" module-id='1' the-top="event_overview">
		<div class="event_title_box">
			<h3>事件概述</h3>
			<div class="edit_icon" the-id="edit">
				<img src="../images/edit_app.png" />
			</div>
		</div>
		<template  v-if="firstSummary.summary.length > 0">
			<div class="detail_text">
				<p class="textarea" contenteditable="false" the-id="editText">{{firstSummary.summary}}</p>
			</div>
			<div class="arrow arrowdown" v-if="firstSummary.summary.length > 81"></div>
		</template>
		<template v-else>	
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>

	<!--首发媒体-->
	<div class="event_list" the-top="event_media">
		<div class="event_title_box">
			<h3>首发媒体</h3>
		</div>
		<template v-if="eventMedia.length>0">
			<div class="first_media_table border_box">
				<table>
					<thead>
					<tr>
						<th class="media_type">类型</th>
						<th class="media_time">时间</th>
						<th class="media_title">标题</th>
						<th class="media_from">来源</th>
					</tr>
					</thead>
					<tbody>
					<tr v-for="(value,index) in eventMedia">
						<td>{{value.sourceType}}</td>
						<td>{{value.ctime.substr(0,10)}}</br>{{value.ctime.substr(11,5)}}</td>
						<td>
							<a :href="value.url" v-if="value.title.length < 30"  target="_blank" >{{value.title}}</a>
							<a :href="value.url" v-else  target="_blank" >{{value.title.substr(0,30)}}...</a>
						</td>
						<td>{{value.webname}}</td>
					</tr>
					</tbody>
				</table>
			</div>
		</template>
		<template v-else>	
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>

	<!--事件脉略-->
	<div class="event_list" the-id="event_vein" the-top="event_vein">
		<div class="event_title_box">
			<h3>事件脉络</h3>
		</div>
		<template  v-if="eventVein.listCountInfo.length > 0">
			<div class="dike_start_time">
				<p>开始时间  {{eventVein.listCountInfo[0].ctime.substr(0,4)}}</p>
				<span class="triangledown triangle_position"></span>
			</div>
			<div class="dike_list_box border_box clearfix" v-for="(j,jIndex) in eventVein.listCountInfo">
				<div class="dike_list_left fl"><!--border_box-->
					<div class="dike_time_box">
						<span class="time_big">{{j.ctime.slice(11)}}</span></br>
						<span class="time_small">{{j.ctime.substr(5,5)}}</span>
					</div>
				</div>
				<div class="dike_list_right fr"><!--border_box-->
					<div class="triangle_box">
						<img src="../images/left_triangle.png" />
					</div>
					<!--首发-->
					<div class="first_flag_icon" v-if="jIndex == 0">
						<img src="../images/first_flag.png" />
					</div>
					<!-- <p class="first_flag">首发</p> -->
					<div class="dike_list_data_box">
						<div class="dike_list_data">
							<!--<img src="__PUBLIC__/appimages/eventanalysislistpage/bird.png">-->
							<span class="disk_from"  :title = "j.webname">{{j.webname}}</span>

							<span class="'disk_type zheng" v-if="j.orientationDesc == '正面'">{{j.orientationDesc}}</span>
							<span class="'disk_type fu" v-else-if="j.orientationDesc == '负面'">{{j.orientationDesc}}</span>
							<span class="'disk_type zhong" v-else >{{j.orientationDesc}}</span>
							<span class="disk_same">同：<span class="disk_num">{{j.repeatCount}}</span></span>
						</div>
						<div class="dike_list_data">
							<a :href="j.url" target="_blank" >{{j.title}}</a>
						</div>
						<div class="dike_list_data">
							<span class="disk_keyword">关键词：<span class="disk_keyword_txt">{{j.keyword}}</span></span>
						</div>
					</div>
				</div>
			</div>
			<div class="line"></div>
			<!--底部标记-->
			<div class="dike_list_flag_box clearfix" >
				<!-- 续 展开事件进展 -->
				<div class="dike_list_flag fl">完</div>
			</div>
		</template>
		<template v-else>	
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>

	<!--发展趋势-->
	<div class="event_list whitebg" the-id="whitebg" module-id='4' the-top="event_trend">
		<div class="event_title_box">
			<h3>发展趋势</h3>
			<div class="edit_icon" the-id="edit">
				<img src="../images/edit_app.png" />
			</div>
		</div>
		<template  v-if="developtrendSummary.summary.length > 0">
			<div class="detail_text">
				<p class="textarea" contenteditable="false" the-id="editText" the-ids="whitebg_editText">{{developtrendSummary.summary}}</p>
			</div>
			<div class="arrow arrowdown" v-if="developtrendSummary.summary.length > 81"></div>
		</template>
		<template v-else>	
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>
	<div class="event_list">
		<div id="developtrend"></div>
	</div>

	<!--调性分析-->
	<div class="event_list whitebg" the-id="whitebg" module-id='5' the-top="event_analyze">
		<div class="event_title_box">
			<h3>调性分析</h3>
			<div class="edit_icon" the-id="edit">
				<img src="../images/edit_app.png" />
			</div>
		</div>
		<template  v-if="eveAnalyzeSummary.summary.length > 0">
			<div class="detail_text">
				<p class="textarea" contenteditable="false" the-id="editText" the-ids="yqfzeditText">{{eveAnalyzeSummary.summary}}</p>
			</div>
			<div class="arrow arrowdown" v-if="eveAnalyzeSummary.summary.length > 81"></div>
		</template>
		<template v-else>	
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>
	<div class="event_list">
		<div id="eveAnalyze"></div>
	</div>
	<div class="event_list">
		<div class="event_title_box">
			<h4>情感占比分析</h4>
		</div>
		<div id="emotionScale"></div>
	</div>

	<!--关键词云-->
	<div class="event_list whitebg" the-id="whitebg" module-id='6'  the-top="event_words">
		<div class="event_title_box">
			<h3>关键词云</h3>
			<div class="edit_icon" the-id="edit">
				<img src="../images/edit_app.png" />
			</div>
		</div>
		<template  v-if="wordsCloudSummary.summary.length > 0">
			<div class="detail_text">
				<p class="textarea" contenteditable="false" the-id="editText" the-ids="keyeditText">{{wordsCloudSummary.summary}}</p>
			</div>
			<div class="arrow arrowdown" v-if="wordsCloudSummary.summary.length > 81"></div>
		</template>
		<template v-else>	
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>
	<div class="event_list">
		<div id="wordsCloud"></div>
	</div>
	<div class="event_list whitebg">
		<div class="event_title_box">
			<h4>事件高频词</h4>
		</div>
		<ul class="event_keyWords_ul" >
			<li v-for="(item,index) in eventWords.eventInfoWords" ><span>{{item.word}}</span> <span>{{item.count}}次</span></li>
		</ul>
	</div>
	<!--微博分析-->
	<div class="event_list whitebg" the-id="whitebg" module-id='7' the-top="blog_analyze">
		<div class="event_title_box">
			<h3>微博分析</h3>
			<div class="edit_icon" the-id="edit">
				<img src="../images/edit_app.png" />
			</div>
		</div>
		<template  v-if="bigVSpreadSummary.summary.length > 0">
			<div class="detail_text">
				<p class="textarea" contenteditable="false" the-id="editText">{{bigVSpreadSummary.summary}}</p>
			</div>
			<div class="arrow arrowdown" v-if="bigVSpreadSummary.summary.length > 81"></div>
		</template>
		<template v-else>	
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>
	<div class="event_list">
		<div class="event_title_box">
			<h4>大V分布</h4>
		</div>
		<div id="bigVSpread"></div>
		<div class="blog_spread_table border_box">
			<!-- border_box 公用样式，不要使用此class获取表格 -->
			<table class="border_box">
				<tr style="background:#f2f2f2">
					<th rowspan="1" class="blog_type">
						<span class="s1">数量</span>
						<p></p>
						<span class="s2">类型</span>
					</th>
					<th class="blog_government">政府</th> <!--style="width:140px"-->
					<th class="blog_media">媒体</th>
					<th class="blog_celebrity">名人</th>
				</tr>
				<tr class="assessDetail" v-for="item in blogAnalyze.bigVSpread">
					<td>{{item.number}}</td>
					<td>{{item.goverment}}</td>
					<td>{{item.media}}</td>
					<td>{{item.surperStar}}</td>
				</tr>
			</table>
		</div>
	</div>
	<!--影响力排行-->
	<div class="event_list whitebg">
		<div class="event_title_box">
			<h4>影响力排行</h4>
		</div>
		<ul class="yxlph clearfix">
			<li class="" v-for="(item,index) in blogAnalyze.affectedRank.nav" @click="blogTab(item,index)" :class="{'hover':blogMark==index}"><span>{{item.name}}</span> </li>
		</ul>
		<template v-if="blogAnalyze.affectedRank.govermentRank.length!=0 ||blogAnalyze.affectedRank.mediaRank.length!=0 ||blogAnalyze.affectedRank.starRank.length!=0">
			<div class="yxlph_table_box border_box">
				<table class="yxlph_table">
					<thead>
					<tr>
						<th>序号</th>
						<th>名称</th>
						<th>粉丝数</th>
						<th>事件中发帖数</th>
					</tr>
					</thead>
					<tbody>
					<tr v-for="(item,index) in blogAnalyze.affectedRank.govermentRank" v-if="blogAnalyze.affectedRank.govermentRank.length>0 && blogMark ==0">
						<td>{{index+1}}</td>
						<td>{{item.name}}</td>
						<td>{{item.fansCount}}</td>
						<td>{{item.statusesCount}}</td>
					</tr>
					<tr v-for="(item,index) in blogAnalyze.affectedRank.mediaRank" v-if="blogAnalyze.affectedRank.mediaRank.length>0 && blogMark ==1">
						<td>{{index+1}}</td>
						<td>{{item.name}}</td>
						<td>{{item.fansCount}}</td>
						<td>{{item.statusesCount}}</td>
					</tr>
					<tr v-for="(item,index) in blogAnalyze.affectedRank.starRank" v-if="blogAnalyze.affectedRank.starRank.length>0 && blogMark ==2">
						<td>{{index+1}}</td>
						<td>{{item.name}}</td>
						<td>{{item.fansCount}}</td>
						<td>{{item.statusesCount}}</td>
					</tr>
					</tbody>
				</table>
			</div>
		</template>
		<template  v-else>
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>
	<!--传播路径-->
	<template v-if="'<{$eventcount}>' != '0'">
		<div class="event_list whitebg">
			<div class="event_title_box">
				<h4>传播路径</h4>
			</div>
			<div class="force_photo">
				<div id="graph-container" class="graph"></div>
			</div>
			<ul class="cblj_ul1 clearfix">
				<li>
					<p>最大信息层级</p>
					<p>{{blogAnalyze.transRoute.maxDeep}}</p>
				</li>
				<li>
					<p>总传播人数</p>
					<p>{{blogAnalyze.transRoute.allAuthor}}</p>
				</li>
				<li>
					<p>总传播受众</p>
					<p>{{blogAnalyze.transRoute.allFans}}</p>
				</li>
			</ul>
		</div>
		<div class="event_list whitebg zycbr_wary" >
			<div class="event_title_box">
				<h4>主要传播人</h4>
			</div>
			<div class="zycbr_data_box clearfix">
				<template v-if="blogAnalyze.transRoute.topSix.length>0">
					<!--<div class="zycbr_public_box border_box" v-for="(item,index) in blogAnalyze.transRoute.topSix" v-if="index < 8">
						<div class="zycbr_public border_box">
							<p class="zycbr_head">
								<img :src="item.avatarUrl" alt="" v-if="item.avatarUrl">
								<img src="../images/default.png" alt="" v-else>
							</p>
							<p class="zycbr_tit"><span v-if="item.auther.length > 5">{{item.auther.substr(0,4)}}...</span><span v-else>{{item.auther}}</span>-
								<span v-if="item.region">{{item.region}}</span><span v-else>未知</span>-层级：{{item.depth}}</p>
							<p class="zycbr_p1">发帖： <span class="zycbr_dark">{{item.statuses_count}}</span></p>
							<p class="zycbr_p2">粉丝：<span class="zycbr_dark">{{item.followers_count}}</span></p>
							<p class="zycbr_p3">被转发数：<span class="zycbr_dark">{{item.childrenSize}}</span></p>
						</div>
						<p class="zycbr_p4">爆发时间点：{{timeTrans(item.time)}}</p>
					</div>-->
					<div class="zycbr_user_box border_box" v-for="(item,index) in blogAnalyze.transRoute.topSix">
						<div class="zycbr_user_info clearfix">
							<div class="zycbr_icon fl">
								<img :src="item.avatarUrl" alt="" v-if="item.avatarUrl" @click="turnauthorid(item)">
								<img src="../images/default.png" alt="" v-else>
							</div>
							<div class="zycbr_info border_box fl">
								<p class="zycbr_nickname">{{item.auther}}</p>
								<p class="zycbr_info_desc">
									<span class="zycbr_light_color">粉丝&nbsp;<span class="zycbr_dark_color">{{item.followers_count}}</span></span>
									<span class="zycbr_light_color">转发层级&nbsp;<span class="zycbr_dark_color">{{item.depth}}</span></span>
									<span class="zycbr_light_color no_mar_right">转发&nbsp;<span class="zycbr_dark_color">{{item.childrenSize}}</span></span>
								</p>
							</div>
							<div class="zycbr_event_post border_box fr">
								<p class="post_num">{{item.statuses_count}}</p>
								<span class="zycbr_light_color no_mar_right">事件发帖</span>
							</div>
						</div>
						<div class="zycbr_user_desc" v-if="item.kn_abstract.length > 65">{{item.kn_abstract.substr(0,65)}}...<a :href="item.url" class="zhycb_url">&nbsp;查看原文</a>
						</div>
						<div class="zycbr_user_desc" v-else>{{item.kn_abstract}}<a :href="item.url" class="zhycb_url">&nbsp;查看原文</a>
						</div>
					</div>
				</template>
				<template v-else>
					<div class="no_data">
						<img src="../images/nodata.png" />
						<p>暂无数据</p>
					</div>
				</template>
			</div>
		</div>
	</template>
	<!--博主地域-->
	<div class="event_list whitebg">
		<div class="event_title_box">
			<h4>博主地域</h4>
		</div>
		<div id="bloggerArea" class="bloggerArea"></div>
		<div class="bloggerArea_table"  v-if="blogAnalyze.bloggerArea.length>0">
			<table>
				<tbody>
				<tr v-for="(item,index) in blogAnalyze.bloggerArea">
					<td>{{item.name}}</td>
					<td>{{item.value}}</td>
				</tr>
				</tbody>
			</table>
		</div>
		<template v-else>
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>
	<!--水军分析-->
	<div class="event_list whitebg">
		<div class="event_title_box">
			<h4>水军分析</h4>
		</div>
		<div id="netizenAnalysis" class="echartsCss"></div>
	</div>
	<!--水军分析-->
	<div class="event_list whitebg">
		<div class="event_title_box">
			<h4>情感分析</h4>
		</div>
		<div id="emotionAnalysis" class="echartsCss"></div>
	</div>
	<!--媒体分析-->
	<div class="event_list whitebg" the-id="whitebg" module-id='8'  the-top="media_analyze">
		<div class="event_title_box">
			<h3>媒体分析</h3>
			<div class="edit_icon" the-id="edit">
				<img src="../images/edit_app.png" />
			</div>
		</div>
		<template  v-if="mediaAnalysisSummary.summary.length > 0">
			<div class="detail_text">
				<p class="textarea" contenteditable="false" the-id="editText">{{mediaAnalysisSummary.summary}}</p>
			</div>
			<div class="arrow arrowdown" v-if="mediaAnalysisSummary.summary.length > 81"></div>
		</template>
		<template v-else>	
			<div class="no_data">
				<img src="../images/nodata.png" />
				<p>暂无数据</p>
			</div>
		</template>
	</div>
	<!--媒体来源-->
	<div class="event_list whitebg" the-id="whitebg" module-id='8'>
		<div class="event_title_box">
			<h4>媒体来源</h4>
		</div>
		<div id="mediaAnalysis" class="echartsCss"></div>
	</div>
	<!--媒体来源-->
	<div class="event_list whitebg" the-id="whitebg" module-id='8'>
		<div class="event_title_box">
			<h4>媒体活跃度</h4>
		</div>
		<div id="mediaActive" class="echartsCss"></div>
	</div>
	<!--观点分析-->
	<div class="event_list whitebg" the-id="whitebg" module-id='9' the-top="point_analyze">
		<div class="event_title_box">
			<h3>观点分析</h3>
		</div>
	</div>
	<!--网民观点-->
	<div class="event_list whitebg" the-id="whitebg" module-id='8'>
		<div class="event_title_box">
			<h4>网民观点</h4>
		</div>
		<div id="peoplePoint" class="echartsCss"></div>
	</div>
	<!--重点微博-->
	<div class="event_list whitebg">
		<div class="event_title_box">
			<h4>重点微博</h4>
		</div>
		<ul class="yxlph">
			<li v-for="(item,index) in pointView.importentBlog.nav" @click="pointTab(item,index)" :class="{'hover':pointMark==index}">
				<span>{{item.name}}</span>
			</li>
		</ul>
	</div>
	<template v-if="pointView.importentBlog.govermentblog.length!=0 ||pointView.importentBlog.mediablog.length!=0 ||pointView.importentBlog.starblog.length!=0">
		<div class="event_list whitebg" v-if="pointView.importentBlog.govermentblog.length>0 && pointMark == 0">
			<div class="zdwb_public"  v-for="(item,index) in pointView.importentBlog.govermentblog">
				<p class="zycbr_head" v-if="item.avatarUrl != ''">
					<img :src="item.avatarUrl" alt="">
				</p>
				<p class="zycbr_head" v-if="item.avatarUrl == ''">
					<img src="../images/default.png" alt="">
				</p>
				<p class="p1"><span style="color:#38a9e1;">{{item.name}}</span></p>
				<p>
					<span style="color:#999;">{{item.ctime}}</span>
					<span><a :href="item.url" target="_blank" style="color:#feb364">原文</a></span>
				</p>
				<div class="infos">
					<p :title="item.title">{{item.title.substr(0,58)}}...</p>
				</div>
			</div>
			<div style="clear: both;width: 100%;height: 0px;"></div>
		</div>
		<div class="no_data" v-if="pointView.importentBlog.govermentblog.length == 0 && pointMark == 0">
			<img src="../images/nodata.png" />
			<p>暂无数据</p>
		</div>

		<div class="event_list whitebg" v-if="pointView.importentBlog.mediablog.length>0 && pointMark == 1">
			<div class="zdwb_public"  v-for="(item,index) in pointView.importentBlog.mediablog" >
				<p class="zycbr_head" v-if="item.avatarUrl != ''">
					<img :src="item.avatarUrl" alt="">
				</p>
				<p class="zycbr_head" v-if="item.avatarUrl == ''">
					<img src="../images/default.png" alt="">
				</p>
				<p class="p1"><span style="color:#38a9e1;">{{item.name}}</span></p>
				<p>
					<span style="color:#999;">{{item.ctime}}</span>
					<span><a :href="item.url" target="_blank" style="color:#feb364">原文</a></span>
				</p>
				<div class="infos">
					<p :title="item.title">{{item.title.substr(0,58)}}...</p>
				</div>
			</div>
			<div style="clear: both;width: 100%;height: 0px;"></div>
		</div>
		<div class="no_data"  v-if="pointView.importentBlog.mediablog.length == 0 && pointMark == 1">
			<img src="../images/nodata.png" />
			<p>暂无数据</p>
		</div>

		<div class="event_list whitebg" v-if="pointView.importentBlog.starblog.length>0 && pointMark == 2">
			<div class="zdwb_public"  v-for="(item,index) in pointView.importentBlog.starblog">
				<p class="zycbr_head" v-if="item.avatarUrl != ''">
					<img :src="item.avatarUrl" alt="">
				</p>
				<p class="zycbr_head" v-if="item.avatarUrl == ''">
					<img src="../images/default.png" alt="">
				</p>
				<p class="p1"><span style="color:#38a9e1;">{{item.name}}</span></p>
				<p>
					<span style="color:#999;">{{item.ctime}}</span>
					<span><a :href="item.url" target="_blank" style="color:#feb364">原文</a></span>
				</p>
				<div class="infos">
					<p :title="item.title">{{item.title.substr(0,58)}}...</p>
				</div>
			</div>
			<div style="clear: both;width: 100%;height: 0px;"></div>
		</div>
		<div class="no_data" v-if="pointView.importentBlog.starblog.length == 0 && pointMark == 2">
			<img src="../images/nodata.png" />
			<p>暂无数据</p>
		</div>

	</template>
	<template  v-else>
		<div class="no_data">
			<img src="../images/nodata.png" />
			<p>暂无数据</p>
		</div>
	</template>

</div>
<!-- toast提示信息 -->
<div class="toast_tips" the-id="toast_tips">提示信息</div>
</body>
<script type="text/javascript" src="../lib/echarts.min.new.js"></script>
<script type="text/javascript" src="../lib/echarts-wordcloud.min.js"></script>
<script type="text/javascript" src="../lib/china.js"></script>
<script>
    document.documentElement.scrollTop = 0;
    //var beginTime = '<{$beginTime}>',endTime='<{$endTime}>',eventId='<{$eventId}>',title = '<{$title}>',hover='<{$hover}>',sharerid='<{$sharerid}>',username='<{$username}>';
    var beginTime = '2018-03-28 09:51',endTime='2018-04-04 09:51',eventId='abb12a50322a11e8d3c8c87096657d9f',title = '',hover='',sharerid='',username='';
    //    请求参数
    //var data = {beginTime:beginTime,endTime:endTime,eventId:eventId,sharerid:sharerid};
    var data = {beginTime:'2018-03-28 09:51',endTime:'2018-04-04 09:51',eventId: '',sharerid: ''};
    //   关键词nav  数据获取开始
    //var timedata = '<{$timedata}>';
    //timedata = timedata =='null' ? [] :JSON.parse(timedata);
	var timedata = ['20180404','20180405','20180406','20180407','20180408','20180409','20180410'];
    var keyWordsDate = [ {date:'全部',beginTime:data.beginTime,endTime:data.endTime,eventId:eventId}];
    for(var i=0;i<timedata.length;i++){
        var obj = {date:'',beginTime:'',endTime:'',eventId:eventId};
        obj.date = timedata[i].substring(4,6)+'月'+timedata[i].substring(6,8)+'日';
        obj.beginTime = timedata[i].substring(0,8)+'000000';
        obj.endTime = timedata[i].substring(0,8)+'235959';
        keyWordsDate.push(obj)
    };
    function animateScroll(current){
        var contentScrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        if(contentScrollTop>current){
            var speed = parseFloat((contentScrollTop -current)/10).toFixed(2);
            scrollToptimer = setInterval(function () {
                contentScrollTop -= Number(speed);
                document.documentElement.scrollTop = contentScrollTop;
                document.body.scrollTop = contentScrollTop;
                if (contentScrollTop - current<1) {
                    clearInterval(scrollToptimer);
                }
            }, 10);
        }else{
            var speed = parseFloat((current - contentScrollTop)/10).toFixed(2);
            scrollToptimer = setInterval(function () {
                contentScrollTop += Number(speed);
                document.documentElement.scrollTop = contentScrollTop;
                document.body.scrollTop = contentScrollTop;
                if (current - contentScrollTop <1) {
                    clearInterval(scrollToptimer);
                }
            }, 10);
        }
    };
    //var firstSummary = '<{$sum}>';
    //firstSummary = JSON.parse(firstSummary);
	var firstSummary = ${firstSummary};
	var developtrendSummary =${developtrendSummary};
	var eveAnalyzeSummary = ${eveAnalyzeSummary};
	var wordsCloudSummary = ${wordsCloudSummary};
	var bigVSpreadSummary = ${bigVSpreadSummary};
	var mediaAnalysisSummary =${mediaAnalysisSummary};
	
    var evenWrap = new Vue({
        el:'#wrap',
        data:{
			docTitle: '文章标题',
            firstSummary:firstSummary,
			developtrendSummary: developtrendSummary,
			eveAnalyzeSummary: eveAnalyzeSummary,
			wordsCloudSummary: wordsCloudSummary,
			bigVSpreadSummary: bigVSpreadSummary,
			mediaAnalysisSummary: mediaAnalysisSummary,
            //nodata:'<{$eventcount}>',                                             //  数据
            nodata:'2423',                                             //  数据
            eventList:[
                {name:'事件概述',src:'__PUBLIC__/images/comold/overview.png',src_w:'__PUBLIC__/images/comold/overview_w.png',id:1},
                {name:'首发媒体',src:'__PUBLIC__/images/comold/eveMedia.png',src_w:'__PUBLIC__/images/comold/eveMedia_w.png',id:2},
                {name:'事件脉络',src:'__PUBLIC__/images/comold/eveVein.png',src_w:'__PUBLIC__/images/comold/eveVein_w.png',id:3},
                {name:'发展趋势',src:'__PUBLIC__/images/comold/eveTrend.png',src_w:'__PUBLIC__/images/comold/eveTrend_w.png',id:4},
                {name:'调性分析',src:'__PUBLIC__/images/comold/eveAnalyze.png',src_w:'__PUBLIC__/images/comold/eveAnalyze_w.png',id:5},
                {name:'关键词云',src:'__PUBLIC__/images/comold/eveWords.png',src_w:'__PUBLIC__/images/comold/eveWords_w.png',id:6},
                {name:'微博分析',src:'__PUBLIC__/images/comold/blogAnalyze.png',src_w:'__PUBLIC__/images/comold/blogAnalyze_w.png',id:7},
                {name:'媒体分析',src:'__PUBLIC__/images/comold/mediaAnalyze.png',src_w:'__PUBLIC__/images/comold/mediaAnalyze_w.png',id:8},
                {name:'观点分析',src:'__PUBLIC__/images/comold/pointAnalyze.png',src_w:'__PUBLIC__/images/comold/pointAnalyze_w.png',id:9},
            ],                                                     //  nav数据
            number:0,                                                             //  nav
            eventMedia:${eventMedia},                                                       //  首发媒体数据
            eventVein:{
                listCountInfo:${listCountInfo},                        //  事件脉络数据
                top10:[],
                topmore:[],
                buttonTitle:'展开事件进展',
                buttonImg:"../images/white_down.png",
                showInfo:false,
                extend:'续'
            },                            //  事件脉络数据
            eventWords:{
                imgArray: keyWordsDate,                                                  //  关键词云导航数据
                timer:null,                          //  关键词云轮播图
                timename:"全部",                          //  关键词云时间
                eventInfoWords:${eventInfoWords},               //  事件高频词数据
            },                            //  关键词云
            mark:0,                                                               //  关键词云标记
            blogAnalyze:{
                echartData:[{name:'hahah '}],
                bigVSpread:[
					{
						number:'粉丝数>50W',
						goverment:5,
						media:0,
						surperStar:10
					},
					{
						number:'粉丝数>100W',
						goverment:4,
						media:0,
						surperStar:5
					},
					{	
						number:'粉丝数>1000W',
						goverment:3,
						media:0,
						surperStar:3
					}
				],
                affectedRank:{
                    nav:[
                        {name:'政府',id:1},
                        {name:'媒体',id:2},
                        {name:'名人',id:3}
                    ],
                    govermentRank:${govermentRank},                              //   政府
                    mediaRank:${mediaRank},                                  //    媒体
                    starRank:${starRank}                                    //    名人
                },                              //  影响力排行
                transRoute:{
                    topSix:${topSix},
                    maxDeep: ${maxDeep},
                    allFans: ${allFans},
                    allAuthor: ${allAuthor}
                },                   //  传播途径（主要传播人）
                bloggerArea:${bloggerArea}                                   //  博主地域
            },                                                   //  微博分析数据
            blogMark:0,
            pointView:{
                importentBlog:{
                    nav:[
                        {name:'政府',id:1},
                        {name:'媒体',id:2},
                        {name:'名人',id:3}
                    ],
                    govermentblog:${govermentblog},                              //   政府
                    mediablog:${mediablog},                                  //    媒体
                    starblog:${starblog}                                  //    名人
                }                                //  重点微博
            },                                                     //  观点分析数据
            pointMark:0,
            exportAllInfoParams:{
                monitorname:'舆情专报',
                shareid:sharerid,
                eventid:eventId,
//                event_monitor_base:event_monitor_base,
                event_summary_info:'',                            //   事件概述
                first_media_info:'',                              //   首发媒体
                event_vein_info:'',                               //   事件脉络
                development_trend_base:'',                       //   发展趋势概述
                developmentTrendOnePic:'',                       //   发展趋势Base64
                tonal_analysis_base:'',                          //   调性分析概述
                tonalAnalysisOnePic:'',                          //   调性分析—调性分析Base64
                tonalAnalysisTwoPic:'',                          //   调性分析—情感占比分析Base64
                key_word_base:'',                                 //   关键词云概述
                keyWordOnePic:'',                                 //   关键词云—关键词云分析Base64
                key_word_info:'',                                 //   关键词云—列表
                microblog_analysis_base:'',                      //   微博分析概述
                microblogBigvOnePic:'',                          //   微博分析—大V分布Base64
                microblog_bigv_info:'',                          //   微博分析—大Vtable列表
                microblog_government_info:'',                   //   微博分析—影响力table列表—政府
                microblog_media_info:'',                         //   微博分析—影响力table列表—媒体
                microblog_celebrity_info:'',                    //    微博分析—影响力table列表—名人
                microblog_spred_base:'',                         //   微博分析—传播路径
                microblog_spred_info:'',                         //   微博分析—传播路径列表
                microblogRegionOnePic:'',                        //   微博分析—博主地域Base64
                microblog_region_leftInfo:'',                   //   微博分析—博主地域table列表
                microblog_region_rightInfo:'',                  //   微博分析—博主地域table列表
                microblogNavyOnePic:'',                          //   微博分析—水军分析
                microblogEmotionOnePic:'',                       //  微博分析—情感分析
                media_analysis_base:'',                          //   媒体分析概述
                mediaSourceOnePic:'',                            //   媒体分析—媒体来源Base64
                mediaActivityOnePic:'',                          //   媒体分析—媒体活跃度Base64
                viewpointNetizenOnePi:'',                        //   观点分析—网民观点Base64
                viewpoint_government_info:'',                   //   观点分析—重点微博table列表—政府
                viewpoint_media_info:'',                         //   观点分析—重点微博table列表—媒体
                viewpoint_celebrity_info:'',                     //   观点分析—重点微博table列表—名人
            }                  //  全部导出参数
        },	
		methods:{
            tab:function (item,index) {
                var overview = $('[the-top=event_overview]').offset().top;            //   事件概述
                var eveMedia = $('[the-top=event_media]').offset().top;               //   首发媒体
                var eveVein = $('[the-top=event_vein]').offset().top;                 //   事件脉络
                var eveTrend = $('[the-top=event_trend]').offset().top;               //   发展趋势
                var eveAnalyze = $('[the-top=event_analyze]').offset().top;           //   调性分析
                var eveWords = $('[the-top=event_words]').offset().top;               //   关键词云
                var blogAnalyze = $('[the-top=blog_analyze]').offset().top;           //   微博分析
                var mediaAnalyze = $('[the-top=media_analyze]').offset().top;         //   媒体分析
                var pointAnalyze = $('[the-top=point_analyze]').offset().top;         //   观点分析
                switch(item.id)
                {
                    case 1:
                    	this.number = 0;
						this.evenav();
                        animateScroll(overview);
                        break;
                    case 2:
                    	this.number = 1;
						this.evenav();
                        animateScroll(eveMedia);
                        break;
                    case 3:
                    	this.number = 2;
						this.evenav();
                        animateScroll(eveVein);
                        break;
                    case 4:
                    	this.number = 3;
						this.evenav();
                        animateScroll(eveTrend);
                        break;
                    case 5:
                    	this.number = 4;
						this.evenav();
                        animateScroll(eveAnalyze);
                        break;
                    case 6:
                    	this.number = 5;
						this.evenav();
                        animateScroll(eveWords);
                        break;
                    case 7:
                    	this.number = 6;
						this.evenav();
                        animateScroll(blogAnalyze);
                        break;
                    case 8:
                    	this.number = 7;
						this.evenav();
                        animateScroll(mediaAnalyze);
                        break;
                    case 9:
                    	this.number = 8;
						this.evenav();
                        animateScroll(pointAnalyze);
                        break;
                }
            },
            evenav:function () {
                $('[the-id=event_navs]').slideToggle();
            },
            turnauthorid: function (item) {
            	//跳转至微博个人信息页
                window.open('https://weibo.com/'+item.authorId)
            },
            /*toggle:function () {
                //  事件脉络
                this.eventVein.showInfo = !this.eventVein.showInfo;
                if(this.eventVein.showInfo == true){
                    this.eventVein.buttonImg = "../images/white_up.png";
                }else{
                    this.eventVein.buttonImg = "../images/white_down.png";
                }
                var element = $('.btn_toggle .btn')[0];
                if(!this.eventVein.showInfo){
                    var eveVein = $('[the-id=event_vein]').offset().top;                 //   事件脉络
                    document.documentElement.scrollTop = eveVein;
                    this.eventVein.buttonTitle = '展开事件进展';
                    this.eventVein.extend = "续";
                }else{
                    this.eventVein.buttonTitle = '合并事件进展';
                    this.eventVein.extend = "完";
                }
                this.eventVein.listCountInfo = this.eventVein.showInfo ? this.eventVein.topmore : this.eventVein.top10;

            },  */                                    //  播放轮播图
            drawTurnCloum:function () {
                var _this = this;
				loadComplete(echarts);
               
                function loadComplete(ec) {
					//空数据展示图
					var noData = '<div class="no_data"><img src="../images/nodata.png" /><p>暂无数据</p></div>';
					//处理没有值时的情况
					if(${eventInfoWords}.length == 0){
						$('#wordsCloud').html(noData);
					}else{
						var hotwords = ec.init($('#wordsCloud').get(0));
						var hotoption = {
							tooltip: {
								show: true,
								formatter:function(value){
									var subString =value.name+':'+value.value/20;
									return subString;
								},
								textStyle: {
									fontSize: getDpr()
								},
								confine: true
							},
							animation:false,
							series: [{
								type: 'wordCloud',
								shape: 'circle',
								left: 'center',
								top: 'center',
								width: '90%',
								height: '80%',
								right: null,
								bottom: null,
								sizeRange: getSizeRange(),	
								rotationRange: [0, 0],
								gridSize: getGridSize(),
        						drawOutOfBound: false,
								textStyle: {
									normal: {
										// fontFamily: 'sans-serif',
										fontWeight: 'bold',
										// Color can be a callback function or a color string
										color: function (params) {
											var colorList = [
												'#4acff3','#6892d5','#edcc5e', '#eec258','#4acff3','#6892d5','#edcc5e', '#eec258','#4acff3','#6892d5','#edcc5e', '#eec258','#4acff3','#6892d5','#edcc5e', '#eec258','#4acff3','#6892d5','#edcc5e', '#eec258'
											];
											return colorList[params.dataIndex];
										}
									},
									emphasis: {
										shadowBlur: 10,
										shadowColor: '#333'
									}
								},
								data:${eventInfoWords}
							}]
						};
						hotwords.setOption(hotoption);
					}
					
					
					$('[the-id=imgArray_ul]').addClass("hide");
					var timenames = _this.eventWords.timename || '全部';
					$('[the-id=keywords_select]').html(timenames);
					
					//      关键词云摘要
					//$('[the-ids=keyeditText]').text(JSON.parse(res).summary);
					//不超过100个字时，不显示 展开和收起 按钮， 超过100个字显示
					if($('[the-ids=keyeditText]').text().length > 81){
						$('[the-ids=keyeditText]').parent('.detail_text').siblings('.arrow').show();
						$('[the-ids=keyeditText]').parent('.detail_text').css('height','1.62rem');
					}else{
						$('[the-ids=keyeditText]').parent('.detail_text').siblings('.arrow').hide();
						$('[the-ids=keyeditText]').parent('.detail_text').css('height','auto');
					}
                }
            },
            pointTab:function (item,index) {
                // 重点微博
                switch (item.id)
                {
                    case 1:
                        this.pointMark = 0
                        break;
                    case 2:
                        this.pointMark = 1
                        break;
                    case 3:
                        this.pointMark = 2
                        break;
                }
            },
            drawEchart:function () {
                var _this = this;
				loadFinish(echarts);
				
                function loadFinish(ec) {
					//空数据展示图
					var noData = '<div class="no_data"><img src="../images/nodata.png" /><p>暂无数据</p></div>';
				
                    //发展趋势
					//处理没有值时的情况
					if(${developtrendoption_legend_data}.length == 0 && ${developtrendoption_xAxis_data}.length == 0 && ${developtrendoption_xAxis_series}.length == 0 == ''){
						$('#developtrend').html(noData);
					}else{
						var developtrend = ec.init($('#developtrend').get(0));
						var developtrendoption = {
							color:['#00b3e8','#ae30ee','#4fbcbe','#fc5e67','#ff904b','#9594cb','#3584e6','#6474de','#f9c04f'],
							legend: {
								left:'left',
								padding: 8,
								itemGap: getLegendItemGap(), 
								textStyle: {
									fontSize: getDpr()
								},
								data:${developtrendoption_legend_data}
							},
							tooltip : {
								trigger: 'axis', 
								textStyle: {
									fontSize: getDpr()
								},
								confine: true,
								triggerOn: 'click'
							},
							dataZoom:[
								{
									// dataZoomIndex: 1,
									type: 'inside',
									moveOnMouseMove: false, //表示鼠标滚轮不能触发平移。
									zoomOnMouseWheel: false //表示鼠标滚轮不能触发缩放。
								},{
									show: true,
									realtime: true,
									handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
									handleSize: '100%',
									handleStyle: {
										color: '#fff',
										shadowBlur: 3,
										shadowColor: 'rgba(0, 0, 0, 0.6)',
										shadowOffsetX: 2,
										shadowOffsetY: 2
									},
									textStyle: {
										fontSize: getDpr()
									},
									height: getDataZoomHeight(),
									bottom: 0
								}
							],
							xAxis : {
								type : 'category',
								boundaryGap : false,
								data : ${developtrendoption_xAxis_data},
								axisLabel: {
									fontSize: getDpr()
								}
							},
							yAxis :  {
								type : 'value',
								axisLabel : {
									formatter: '{value}',
									fontSize: getDpr()
								}
							},
							grid:{
								borderWidth:0,
								left: getGridX(),
								top: getGridY(),
								right: '14',
								bottom: getDprZoom()
							},
							animation:false,
							series:${developtrendoption_xAxis_series}
						};                                                              //   发展趋势
						developtrend.setOption(developtrendoption);
					}
					
                    
					//$('[the-ids=whitebg_editText]').text(res.summary);
					//不超过100个字时，不显示 展开和收起 按钮， 超过100个字显示
					if($('[the-ids=whitebg_editText]').text().length > 81){
						$('[the-ids=whitebg_editText]').parent('.detail_text').siblings('.arrow').show();
						$('[the-ids=whitebg_editText]').parent('.detail_text').css('height','1.62rem');
					}else{
						$('[the-ids=whitebg_editText]').parent('.detail_text').siblings('.arrow').hide();
						$('[the-ids=whitebg_editText]').parent('.detail_text').css('height','auto');
					}
					
					
                    //调性分析                                                          
					//$('[the-ids=yqfzeditText]').text(Analyzeresult.summary);
					//不超过100个字时，不显示 展开和收起 按钮， 超过100个字显示
					if($('[the-ids=yqfzeditText]').text().length > 81){
						$('[the-ids=yqfzeditText]').parent('.detail_text').siblings('.arrow').show();
						$('[the-ids=yqfzeditText]').parent('.detail_text').css('height','1.62rem');
					}else{
						$('[the-ids=yqfzeditText]').parent('.detail_text').siblings('.arrow').hide();
						$('[the-ids=yqfzeditText]').parent('.detail_text').css('height','auto');
					}
					
					//处理没有值时的情况
					if(${eveAnalyzeoption_xAxis_data}.length == 0 && ${eveAnalyzeoption_xAxis_series}.length ==0){
						$('#eveAnalyze').html(noData);
					}else{
						var eveAnalyze = ec.init($('#eveAnalyze').get(0));                    //   调性分析
						var eveAnalyzeoption = {
							color:['#00b1e2','#ec3c44','#fca338'],
							legend: {
								left:'center',
								padding: 8,
								data:['正面','中性','负面'],
								textStyle: {
									fontSize: getDpr()
								}
							},
							tooltip : {
								trigger: 'axis',
								textStyle: {
									fontSize: getDpr()
								},
								confine: true,
								triggerOn: 'click'
							},
							dataZoom:[
								{
									// dataZoomIndex: 1,
									type: 'inside',
									moveOnMouseMove: false, //表示鼠标滚轮不能触发平移。
									zoomOnMouseWheel: false //表示鼠标滚轮不能触发缩放。
								},{
									show : true,
									realtime : true,
									handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
									handleSize: '100%',
									handleStyle: {
										color: '#fff',
										shadowBlur: 3,
										shadowColor: 'rgba(0, 0, 0, 0.6)',
										shadowOffsetX: 2,
										shadowOffsetY: 2
									},
									textStyle: {
										fontSize: getDpr()
									},
									height: getDataZoomHeight(),
									bottom: 0
								}
							],
							xAxis : {
								type : 'category',
								boundaryGap : false,
								data : ${eveAnalyzeoption_xAxis_data},
								axisLabel: {
									fontSize: getDpr()
								}
							},
							yAxis : {
								type : 'value',
								axisLabel : {
									formatter: '{value}',
									fontSize: getDpr()
								}
							},
							grid:{
								borderWidth:0,
								left: getGridX(),
								top:'60',
								right:'14',
								bottom: getDprZoom()
							},
							animation:false,
							series: ${eveAnalyzeoption_xAxis_series}
						};
						eveAnalyze.setOption(eveAnalyzeoption);
					}
					
					if(${emotionscaleoption_series_data}.length ==0){
						$('#emotionScale').html(noData);
					}else{
						var emotionScale = ec.init($('#emotionScale').get(0));                //   情感占比分析
						var emotionscaleoption = {
							color:['#00b2e6','#ffa741','#ed3e4a'],
							tooltip : {
								trigger: 'item',
								formatter: "{a} <br/>{b} : {c} ({d}%)",
								textStyle: {
									fontSize: getDpr()
								},
								confine: true
							},
							legend: {
								left : 'center',
								padding: 8,
								data:['正面','中性','负面'],
								textStyle: {
									fontSize: getDpr()
								}
							},
							series : [
								{
									name:'情感占比分析',
									type:'pie',
									radius :  ['10px', '50%'],
									center: ['50%', '50%'],
									itemStyle: {
										normal: {
											color: function(params) {
												// build a color map as your need.
												var colorList = [
													'#00b2e6','#ffa741','#ed3e4a'
												];
												return colorList[params.dataIndex]
											},
											label:{
												show:true,
												position:'outside',
												formatter:'{b} : {d}%',
												fontSize:getDpr()
											},
											labelLine:{
												length: getLength(),
												length2: getLength2()
											}
										}
									},
									data: ${emotionscaleoption_series_data}
								}
							],
							animation:false
						};
						emotionScale.setOption(emotionscaleoption);
					}
																		
					//      微博分析
					//处理没有值时的情况
					if(${bigVSpreadoption_series_data}.length ==0){
						$('#bigVSpread').html(noData);
					}else{
						var bigVSpread = ec.init($('#bigVSpread').get(0));                    //   大V分布
						var bigVSpreadoption = {
							color:['#35b6e6','#fd9746','#feb509','#3587e6','#b0d461','#6377ed'],
							tooltip : {
								trigger: 'item',
								formatter: "{a} <br/>{b} : {c} ({d}%)",
								textStyle: {
									fontSize: getDpr()
								},
								confine: true
							},
							legend: {
								left : 'center',
								padding: 8,
								itemGap: getLegendItemGap2(),
								data:['企业','政府','机构','名人','网站','媒体'],
								textStyle: {
									fontSize: getDpr()
								}
							},
							calculable : false,
							series : [
								{
									name:'大V分布',
									type:'pie',
									radius :  ["10px", '50%'],
									center: ['50%', '50%'],
									itemStyle: {
										normal: {
											label:{
												show:true,
												position:'outside',
												formatter:'{b} : {d}%',
												fontSize: getDpr()
											},
											labelLine:{
												length: getLength(),
												length2: getLength2()
											}
										}
									},
									data: ${bigVSpreadoption_series_data}
								}
							],
							animation:false
						};
						bigVSpread.setOption(bigVSpreadoption);
					}										
					
					//处理没有值时的情况
					if(${node}.length == 0 && ${edges}.length == 0) {
						$('#graph-container').css('background','#fff').html(noData);
					}else{
						var transRoute = echarts.init($('#graph-container').get(0)) ;         //传播途径
						var nodes = ${node};
						var edges =${edges};
						var transRouteOption = {
							series : [
								{
									type: 'graph',
									layout: 'none',
									// progressiveThreshold: 700,
									nodes: nodes.map(function (node) {
										return {
											x: node.x,
											y: node.y,
											id: node.id,
											name: node.label,
											symbolSize: node.size,
											itemStyle: {
												normal: {
													color: node.color
												}
											}
										};
									}),
									edges: edges.map(function (edge) {
										return {
											source: edge.sourceID,
											target: edge.targetID
										};
									}),
									label: {
										emphasis: {
											position: 'right',
											show: true,
	                                        fontSize: getDpr()
										}
									},
									roam: true,
									focusNodeAdjacency: true,
									lineStyle: {
										normal: {
											width: 0.5,
											color:'#FEF806',
											curveness: 0.3,
											opacity: 0.7
										}
									}
								}
							]
						}
						transRoute.setOption(transRouteOption);
					}										
                    
					//处理没有值时的情况
					if(${bloggerArea}.length == 0){
						$('#bloggerArea').html(noData);
					}else{
						var bloggerArea = ec.init($('#bloggerArea').get(0));                  //   博主地域
						var bloggerAreaoption = {
							tooltip : {
								show:true,
								trigger: 'item',
								textStyle: {
									fontSize: getDpr()
								},
								confine: true
							},
							visualMap: {
								min: 0,
								max: ${bloggerMaxCount},
								left: 'left',
								top: 'bottom',
								calculable: true,
								text:['高','低'],
								textStyle: {
									fontSize: getDpr()
								}
							},
							series : [
								{
									name: '博主地域',
									type: 'map',
									mapType: 'china',
									roam: false,
									label: {
										normal: {
											show: true, //显示文本
											fontSize: getDpr2()
										},
										emphasis: {
											show: true
										}
									},
									data:${bloggerArea}
								}
							],
							animation:false
						};
						bloggerArea.setOption(bloggerAreaoption);
					}										
						
					//处理没有值时的情况
					if(${netizenAnalysisoption_series_data}.length == 0){
						$('#netizenAnalysis').html(noData);
					}else{
						var netizenAnalysis = ec.init($('#netizenAnalysis').get(0));          //   水军分析	
						var netizenAnalysisoption = {
							color:['#fb3a16','#ff9f46'],
							tooltip : {
								trigger: 'item',
								formatter: "{a} <br/>{b} : {c} ({d}%)",
								textStyle: {
									fontSize: getDpr()
								},
								confine: true
							},
							legend: {
								orient : 'vertical',
								left : 'left',
								padding: 8,
								itemGap: getLegendItemGap3(),
								data:['真实用户','水军'],
								textStyle: {
									fontSize: getDpr()
								}
							},
							series : [
								{
									name:'水军分析',
									type:'pie',
									radius : [0,'50%'],
									center: ['48%', '55%'],
									itemStyle: {
										normal: {
											label:{
												show:true,
												position:'outside',
												formatter:"{b} : {d}%",
												fontSize: getDpr()
											},
											lableLine:{
												length: getLength(),
												length2: getLength2()
											}
										}
									},
									data: ${netizenAnalysisoption_series_data}
								}
							],
							animation:false
						};
						netizenAnalysis.setOption(netizenAnalysisoption);
					}															
                    
					//处理没有值时的情况
					if(${emotionAnalysis_series_data}.length == 0){
						$('#emotionAnalysis').html(noData);
					}else{
						var emotionAnalysis = ec.init($('#emotionAnalysis').get(0));          //   情感分析
						var emotionAnalysisoption = {
							tooltip : {
								trigger: 'item',
								formatter: "{a} <br/>{b} : {c} ({d}%)",
								textStyle: {
									fontSize: getDpr()
								},
								confine: true
							},
							legend: {
								left : 'center',
								padding: 8,
								data:['正面','中性','负面'],
								textStyle: {
									fontSize: getDpr()
								}
							},
							series : [
								{
									name:'情感分析',
									type:'pie',
									radius : [0, '50%'],
									center: ['50%', '50%'],
									itemStyle: {
										normal: {
											color: function(params) {
												// build a color map as your need.
												if(params.data.name == '正面'){
													return  '#00b2e6';
												}else if(params.data.name == '中性'){
													return  '#ffa741';
												}else{
													return  '#ed3e4a';
												}
											},
											label:{
												show:true,
												position:'outside',
												formatter:'{b} : {d}%',
												fontSize: getDpr()
											},
											labelLine:{
												length: getLength(),
												length2: getLength2()
											}
										}
									},
									data: ${emotionAnalysis_series_data}
								}
							],
							animation:false
						};
						emotionAnalysis.setOption(emotionAnalysisoption);
					}						
                    
					//处理没有值时的情况
					if(${mediaSourceoption_series_data}.length == 0){
						$('#mediaAnalysis').html(noData);
					}else{
						var mediaSource = ec.init($('#mediaAnalysis').get(0));                //   媒体来源
						var mediaSourceoption = {
							color:['#fd1925','#6172e1','#ff701d','#03d8f7','#3684e3','#d527e9','#825af6','#ff5d41'],
							tooltip : {
								trigger: 'item',
								formatter: "{a} <br/>{b} : {c} ({d}%)",
								textStyle: {
									fontSize: getDpr()
								},
								confine: true
							},
							legend: {
								left : 'center',
								padding: 8,
								data: ['网媒', '论坛', '博客', '微博', '报刊', '微信', '视频', 'APP', '其他'],
								textStyle: {
									fontSize: getDpr()
								}
							},
							calculable : false,
							series : [
								{
									name:'媒体来源',
									type:'pie',
									radius :  [0, '50%'],
									center: ['50%', '64%'],
									itemStyle: {
										normal: {
											label:{
												show:true,
												position:'outer',
												formatter:"{b} : {d}%",
												fontSize: getDpr()
											},
											labelLine:{
												length: getLength(),
												length2: getLength2(),
											}
										}
									},
									data: ${mediaSourceoption_series_data}
								}
							],
							animation:false
						}
						mediaSource.setOption(mediaSourceoption);
					}					
                    		
					//处理没有值时的情况
					if(${mediaActiveoption_series_data}.length == 0){
						$('#mediaActive').html(noData);
					}else{
						var mediaActive = ec.init($('#mediaActive').get(0));                  //   媒体活跃度
						var mediaActiveoption = {
							color:['#00b3e6','#468aca','#0c98ce','#2fc7d0','#34b26c','#f7b61b','#ff7d06','#8959a2','#834f00','#a30101'],
							tooltip : {
								show:true,
								trigger: 'axis',
								axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
								},
								textStyle: {
									fontSize: getDpr()
								},
								confine: true
							},
							grid : {
								borderWidth:0,
								left: getGridX(),
								right:'8',
								bottom: getGridY2()
							},
							xAxis : {
								type : 'category',
								data : ${mediaActiveoption_xAxis_data},
								axisLabel : {
									rotate : 45,
									color : '#333',
									fontStyle : 'normal',
									fontFamily : '微软雅黑',
									// fontWeight : 'lighter',
									fontSize: getDpr(),
									margin : 6,
									formatter: function(value) {
										var subString = "";
										subString = value;
										if(value.length > 4){
											subString = value.substring(0,4)+"…";
										}
										return subString;
									}
								},
								axisLine : {
									show : true,
									lineStyle : {
										color : '#b5b5b5',
										width:0.5
									}
								},
								axisTick:{show:false},
								splitLine:{show:false}
							},
							yAxis : {
								type : 'value',
								axisLine : {
									show : true,
									lineStyle : {
										color : '#b5b5b5',
										width:0.5
									}
								},
								axisLabel : {
									fontSize: getDpr()
								}
							},
							series : [
								{
									name:'条数',
									type:'bar',
									itemStyle: {
										normal: {
											barBorderRadius:[15,15,0,0],
											color: function(params) {
												// build a color map as your need.
												var colorList = [
													'#00b2e6','#458aca','#0b97ce','#2ec5d2','#33b16c',
													'#f9b21f','#ff7c05','#8957a1','#834e00','#a40000'
												];
												return colorList[params.dataIndex];
											}
										}
									},
									barMaxWidth:20,
									data:${mediaActiveoption_series_data},
								}
							],
							animation:false
						};
						mediaActive.setOption(mediaActiveoption);
					}																		
					
					//处理没有值时的情况
					if(${peoplePoint_series_data}.length == 0){
						$('#peoplePoint').html(noData);
					}else{
						var peoplePoint = ec.init($('#peoplePoint').get(0));                //   网民观点
						var peoplPointoption = {
							color:['#fd1925','#6172e1','#ff701d','#03d8f7','#3684e3','#d527e9','#825af6','#ff5d41'],
							tooltip : {
								trigger: 'item',
								formatter: "{a} <br/>{b} : {c} ({d}%)",
								textStyle: {
									fontSize: getDpr()
								},
								confine: true
							},
							legend: {
								orient : 'vertical',
								left : '0px',
								top: 'bottom',
								itemGap: getLegendItemGap4(),
								data: ${peoplePoint_legend_data},
								textStyle: {
									fontSize: getDpr3()
								}
							},
							series : [
								{
									name:'网民观点',
									type:'pie',
									radius :  [0, '50%'],
									center: ['50%', '25%'],
									itemStyle: {
										normal: {
											label:{
												show:true,
												position:'outside',
												formatter:"{d}%",
												fontSize: getDpr()
											},
											labelLine:{
												length: getLength(),
												length2: getLength2()
											}
										}
									},
									data: ${peoplePoint_series_data}
								}
							],
							animation:false
						};
						peoplePoint.setOption(peoplPointoption);
					}
					
                    
                }
            },
            showImgArray:function () {
                //点击下拉
                $('[the-id=imgArray_ul]').removeClass("hide");
            },
            wordsNav: function (index,text) {
                //查询词云图
                this.mark = index;
                this.eventWords.timename = text;
                this.drawTurnCloum();
            },
            blogTab:function(item,index){
                switch (item.id)
                {
                    case 1:
                        this.blogMark = 0;
                        break;
                    case 2:
                        this.blogMark = 1;
                        break;
                    case 3:
                        this.blogMark = 2;
                        break;
                }
            },
            timeTrans:function(time){
                return time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6,8)+" "+time.substring(8,10)+":"+time.substring(10,12)+":"+time.substring(12,14);
            }
        },
        mounted:function () {
            var _this = this;
            //词云图
            _this.drawTurnCloum();
            //绘制图
            _this.drawEchart();
        }
    });
    
	
	$(function() {

        //编辑 展开 收起 功能
        $('.arrow').click(function() {
            if($(this).hasClass('arrowdown')) {
                $(this).siblings('.detail_text').css('height','auto');
                $(this).removeClass('arrowdown').addClass('arrowup');
            } else {
                $(this).siblings('.detail_text').removeAttr('style');
                $(this).removeClass('arrowup').addClass('arrowdown');
            }
        });
        
    });
    //    发展趋势判断时间选择(1天之内按照小时展示数据，1天以上按照天展示数据)
    function lineCharts(type,data) {
        if(type){
            if(type.length <2){
                type[0].hours.forEach(function (item) {
                    data.push(item.num)
                })
            }else{
                type.forEach(function (item) {
                    data.push(item.count)
                })
            }
        }else{
            data = []
        }
    }

	//处理词云图字体大小
	function getSizeRange(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return [12, 30];
	    }else if (dpr == 2) {
	        return  [12, 50];
	    }else {
	        return [12, 90];
	    }
	}
	
	//处理词云图字间距大小
	function getGridSize(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 8;
	    }else if (dpr == 2) {
	        return  16;
	    }else {
	        return 24;
	    }
	}
	
	//处理echarts中图表的字体大小问题
	function getDpr(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 12;
	    }else if (dpr == 2) {
	        return  20;
	    }else {
	        return 32;
	    }
	}
	//饼图提示字调整
	function getDpr2(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 8;
	    }else if (dpr == 2) {
	        return  14;
	    }else {
	        return 28;
	    }
	}
	//网民观点legend字调整
	  function getDpr3(){
	    var dpr = $('html').attr('data-dpr');
	      if (dpr == 1) {
	          return 10;
	      }else if (dpr == 2) {
	          return  20;
	      }else {
	          return 32;
	      }
	  }
	//饼图线长适配--第一根线
	function getLength(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 4;
	    }else if (dpr == 2) {
	        return  5;
	    }else {
	        return 8;
	    }
	}
	//饼图线长适配--第二根线
	function getLength2(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 6;
	    }else if (dpr == 2) {
	        return  10;
	    }else {
	        return 15;
	    }
	}
	
	//dataZoom间距调整
	function getDprZoom(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 65;
	    }else if (dpr == 2) {
	        return  82;
	    }else {
	        return 110;
	    }
	}
	
	//dataZoom高度
	function getDataZoomHeight(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 30;
	    }else if (dpr == 2) {
	        return  40;
	    }else {
	        return 60;
	    }
	}

	//柱状图的x间距调整
	function getGridX(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 55;
	    }else if (dpr == 2) {
	        return  85;
	    }else {
	        return 115;
	    }
	}

	//柱状图的y间距调整
	function getGridY(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 80;
	    }else if (dpr == 2) {
	        return  85;
	    }else {
	        return 130;
	    }
	}

	//柱状图的y2间距调整
	function getGridY2(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 60;
	    }else if (dpr == 2) {
	        return  90;
	    }else {
	        return 140;
	    }
	}

	//legend 的 itemGap 适配
	function getLegendItemGap(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 10;
	    }else if (dpr == 2) {
	        return  18;
	    }else {
	        return 28;
	    }
	}

	//大v分布的legend 的 itemGap 适配
	function getLegendItemGap2(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 4;
	    }else if (dpr == 2) {
	        return  18;
	    }else {
	        return 14;
	    }
	}

	//水军分析的 legend 的 itemGap 适配
	function getLegendItemGap3(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 10;
	    }else if (dpr == 2) {
	        return  18;
	    }else {
	        return 28;
	    }
	}

	//网民观点的 legend 的 itemGap 适配
	function getLegendItemGap4(){
		var dpr = $('html').attr('data-dpr');
	    if (dpr == 1) {
	        return 10;
	    }else if (dpr == 2) {
	        return  24;
	    }else {
	        return 40;
	    }
	}

</script>
</html>