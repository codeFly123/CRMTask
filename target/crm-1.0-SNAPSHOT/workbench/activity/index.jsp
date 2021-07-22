<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

<script type="text/javascript">

	$(function(){

		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});

		$("#addBtn").click(function(){

			alert("1234568646")



		$.ajax({
			url:"workbench/activity/getUserList.do",
			dataType:"json",
			data:{},
			type:"get",
			success:function(data){

				var html="<option></option>";
				$.each(data,function(i,n){
					html+="<option value='"+n.id+"'>"+n.name+"</option>";
				})

				$("#create-Owner").html(html);



				$("#create-Owner").val("${sessionScope.user.id}");

				$("#createActivityModal").modal("show")
			}

		})

		})

		//保存数据；
		$("#save").click(function(){
			if(confirm("确定要保存么？")) {
				$.ajax({
					url: "workbench/activity/save.do",
					data: {
						"OWNER": $.trim($("#create-Owner").val()),
						"NAME": $.trim($("#create-marketActivityName").val()),
						"startDate": $.trim($("#create-startTime").val()),
						"endDate": $.trim($("#create-endTime").val()),
						"cost": $.trim($("#create-cost").val()),
						"description": $.trim($("#create-describe").val())
					},
					dataType: "json",
					type: "post",
					success: function (data) {

						if (data.success) {
							pageList(1, $("#activePage").bs_pagination('getOption', 'rowsPerPage'));
							// alert("12345645654465656599999999999999999!!!");
							//表单清空；
							$("#ActivityAddForm")[0].reset();
							//document.getElementById("createActivityModal").reset();

							//alert("成功！")
							$("#createActivityModal").modal("hide");

						} else {
							alert("添加数据失败！")

						}
					}


				})
			}

		})



        //活动列表查询；
		$(".searchTime").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});

		pageList(1,4);
		$("#search-Btn").click(function(){

			$("#hidden-name").val($.trim($("#search-name").val()))
			$("#hidden-owner").val($.trim($("#search-owner").val()))
			$("#hidden-startDate").val($.trim($("#search-startDate").val()))
			$("#hidden-enddate").val($.trim($("#search-endDate").val()))

            pageList(1,4);
        })

		//复选框全选；
		$("#qx").click(function(){
			$("input[name=zx]").prop("checked",this.checked)
		})

		//全选复选框反选全选键；
		$("#activity-tbody").on("click",$("input[name=zx]"),function(){
			$("#qx").prop("checked",$("input[name=zx]").length===$("input[name=zx]:checked").length)

		})

		//删除记录；
		$("#deleteBtn").click(function(){
			var $zx=$("input[name=zx]:checked")
			if($zx.length==0){
				alert("请选择要啥出的记录！")
			}else{
				var param="";
				for(var i=0;i<$zx.length;i++){
					param+="id="+$($zx[i]).val()
					if(i<$zx.length-1){
						param+="&"
					}
				}
				alert(param)
				alert("123456789")

				if(confirm("确定删除么？")){
					$.ajax({
						url: "workbench/activity/delete.do",
						data: param,
						dataType: "json",
						type: "post",
						success: function (data) {
							if (data.success) {
								alert("删除成功！")
                                //重刷新；
                                //更新后，在哪一页还回到哪一页
                                //关键代码
                                pageList(1,$("#activePage").bs_pagination('getOption', 'rowsPerPage'));
								//pageList(1, 4);
							} else {
								alert("删除市场活动失败！")

							}
						}
					})
				}
			}
		})

