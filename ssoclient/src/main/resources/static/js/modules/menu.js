/**
 * 项目JS菜单入口
 */
layui.define([ 'element' ], function(exports) {
	var element = layui.element, layer = parent.layer === undefined ? layui.layer : parent.layer,
			$ = layui.jquery;

	// 左边菜单栏
	var leftMenuHtml = '';
	$.ajax({
		type : "POST",
		url : "sys/leftMemu",
		dataType : "json",
		success : function(result) {
			if (result.code == 200) {
				var navs = result.roleList;
				$.each(navs,function(i,item){
					leftMenuHtml += '<dl>';  
					leftMenuHtml += '<dt><a href="javascript:;" data-url="'+item.url+'" nav-id="'+item.id+'"><i class="'+item.icon+'"></i><cite>'+item.title+'</cite></a></dt>';  
		            //如果有第二级菜单  
		            if(item.children !== undefined && item.children.length > 0){  
		                $.each(item.children,function(j,item2){  
		                	leftMenuHtml += '<dd>';  
		                	leftMenuHtml += '<a href="javascript:;" data-url="'+item2.url+'" nav-id="'+item2.id+'"><i class="'+item2.icon+'"></i><cite>'+item2.title+'</cite></a>';  
		                    //如果有三级菜单  
		                    if(item2.children !== undefined && item2.children.length > 0){  
		                    	leftMenuHtml += '<ul>';  
		                        $.each(item2.children,function(k,item3){  
		                            html += '<li>'+  
		                                        '<a href="javascript:;" data-url="'+item3.url+'" nav-id="'+item3.id+'">'+  
		                                            '<i class="'+item3.icon+'"></i>'+  
		                                            '<cite>'+item3.title+'</cite>'+  
		                                        '</a>'+  
		                                    '</li>';  
		                        });  
		                        leftMenuHtml += '</ul>';  
		                    }  
		                    leftMenuHtml += '</dd>';  
		                });  
		            }  
		            leftMenuHtml += '</dl>';  
		        });  
		        //渲染html  
		        $('#leftMemu-navbar-side').html(leftMenuHtml); 
			} else {
				layer.alert(result.msg);
			}
		}
	});
	
	// 顶部菜单栏
	/*var html = '';
	$.ajax({  
	    url:"/sys/topMenu",  
	    type:"post",  
	    dataType:"json",  
	    data:{},  
	    success:function(data){
	    	if (result.code == 200) {
				
			} else {
				layer.alert(result.msg);
			}
	    }  
	});*/
	
	//左侧菜单显示与隐藏  
	$('.switchMenu').on('click', function() {
		console.info("ff")
	    var sideWidth = $('#leftMemu-side').width();  
	    if(sideWidth === 200) {  
	        $('#admin-body').animate({  
	            left: '0'  
	        });
	        $('#admin-footer').animate({  
	            left: '0'  
	        });  
	        $('#leftMemu-side').animate({  
	            width: '0'  
	        });  
	    } else {  
	        $('#admin-body').animate({  
	            left: '200px'  
	        });  
	        $('#admin-footer').animate({  
	            left: '200px'  
	        });  
	        $('#leftMemu-side').animate({  
	            width: '200px'  
	        });  
	    }  
	});
	
	exports('menu', {}); // 注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});