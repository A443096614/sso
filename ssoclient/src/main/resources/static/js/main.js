layui.use([ 'element', 'layer' ], function() {
	var element = layui.element, layer = layui.layer, $ = layui.jquery;

	leftMemu();
	
	//左边菜单栏
	function leftMemu(){
		$.ajax({
			type: "POST",
		    url: "sys/leftMemu",
		    dataType: "json",
		    success: function(result){
				if(result.code == 200){
				}else{
					layer.alert(result.msg);
				}
			}
		});
	}
	
	////修改密码
	$('#updatePwd').on('click', function(){
		layer.open({
			type: 1,
			skin: 'layui-layer-molv',
			title: "修改密码",
			area: ['400px', '270px'],
			shadeClose: false,
			content: $("#passwordLayer"),
			btn: ['修改','取消'],
			btn1: function (index) {
				var data = "oldPwd=" + oldPwd + "&newPwd=" + newPwd + "&checkPwd=" + checkPwd;
				$.ajax({
					type: "POST",
				    url: "sys/updatePwd",
				    data: data,
				    dataType: "json",
				    success: function(result){
						if(result.code == 200){
							layer.alert('修改成功', function(index){
								location.reload();
							});
						}else{
							layer.alert(result.msg);
						}
					}
				});
	        }
		});
	});
});

//退出
function exit(){
	layer.confirm('您确定要退出当前系统？', {
		icon : 3,
		title : '确认',
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		location.href = '/sys/logout';
	});
}
