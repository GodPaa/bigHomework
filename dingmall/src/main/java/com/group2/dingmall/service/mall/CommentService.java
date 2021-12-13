package com.group2.dingmall.service.mall;


import com.group2.dingmall.po.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2021-12-13
 */
@Service
public interface CommentService{

    // 根据bookID获取该书评论
    List<Comment> getCommentByBookId(Long bookId);

}
