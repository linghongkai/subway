

<!DOCTYPE html>
<html>
<head>
<title>地铁信息查询</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
/*设置超链接样式*/
table {
	border-collapse: collapse;
	border-spacing: 0;
}

a {
	color: #5086a5;
	text-decoration: none;
	font-size: 12px;
}

a:hover {
	color: #5086a5;
	text-decoration: underline;
	font-size: 12px;
}

a:visited {
	color: #5086a5;
	font-size: 12px;
}
/*整个tab层居中，宽度为600px*/
#tabDiv {
	width: 600px;
	margin: 1em auto;
	padding-bottom: 10px;
	border-right: #b2c9d3 1px solid;
	border-top: #b2c9d3 1px solid;
	border-left: #b2c9d3 1px solid;
	border-bottom: #b2c9d3 1px solid;
	background: #ffffff;
}
/*tab头的样式*/
#tabsHead {
	padding-left: 0px;
	height: 26px;
	background-color: #e8f7fc;
	font-size: 1em;
	margin: 1px 0px 0px;
	color: #5086a5;
	line-height: 26px;
}
/*已选tab头（超链接）的样式*/
.curtab {
	padding-top: 0px;
	padding-right: 10px;
	padding-bottom: 0px;
	padding-left: 10px;
	border-right: #b2c9d3 1px solid;
	font-weight: bold;
	float: left;
	cursor: pointer;
	background: #ffffff;
}
/*未选tab头（超链接）的样式*/
.tabs {
	border-right: #c1d8e0 1px solid;
	padding-top: 0px;
	padding-right: 10px;
	padding-bottom: 0px;
	padding-left: 10px;
	font-weight: normal;
	float: left;
	cursor: pointer;
}
p {
	font-size: 12pt;
	text-indent: 2em;
}
li {
  	border-bottom-style: solid;
  	border-bottom-color: #EEE;
  	border-bottom-width: thin;
  	height: 25px;
	font-family: "宋体";
	font-size: 12pt;
}
</style>

<script type="text/jscript">	
        //显示tab（tabHeadId：tab头中当前的超链接；tabContentId要显示的层ID）
        function showTab(tabHeadId,tabContentId) 
        {
            //tab层
            var tabDiv = document.getElementById("tabDiv");
            //将tab层中所有的内容层设为不可见
            //遍历tab层下的所有子节点
            var taContents = tabDiv.childNodes;
            for(i=0; i<taContents.length; i++) 
            {
                //将所有内容层都设为不可见
                if(taContents[i].id!=null && taContents[i].id != 'tabsHead')
                {
                    taContents[i].style.display = 'none';
                }
            }
            //将要显示的层设为可见
            document.getElementById(tabContentId).style.display = 'block';          
            //遍历tab头中所有的超链接
            var tabHeads = document.getElementById('tabsHead').getElementsByTagName('a');
            for(i=0; i<tabHeads.length; i++) 
            { 
                //将超链接的样式设为未选的tab头样式
                tabHeads[i].className='tabs'; 
            }
            //将当前超链接的样式设为已选tab头样式
            document.getElementById(tabHeadId).className='curtab';
            document.getElementById(tabHeadId).blur();
        }
</script>
</head>

<body>
	<div style="width: 100%; font-family: 微软雅黑; text-align: center; font-size: 20pt;">地铁信息查询</div>

	<div id="tabDiv" style="width: 1000px">

		<div id="tabsHead">
			<a id="tabs1" class="curtab" href="javascript:showTab('tabs1','tabContent1')">起点-终点查询</a> <a id="tabs2" class="tabs" href="javascript:showTab('tabs2','tabContent2')">线路查询</a> <a id="tabs3" class="tabs" href="javascript:showTab('tabs3','tabContent3')">站点查询</a>
		</div>

		<div id="tabContent1" style="display: block">
			<table style="border-width: 0; width: 100%">
				<tr>
					<td colspan="3" rowspan="3">
						<form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
							<div>
								换乘查询
							</div>
							<div class="form-group">
								<label for="origin">出发地</label>
								<input type="text" name="origin" class="form-control" id="origin" >
							</div>
							<div class="form-group">
								<label for="distination">目的地</label>
								<input type="text" name="distination" class="form-control" id="distination" >
							</div><br>
							<a href="http://localhost:8080/subwayInfo/queryRoute?startSiteId=8810&endSiteId=8813">查询</a><br/>
							<button type="submit" class="btn btn-default">查询</button>
						</form>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
			</table>
		</div>
		<!--以下为获奖记录部分内容-->
		<div id="tabContent2" style="display: none">
			<form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
				<div>
					线路查询
				</div>
				<div class="form-group">
					<label for="xianlumingcheng">线路名称</label>
					<input type="text" name="xianlumingcheng" class="form-control" id="xianlumingcheng" >
				</div>
				<div class="form-group">
					<label for="xianlumingcheng1">线路名称：</label>
					<select name="xianlumingcheng1" class="form-control" id="xianlumingcheng1">
						<option value="1号线">1号线</option>
						<option value="2号线">2号线</option>
						<option value="3号线">3号线</option>
					</select><br>
				</div>

				<button type="submit" class="btn btn-default">查询</button>
			</form>
		</div>
		<div id="tabContent3" style="display: none">
			<form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
				<div>
					站点查询
				</div>
				<div class="form-group">
					<label for="zhandianmingcheng">站点名称</label>
					<input type="text" name="zhandianmingcheng" class="form-control" id="zhandianmingcheng" >
				</div>
				<div class="form-group">
					<label for="zhandianfenlei">站点分类：</label>
					<select name="zhandianmingcheng" class="form-control" id="zhandianfenlei">
						<option value="0">0</option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
					</select>
				</div>
			<div class="form-group">
				<label for="zhandianmingcheng1">站点名称：</label>
				<select name="zhandianmingcheng1" class="form-control" id="zhandianmingcheng1">

					<option value="1号线">1号线</option>
					<option value="2号线">2号线</option>
					<option value="3号线">3号线</option>
				</select><br>
			</div>
				<a href="http://localhost:8080/subwayInfo/querySubway/8816">查询</a><br/>

				<button type="submit" class="btn btn-default">查询</button>
			</form>
		</div>
	</div>
	<hr />

</body>
</html>
