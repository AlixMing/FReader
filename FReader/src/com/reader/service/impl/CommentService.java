package com.reader.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reader.model.Comments;
import com.reader.service.interfaces.ICommentService;
@Service
public class CommentService implements ICommentService {

	public List<Comments> getCommentByBookId(int bookId) {
		return Comments.me.find("select * from comments where bookId = " + bookId);
	}
}
