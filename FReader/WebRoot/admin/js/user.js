$('.confirm a').click(function(){
	var $td = $(this).parent().parent().parent().children(),
		id = $td.eq(0).text().trim(),
		name = $td.eq(1).find('input').val().trim(),
		pwd = $td.eq(2).find('input').val().trim();
	if(id=='' || name=='' || pwd==''){
		alert('用户名和密码不能为空');
		return ;
	}
	$.ajax({
		type:"get",
		url:"/DinResSys2/admin/user!changeUserInfo",
		async:true,
		dataType:'json',
		data:{
			'user.id': id,
			'user.name': name,
			'user.password': pwd
		},
		success:function(data){
			if(parseInt(data.status)==1){
				alert('修改成功');
				location.href = '/DinResSys2/admin/user!getAllUser';
			}else{
				alert('修改失败');
				location.href = '/DinResSys2/admin/user!getAllUser';
			}
		}
	})
})
