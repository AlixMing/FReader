package com.reader.service.interfaces;

import java.util.List;

import com.reader.model.Comments;

public interface ICommentService {
	List<Comments>	getCommentByBookId(int bookId);
	boolean saveComment(Comments comments);
	boolean praiseComment(int id);
}
