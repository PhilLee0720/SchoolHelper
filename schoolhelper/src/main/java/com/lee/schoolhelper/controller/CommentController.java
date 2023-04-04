package com.lee.schoolhelper.controller;

import com.lee.schoolhelper.dto.SearchBody;
import com.lee.schoolhelper.dto.UserDTO;
import com.lee.schoolhelper.entity.Comment;
import com.lee.schoolhelper.service.CommentService;
import com.lee.schoolhelper.utils.R;
import com.lee.schoolhelper.utils.UserHolder;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.*;
import sun.plugin.com.event.COMEventHandler;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;
    @PostMapping("/addR/{orderId}/{userNickName}")
    public R addR(@RequestBody Comment comment,@PathVariable("orderId") Integer orderId,@PathVariable("userNickName") String userNickName){
        log.info("comment content is {}",comment.getCommentContent());
        UserDTO userDTO = UserHolder.get();
        Integer integer = commentService.addComment(comment, userDTO.getId(), orderId, userNickName);
        if(integer <= 0){
            return  R.fail("添加失败请稍后再试");
        }
        return  R.success("添加成功",null);
    }

    @PostMapping("/addA/{orderId}/{userNickName}")
    public R addA(@RequestBody Comment comment, @PathVariable("orderId")Integer orderId, @PathVariable("userNickName") String userNickName){
        UserDTO userDTO = UserHolder.get();
        Integer integer = commentService.addComment2(comment,userNickName,orderId,userDTO.getId());
        if(integer <= 0){
            return R.fail("添加失败，请稍后再试");
        }
        return R.success("添加成功",null);
    }
    @GetMapping("/getCommentByOrderId/{orderId}")
    public R getCommentByOrderId(@PathVariable("orderId")Integer orderId){
        List<Comment> comments = commentService.getCommentByOrderId(orderId);
        if(comments == null || comments.size() <= 0){
            return R.fail("");
        }
        return R.success("",comments);
    }

    @GetMapping("/query/{userId}")
    public R getMyComments(@PathVariable("userId")Integer userId){
        List<Comment> comments = commentService.getCommentByUserId(userId);
        if(comments == null || comments.size() <= 0 ){
            return R.fail("");
        }
        return R.success("",comments);
    }
    @DeleteMapping("/delete/{commentId}")
    public R deleteCommentById(@PathVariable("commentId")Integer id){
        Integer res = commentService.deleteCommentById(id);
        if(res <= 0){
            return R.fail("删除失败 请稍后再试!");
        }
        return R.success("删除成功",null);

    }
    @PostMapping("/addAA/{userId}/{orderId}")
    public R addCommentA(@RequestBody Comment comment,@PathVariable("userId")Integer userId,@PathVariable("orderId")Integer orderId){
        Integer res = commentService.addComment3(comment, UserHolder.get().getId(), orderId, userId);
        if(res <= 0){
            return R.fail("评论失败");
        }
        return R.success("评论成功",null);
    }
    @GetMapping("/page/{pageNo}/{pageSize}")
    public R getCommentPage(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        List<Comment> commentPage = commentService.getCommentPage(pageNo, pageSize);
        if(commentPage == null || commentPage.size() <= 0){
            return R.fail("");
        }
        return R.success("",commentPage);
    }
    @GetMapping("/count")
    public R count(){
        Integer res = commentService.countComment();
        if(res <= 0){
            return R.fail("");
        }
        return R.success("",res);
    }
    @PostMapping("/query")
    public R queryCommentLike(@RequestBody SearchBody searchBody){
        List<Comment> comments = commentService.queryCommentLike(searchBody.getContent());
        if(comments == null || comments.size() <= 0){
            return R.fail("");
        }
        return R.success("",comments);

    }
}
