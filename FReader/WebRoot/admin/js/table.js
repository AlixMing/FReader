//点击新增
$('.add a').on('click',function(){
	var now = new Date();
	$('#start-date').val(now.Format('yyyy-MM-dd'));
	$('#start-time').val(now.Format('hh:mm'));
	$('#addNewActivityModel').modal('show');
	$('#addNewActivityModel').data('id', '');
})

//点击修改
$('.change a').on('click',function(){
	var $td = $(this).parent().parent().parent().children();
	$('#addNewActivityModel').data('id', $td.eq(0).text());
	$('#activity-name').val($td.eq(1).text());
	$('#activity-describe').val($td.eq(2).text());
	var startDate = $td.eq(3).text().split(' ')[0],
		startTime = $td.eq(3).text().split(' ')[1],
		endDate = $td.eq(4).text().split(' ')[0],
		endTime = $td.eq(4).text().split(' ')[1];
	$('#start-date').val(startDate);
	$('#start-time').val(startTime);
	$('#end-date').val(endDate);
	$('#end-time').val(endTime);
	$('#promotion-num').val($td.eq(5).text());
	$('#addNewActivityModel').modal('show');
})

//点击模态框的提交按钮
$('.commit-activity').on('click', function(){
	var activityName = $('#activity-name').val(),
		describe = $('#activity-describe').val(),
		beginTime = $('#start-date').val()+" "+$('#start-time').val(),
		endTime = $('#end-date').val()+" "+$('#end-time').val(),
		promotion = $('#promotion-num').val();
		
	if(activityName=='' || describe=='' || beginTime=='' || endTime=='' || promotion==''){
		alert('所有项不能为空');
		return;
	}
	var id = $('#addNewActivityModel').data('id');
	if(id==''){
		$.ajax({
			type:"get",
			url:"/DinResSys2/admin/activity!saveActivity",
			async:true,
			dataType:'json',
			data:{
				'activity.activityName': activityName,
				'activity.descri':  describe,
				'activity.beginTime': beginTime,
				'activity.endTime': endTime,
				'activity.promotion':promotion
			},
			success:function(data){
				if(parseInt(data.status)==1){
					alert('添加成功');
					$('#addNewActivityModel').modal('hide');
					location.href = '/DinResSys2/admin/activity!getAllActivity';
				}else{
					alert('添加失败');
				}
			}
		});
	}else{
		$.ajax({
			type:"get",
			url:"/DinResSys2/admin/activity!changeActivity",
			async:true,
			dataType:'json',
			data:{
				'activity.id': id,
				'activity.activityName': activityName,
				'activity.descri':  describe,
				'activity.beginTime': beginTime,
				'activity.endTime': endTime,
				'activity.promotion':promotion
			},
			success:function(data){
				if(parseInt(data.status)==1){
					alert('修改成功');
					$('#addNewActivityModel').modal('hide');
					location.href = '/DinResSys2/admin/activity!getAllActivity';
				}else{
					alert('修改失败');
					location.href = '/DinResSys2/admin/activity!getAllActivity';
				}
			}
		});
	}
	
})
