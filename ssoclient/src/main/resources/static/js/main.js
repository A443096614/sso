layui.config({
  base: '/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use('menu'); //加载菜单
layui.use([ 'element', 'layer' ], function() {
	var element = layui.element, layer = layui.layer, $ = layui.jquery;

	// 监听菜单导航点击
	element.on('nav(navTopRight)', function(elem) {
		var id = $(elem).attr("id");
		if (id == 'sysOut') {//退出
			layer.confirm('您确定要退出当前系统？', {
				icon : 3,
				title : '确认',
				btn : [ '确定', '取消' ]
			// 按钮
			}, function() {
				location.href = '/sys/logout';
			});
		} else if(id == 'sysUpPwd'){// 修改密码
			layer.open({
				type : 2,
				skin : 'layui-layer-molv',
				title : "<i class='fa fa-lock'></i> 修改密码",
				area : [ '350px', '270px' ],
				shadeClose : false,
				content : ['/updatePwd', 'no']
			});
		}
	});
});
