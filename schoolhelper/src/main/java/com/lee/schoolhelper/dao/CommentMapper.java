package com.lee.schoolhelper.dao;

import com.lee.schoolhelper.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import sun.plugin.com.event.COMEventHandler;

import java.util.List;

@Mapper
public interface CommentMapper {
    Integer addComment(@Param("comment") Comment comment,@Param("releasedUserId")Integer releasedUserId,@Param("orderId")Integer orderId,@Param("acceptedUserId")Integer acceptedUserId);

    List<Comment> getCommentByOrderId(@Param("orderId")Integer orderId);

    List<Comment> getCommentByUserId(@Param("userId")Integer userId);

    Integer deleteCommentById(@Param("commentId")Integer commentId);

    List<Comment> getCommentPage(RowBounds rowBounds);

    Integer getCommentNumber();

    List<Comment> getCommentLike(@Param("content")String content);
}
