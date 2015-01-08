$('.order-status').change(function(){
	var id = $(this).parent().parent().children().eq(0).text();
	var status = $(this).find('option:selected').val();
	$.ajax({
		type:"get",
		url:"/DinResSys2/admin/order!changeStatus",
		async:true,
		dataType:'json',
		data:{
			'order.id': id,
			'order.status': status
		},
		success:function(data){
			if(parseInt(data.status)==1){
				alert('修改成功')
			}else{
				alert('修改失败');
			}
		}
	})
})
