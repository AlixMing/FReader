package com.reader.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.reader.model.Timeline;
import com.reader.service.interfaces.ITimelineService;
@Service
public class TimelineService implements ITimelineService {

	public List<Timeline> getTimelines(int userId) {
		return Timeline.me.find("select * from timeline where userId = " + userId);
	}

	public boolean delTimeline(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateTimeline(Timeline timeline) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveTimeline(Timeline timeline) {
		// TODO Auto-generated method stub
		return false;
	}

}
