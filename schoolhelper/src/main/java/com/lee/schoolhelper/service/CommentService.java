package com.lee.schoolhelper.service;

import com.lee.schoolhelper.entity.Comment;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentService {
    Integer addComment(Comment comment,Integer userId,Integer orderId,String userNickName);
    Integer addComment2(Comment comment,String userNickName,Integer orderId,Integer userId);
    List<Comment> getCommentByOrderId(Integer orderId);
    List<Comment> getCommentByUserId(Integer userId);
    Integer deleteCommentById(Integer commentId);
    Integer addComment3(Comment comment,Integer userRId,Integer orderId,Integer userAId);
    List<Comment> getCommentPage(Integer pageNo,Integer pageSize);
    Integer countComment();
    List<Comment> queryCommentLike(String content);
}
