<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
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
										"<input type=\"text\" name=\"quantity\" disabled id=\"count{index}\" value=\"{count}\""+
										"maxlength=\"4\" size=\"10\">"+
									"</td>"+
									"<td width=\"15%\">"+
										"<span class=\"subtotal\" id=\"total{index}\">{total}</span>"+
									"</td>"+
								"</tr>";
								var length;
									$.ajax({
										url:"<%=path%>/product/getcart.do",
										success:function(data){
											var json = eval('('+data+')');
											length = json.cart.length;
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
											})
											$("#t_total").text(t_total);
										}
									})
								</c:if>						
							</script>
							
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-left:800px;" >
					商品金额: <strong style="color:#ff6600;" id="t_total"></strong>
			</div>
			
			
			<div>
				<hr/>
				<form class="form-horizontal" style="margin-top:5px;margin-left:150px;">
					<div class="form-group">
						<label for="username" class="col-sm-1 control-label">地址</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="address" placeholder="请输入收货地址">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-1 control-label">收货人</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="name" placeholder="请输收货人">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-1 control-label">电话</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="tel" placeholder="请输入联系方式">
						</div>
					</div>
				</form>

				<hr/>

				<div style="margin-top:5px;margin-left:150px;">
					<strong>选择银行：</strong>
					<p>
						<br/>
						<input type="radio" name="pd_FrpId" value="工商银行" checked="checked" />工商银行
						<img src="${pageContext.request.contextPath}/bank_img/icbc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="中国银行" />中国银行
						<img src="${pageContext.request.contextPath}/bank_img/bc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="农业银行" />农业银行
						<img src="${pageContext.request.contextPath}/bank_img/abc.bmp" align="middle" />
						<br/>
						<br/>
						<input type="radio" name="pd_FrpId" value="交通银行" />交通银行
						<img src="${pageContext.request.contextPath}/bank_img/bcc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="平安银行" />平安银行
						<img src="${pageContext.request.contextPath}/bank_img/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="建设银行" />建设银行
						<img src="${pageContext.request.contextPath}/bank_img/ccb.bmp" align="middle" />
						<br/>
						<br/>
						<input type="radio" name="pd_FrpId" value="光大银行" />光大银行
						<img src="${pageContext.request.contextPath}/bank_img/guangda.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="招商银行" />招商银行
						<img src="${pageContext.request.contextPath}/bank_img/cmb.bmp" align="middle" />

					</p>
					<hr/>
					<p style="text-align:right;margin-right:100px;">
						<a href="javascript:void(0)" onclick="addinfo()">
							<img src="${pageContext.request.contextPath}/images/finalbutton.gif" width="204" height="51" border="0" />
						</a>
					</p>
					<hr/>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function addinfo(){
				var t_total = $("#t_total").text();
				var pay_way = $("input[name='pd_FrpId']:checked").val();
				var address = $("#address").val();
				var name = $("#name").val();
				var tel = $("#tel").val();
				var orderId = new Array();
				for(var i = 0;i<length;i++){
					orderId[i]=$("#id"+i).val();
				}
				var json = JSON.stringify(orderId);
				$.ajax({
					url:"<%=path%>/product/addinfo.do",
					data:{
						"t_total":t_total,
						"pay_way":pay_way,
						"address":address,
						"name":name,
						"tel":tel,
						"orderId":json
					},
					success:function(data){
						if(data==1){
							alert("支付成功，您的订单已提交");
						}else{
							alert("支付失败，请重试");
						}
					}
				})
				
			}
		</script>
		
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
			Copyright &copy; 200
		</div>

	</body>

</html>