package com.reader.service.interfaces;

import java.util.List;
import com.reader.model.Timeline;

public interface ITimelineService {
	List<Timeline> getTimelines();
	boolean delTimeline(int id);
	boolean updateTimeline(Timeline timeline);
	boolean saveTimeline(Timeline timeline);
}
