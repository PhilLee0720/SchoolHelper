package com.lee.schoolhelper.service.impl;

import com.lee.schoolhelper.dao.CommentMapper;
import com.lee.schoolhelper.dao.UserMapper;
import com.lee.schoolhelper.entity.Comment;
import com.lee.schoolhelper.entity.User;
import com.lee.schoolhelper.service.CommentService;
import com.lee.schoolhelper.utils.R;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public Integer addComment(Comment comment, Integer userId, Integer orderId, String userNickName) {
      Integer id   = userMapper.getUserId(userNickName);
      if(id == null){
          return  0;
      }
        Integer value = commentMapper.addComment(comment, userId, orderId, id);
      return value;
    }

    @Override
    public Integer addComment2(Comment comment, String userNickName, Integer orderId, Integer userId){
        Integer id = userMapper.getUserId(userNickName);
        if(id == null){
            return 0;
        }
        Integer res = commentMapper.addComment(comment, id, orderId, userId);
        return res;
    }

    @Override
    public List<Comment> getCommentByOrderId(Integer orderId) {
        List<Comment> comments = commentMapper.getCommentByOrderId(orderId);
        return comments;

    }

    @Override
    public List<Comment> getCommentByUserId(Integer userId) {
        List<Comment> comments = commentMapper.getCommentByUserId(userId);
        return comments;

    }

    @Override
    public Integer deleteCommentById(Integer commentId) {
        Integer res = commentMapper.deleteCommentById(commentId);
        return res;

    }

    @Override
    public Integer addComment3(Comment comment, Integer userRId, Integer orderId, Integer userAId) {
        Integer res = commentMapper.addComment(comment, userRId, orderId, userAId);
        return res;
    }

    @Override
    public List<Comment> getCommentPage(Integer pageNo, Integer pageSize) {
        Integer startIndex = (pageNo - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(startIndex,pageSize);
        List<Comment> commentPage = commentMapper.getCommentPage(rowBounds);
        return commentPage;
    }

    @Override
    public Integer countComment() {
        Integer res = commentMapper.getCommentNumber();
        return res;
    }

    @Override
    public List<Comment> queryCommentLike(String content) {
        List<Comment> comments = commentMapper.getCommentLike(content);
        return comments;
    }
}