//修改记录分功能--所有者查询；
		$("#updateBtn").click(function(){
			//将所选数据显示在修改页面上；



			$.ajax({
			url: "workbench/activity/getUserList.do",
			dataType: "json",
			data: {},
			type:"get",
			success: function (data) {

				var html = "<option></option>";
				$.each(data, function (i, n) {
					html += "<option value='" + n.id + "'>" + n.name + "</option>";
				})

				$("#edit-Owner").html(html);



				$("#editActivityModal").modal("show")
			}
			})


				$.ajax({
					url:"workbench/activity/update-select.do",
					dataType:"json",
					type:"get",
					data:{
						"$zxId":$("input[name=zx]:checked").val()
					},
					success: function(data){
						//alert(data.owner+"123123123123123123123")
						//var datass=eval(data)
						//alert(data.length)
						//alert(datass.length)
						//alert(data[0].name)
						var data1=data[0]
						alert(data1.owner)

						$("#edit-Owner").val(data1.owner)
						$("#edit-name").val(data1.name)
						$("#edit-startDate").val(data1.startDate)
						$("#edit-endDate").val(data1.endDate)
						$("#edit-cost").val(data1.cost)
						$("#edit-describe").val(data1.description)
					}

				})









		})
		//修改记录功能；
		$("#update").click(function () {

			if(confirm("确定要修改？")){
				$.ajax({
					url:"workbench/activity/update.do",
					data:{
						"id":$("input[name=zx]:checked").val(),
						"OWNER":$.trim($("#edit-Owner").val()),
						"NAME":$.trim($("#edit-name").val()),
						"startDate":$.trim($("#edit-startDate").val()),
						"endDate":$.trim($("#edit-endDate").val()),
						"cost":$.trim($("#edit-cost").val()),
						"description":$.trim($("#edit-describe").val())
					},
					dataType:"json",
					type:"post",
					success:function(data){

						if(data.success){

							alert("12345645654465656599999999999999999!!!");
							//表单清空；
							$("#ActivityUpdateForm")[0].reset();
							//document.getElementById("createActivityModal").reset();

							//alert("成功！")
							$("#editActivityModal").modal("hide");
							//重刷新；
                        //更新后，在哪一页还回到哪一页
                            //关键代码
                            pageList($("#activePage").bs_pagination('getOption', 'currentPage')
                                ,$("#activePage").bs_pagination('getOption', 'rowsPerPage'));
							//pageList(1,4);

						}else{
							alert("修改数据失败！")

						}
					}


				})
			}























		})






	});

	//分页操作功能；
	function pageList(pageNo,pageSize){

		//将全选键取消掉；
		$("#qx").prop("checked",false)


		$("#search-name").val($.trim($("#hidden-name").val()))
		$("#search-owner").val($.trim($("#hidden-owner").val()))
		$("#search-startDate").val($.trim($("#hidden-startDate").val()))
		$("#search-endDate").val($.trim($("#hidden-enddate").val()))
		//alert("进入市场列表！")

	    $.ajax({
            url:"workbench/activity/pageList.do",
			data:{

				"pageNo": pageNo,
				"pageSize": pageSize,
				"name":$.trim($("#search-name").val()) ,
				"owner":$.trim($("#search-owner").val()) ,
				"startDate":$.trim($("#search-startDate").val()) ,
				"endDate":$.trim($("#search-endDate").val())

			},
            dataType:"json",
            type:"get",
			//contentType:"application/json",
            success:function(data){

                    var html="";
                    /*
                    * data:[total:100,dataList:[{},{},...]    ]
                    * */
                    $.each(data.dataList,function(i,n){
                    	//alert(n.id)

                        //多行拼接；
                     html+='<tr class="active">';
                     html+='<td><input type="checkbox" name="zx" value="'+n.id+'"/></td>';
                     html+='<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/activity/detail.do?id='+n.id+'\';">'+n.name+'</a></td>';
                     html+='<td>'+n.owner+'</td>';
                     html+='<td>'+n.startDate+'</td>';
                     html+='<td>'+n.endDate+'</td>';
                     html+='</tr>';

                    })

					$("#activity-tbody").html(html)

                   // $("#activitySearchForm")[0].reset();

                //分页插件；
				const totalPages = (data.total % pageSize === 0 ? data.total / pageSize : (parseInt(data.total / pageSize) + 1));

				$("#activePage").bs_pagination({
					currentPage: pageNo, // 页码
					rowsPerPage: pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: totalPages, // 总页数
					totalRows: data.total, // 总记录条数

					visiblePageLinks: 3, // 显示几个卡片

					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,

					onChangePage: function (event, data) {
						pageList(data.currentPage, data.rowsPerPage);
					}

				});

			}

		})

	}

</script>
</head>
<body>

       <input type="hidden" id="hidden-name" />
	   <input type="hidden" id="hidden-owner" />
	   <input type="hidden" id="hidden-startDate" />
	   <input type="hidden" id="hidden-endDate" />

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="ActivityAddForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-Owner" class="col-sm-2 control-label" >所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-Owner">

								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label" >名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-marketActivityName">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startTime" value="开始日期" readonly>
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endTime" value="结束日期" readonly>
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="save">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="ActivityUpdateForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-Owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-Owner">

								</select>
							</div>
                            <label for="edit-name" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-name" value="">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startDate" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-startDate" value="" readonly>
							</div>
							<label for="edit-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-endDate" value="" readonly>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="update">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" id="activitySearchForm" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="search-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="search-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control searchTime" type="text" id="search-startDate"  readonly>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control searchTime" type="text" id="search-endDate"  readonly>
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="search-Btn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="updateBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx" /></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activity-tbody">

					</tbody>
				</table>
			</div>

			<div style="height: 50px; position: relative;top: 30px;" >
				<!--分页插件-->
				<div id="activePage"></div>

			</div>
		</div>
	</div>
</body>
</html>