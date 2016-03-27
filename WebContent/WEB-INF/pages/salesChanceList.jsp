<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>销售机会管理</title>
	<script type="text/javascript">
		$(function(){
			//新建的 JS 代码. 
			$("#new").click(function(){
				alert("nimei");
				window.location.href = "${ctp}/chance";
			});
			
			//删除的 JS 代码
			$(".delete").click(function(){
				var custName = $(this).next(":hidden").val();
				var flag = confirm("确定要删除" + custName + "的信息吗?");
				
				var $form = $("#hiddenForm");
				var id = $(this).prev(":hidden").val();
				$("#_method").val("DELETE");
				$form.attr("action", "${ctp}/chance/" + id).submit();
				
				return false;
			});
		})
	</script>
</head>

<body class="main">
	<form action="" method="POST" id="hiddenForm">
		<input type="hidden" name="_method" value="" id="_method"/>
	</form>

	<form id="command" action="${ctp}/chance/list" method="post">
		<div class="page_title">
			销售机会管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">
				新建
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
	</form>
	<!-- 列表数据 -->
	<br />
	<c:if test="${empty page.content }">
		没有任何数据
	</c:if>
	<c:if test="${!empty page.content }">
	
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<th>
					客户名称
				</th>
				<th>
					概要
				</th>
				<th>
					联系人
				</th>
				<th>
					联系人电话
				</th>
				<th>
					创建时间
				</th>
				<th>
					操作
				</th>
			</tr>
			<c:forEach items="${page.content }" var="item">
				<tr>
					<td class="list_data_number">${item.id }</td>
					<td class="list_data_text">${item.custName }</td>
					<td class="list_data_text">${item.title }</td>
					<td class="list_data_text">${item.contact }</td>
					<td class="list_data_text">${item.contactTel }</td>
					<td class="list_data_text">
						<fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd"/>
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctp}/chance/dispatch?id=150'" 
							title="指派" src="${ctp}/static/images/bt_linkman.gif" class="op_button" />
						<img onclick="window.location.href='${ctp}/chance/create?id=150'" 
							title="编辑" src="${ctp}/static/images/bt_edit.gif"
							class="op_button" />
						
						<input type="hidden" value="${item.id }"/>
						<img title="删除" src="${ctp}/static/images/bt_del.gif" class="delete" />
						<input type="hidden" value="${item.custName }"/>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>	

<div style="text-align:right; padding:6px 6px 0 0;">

	共 ${page.totalCount } 条记录 
	&nbsp;&nbsp;
	
	当前第 ${page.pageNo }页/共 ${page.totalPageNo } 页
	&nbsp;&nbsp;
	
	<c:if test="${page.hasPrev }">
		<a href='?pageNo=1'>首页</a>
		&nbsp;&nbsp;
		<a href='?pageNo=${page.prevPage }'>上一页</a>
		&nbsp;&nbsp;
	</c:if>
	
	 <c:if test="${page.hasNext }">
		<a href='?pageNo=${page.nextPage }'>下一页</a>
		&nbsp;&nbsp;
		<a href='?pageNo=${page.totalPageNo }'>末页</a>
		&nbsp;&nbsp;
	</c:if>
	
	转到 <input id="pageNo" size='1'/> 页
	&nbsp;&nbsp;

</div>

<script type="text/javascript">

	$(function(){
		$("#pageNo").change(function(){
			
			var pageNo = $(this).val();
			var reg = /^\d+$/;
			if(!reg.test(pageNo)){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			var pageNo2 = parseInt(pageNo);
			if(pageNo2 < 1 || pageNo2 > parseInt("${page.totalPageNo }")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname + "?pageNo=" + pageNo2;
		});
	})
</script>
</body>
</html>
