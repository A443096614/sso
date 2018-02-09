layui.use('form', function() {
	var form = layui.form;
	layer = parent.layer === undefined ? layui.layer : parent.layer,
			$ = layui.jquery;
	
	//登录按钮事件
    form.on("submit(login)", function (data) {
    	var loginLoad = layer.load();
        var datas = "userNo=" + data.field.userNo + "&passWord=" + data.field.passWord + "&captcha=" + data.field.captcha;
        $.ajax({
            type: "POST",
            url: "/sys/login",
            data: datas,
            dataType: "json",
            success: function (result) {
            	layer.close(loginLoad);
            	//关闭
                if (result.code == '200') {//登录成功
                	layer.msg(result.msg,{icon:1,offset:'t'}, function(){
                		location.href = '/main';
                    });
                } else {
                    layer.msg(result.msg, {icon: 5, offset:'t'});
                    refreshCode();
                }
            }
        });
        return false;
    });
});
//验证码
function refreshCode() {
	var captcha = document.getElementById("captcha");
	captcha.src = "/defaultKaptcha?t=" + new Date().getTime();
}
