<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>购物车</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			.container .row div {
				/* position:relative;
	 float:left; */
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>

	<body>

		
		<%@ include file="header.jsp" %>

		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">订单详情</strong>
					<table class="table table-bordered">
						<tbody id="cart">
							<tr class="warning">
								<th>订单编号</th>
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							
								
							<script type="text/javascript">
								<c:if test="${empty user }">
									<% String url = request.getContextPath()+"/jsp/login.jsp"; %>
									<% response.sendRedirect(url); %>
								</c:if>
								<c:if test="${not empty user }">
									var count = $("#quantity").val();
									var model="<tr class=\"active\">"+
									"<td width=\"100\">"+
									"<input type=\"text\" disabled id=\"id{index}\" value=\"{orderId}\">"+
									"</td>"+
									"<td width=\"60\" width=\"40%\">"+
									"<img src=\"${pageContext.request.contextPath}/{pimage}\""+
									"width=\"70\" height=\"60\">"+
									"</td>"+
									"<td width=\"30%\">"+
										"<a target=\"_blank\">{pname}</a>"+
									"</td>"+
									"<td width=\"20%\"id=\"price{index}\">{shop_price}"+
									"</td>"+
									"<td width=\"10%\">"+
										"<input type=\"text\" name=\"quantity\" id=\"count{index}\" value=\"{count}\""+
										"maxlength=\"4\" size=\"10\">"+
									"</td>"+
									"<td width=\"15%\">"+
										"<span class=\"subtotal\" id=\"total{index}\">{total}</span>"+
									"</td>"+
									"<td>"+
										"<a href=\"<%=path%>/product/delorder.do?orderId={orderId}\" class=\"delete\">删除</a>"+
									"</td>"+
								"</tr>";
									$.ajax({
										url:"<%=path%>/product/getcart.do",
										success:function(data){
											var json = eval('('+data+')');
											var length = json.cart.length;
											var t_total=0;
											$.each(json.cart,function(index,value){
												var total = value.shop_price*value.count;
												t_total+=total;
												var oneOrder = model.replace(/{pimage}/g, value.pimage)
												.replace(/{pname}/g, value.pname)
												.replace(/{shop_price}/g, value.shop_price)
												.replace(/{count}/g,value.count)
												.replace(/{total}/g, total)
												.replace(/{index}/g, index)
												.replace(/{orderId}/g, value.orderId)
												.replace(/{pid}/g, value.pid);
												$("#cart").append(oneOrder);
												document.getElementById('count'+index).onchange = function() {         
													//动态添加事件，若在每条数据的后面要添加件事传参，此方式可传一个对象过去
													change(index,length);  //调用这个方法，把对象传过去，
												}
												
											})
											$("#gift").text(t_total);
											$("#t_total").text(""+t_total+".00");
										}
									})
								</c:if>	
								function change(index,length){
									var total = $("#price"+index).text()*$("#count"+index).val();
									$("#total"+index).text(total);
									var t_total=0;
									for(var i=0;i<length;i++){
										t_total+=parseFloat($("#total"+i).text());
									}
									$("#gift").text(t_total);
									$("#t_total").text("￥"+t_total+".00");
								}						
							</script>
							
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					<em style="color:#ff6600;">
				登录后确认是否享有优惠&nbsp;&nbsp;
			</em> 赠送积分: <em style="color:#ff6600;" id="gift"></em>&nbsp; 商品金额: <strong style="color:#ff6600;" id="t_total"></strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="order_info.htm" id="clear" class="clear">清空购物车</a>
					<a href="order_info.jsp">
						<input type="submit" width="100" value="提交订单" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</a>
				</div>
			</div>

		</div>

		<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2017
		</div>

	</body>

</html>