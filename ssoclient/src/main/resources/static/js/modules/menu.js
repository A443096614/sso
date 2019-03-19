/**
 * 项目JS菜单入口
 */
layui.define([ 'element','layer' ], function(exports) {
	var element = layui.element, layer = parent.layer === undefined ? layui.layer : parent.layer,
			$ = layui.jquery;
	
	element.on('nav(navTopLeft)', function(elem) {
		var roleId = $(elem).attr("id");
		//获取前端缓存
		var menuList = JSON.parse(sessionStorage.getItem("menuList"));
		var menus = menuList[roleId];
		//显示菜单
        setMenu(menus);
	});
	
	// 头部区域菜单栏
	$.getJSON('/sys/topMenu', function (result) {
		var roleList = result.roleList;
		for (var i = 0; i < roleList.length; i++) {
			var _li;
			if (i == 0) {
				var roleId = 'roldid'+roleList[i].id;
				_li = ['<li class="layui-nav-item layui-this" id=' + roleId + '>',
					'<a href="javascript:;">' + roleList[i].rolename + '</a>',
					'</li>'].join("");
				// 左边菜单栏
				$.getJSON('sys/leftMemu', function (result) {
					var menus = result.menuList[roleId];
					//设置前端菜单缓存
			        sessionStorage.setItem("menuList", JSON.stringify(result.menuList));
			        //显示菜单
			        setMenu(menus);
			    });
			} else {
				_li = ['<li class="layui-nav-item" id=roldid' + roleList[i].id + '>',
					'<a href="javascript:;">' + roleList[i].rolename + '</a>',
					'</li>'].join("");
			}
	        $("#topMenu").append(_li);
		}
		element.render();
    });
	
	//设置菜单
	function setMenu(menus){
		$(".layui-nav-tree").html("");
	    for (var i = 0; i < menus.length; i++) {
	        var _li;
	        if (menus[i].type === '1') {
	            _li = ['<li class="layui-nav-item">',
	                '<a class="" href="javascript:;" title="' + menus[i].name + '" >',
	                '<i class="' + menus[i].icon + '"></i>' + menus[i].name + '</a>',
	                '</li>'].join("");
	            //是否有下级菜单
	            var childrens = menus[i].children;
	            if (childrens) {
	                var $li = $(_li);
	                $li.find("a").after('<dl class="layui-nav-child">');
	                for (var j = 0; j < childrens.length; j++) {
	                    $li.find(".layui-nav-child").append(' <dd><a href="javascript:;" data-url="' + childrens[j].url + '" title="' + childrens[j].name + '">' + childrens[j].name + '</a></dd>');
	                }
	            }
	            _li = $li.prop("outerHTML");
	        }
	        $(".layui-nav-tree").append(_li);
	    }
	    element.render();
	}
	
	//菜单单击事件
	element.on('nav(navMenuTree)', function(elem) {
		var elem = $(elem);//.attr("id");
		var a = $(elem.context.firstChild);
		var title = a.attr("title");
		var url = a.attr("data-url");
		
		var layId = elem.getAttribute('lay-id');
		console.info(layId);
		
		//新增一个Tab项
		element.tabAdd('myTabs', {
			id: 1,
			title: title, //用于演示
			content: '<iframe src="https://www.2345.com/"></iframe>'
		});
		element.tabChange('myTabs', 1); 
	});
	
	//左侧菜单显示与隐藏 
	$('.leftMemu-collapse').on('click', function() {
	    var sideWidth = $('#leftMemu-side').width();  
	    if(sideWidth === 200) {
	        $('#admin-body').animate({left: '0'});
	        $('#admin-footer').animate({left: '0'});
	        $('#leftMemu-side').animate({width: '0'});  
	        $(this).addClass("leftMemu-show").animate({left: '0px'});
	    } else {
	    	$('#admin-body').animate({left: '200px'});
	        $('#admin-footer').animate({left: '200px'});
	        $('#leftMemu-side').animate({width: '200px'});
	        $(this).removeClass("leftMemu-show").animate({left: '200px'});
	    }
	});
	
	exports('menu', {}); // 注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});