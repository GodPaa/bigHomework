package com.group2.dingmall.service.mall;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group2.dingmall.dao.BookMapper;
import com.group2.dingmall.dao.CommentMapper;
import com.group2.dingmall.po.Book;
import com.group2.dingmall.po.Comment;
import com.group2.dingmall.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2021-12-13
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private BookMapper bookMapper;

    /**
     * 根据id获取评论
     * @param bookId
     * @return
     */
    @Override
    public List<Comment> getCommentByBookId(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        AssertUtil.isTrue(book == null,"该书本不存在");
        List<Comment> commentList = commentMapper.selectList(new QueryWrapper<Comment>().eq("book_id",bookId));
        AssertUtil.isTrue(commentList.isEmpty() || commentList == null,"该书本还没有人评论");
        return commentList;
    }
}
