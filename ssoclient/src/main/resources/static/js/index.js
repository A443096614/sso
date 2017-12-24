layui.use([ 'element', 'layer' ], function() {
	var element = layui.element, layer = layui.layer;
	// 监听导航点击
	element.on('nav(navRight)', function(elem) {
		var text = elem.text().trim();
		if (text === '退出') {
			layer.confirm('您确定要退出当前系统？', {
				icon : 3,
				title : '确认',
				btn : [ '确定', '取消' ]
			// 按钮
			}, function() {
				parent.location.href = '/sys/logout';
			});
		}
	});
});
