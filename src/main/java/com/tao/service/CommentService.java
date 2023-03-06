package com.tao.service;

import com.tao.po.Comment;

import java.util.List;

/**
 * @author huangt
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
