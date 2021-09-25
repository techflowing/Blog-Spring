package cn.techflowing.one.blog.thought.service;

import cn.techflowing.one.blog.thought.mapper.ThoughtMapper;
import cn.techflowing.one.blog.thought.model.Thought;
import cn.techflowing.one.common.Page;
import cn.techflowing.one.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 随想录 Service
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/23 12:20 上午
 */
@Service
public class ThoughtService {

    @Autowired
    ThoughtMapper thoughtMapper;

    public boolean create(Thought thought) {
        String hashKey = Md5Util.getMd5(thought.getTitle() + System.currentTimeMillis());
        return thoughtMapper.create(thought.getTitle(), thought.getContent(), thought.getHtml(),
                thought.getTag(), hashKey) > 0;
    }

    public boolean update(Thought thought) {
        return thoughtMapper.update(thought.getTitle(), thought.getContent(), thought.getHtml(),
                thought.getTag(), thought.getHashKey()) > 0;
    }

    public boolean delete(String hashKey) {
        return thoughtMapper.delete(hashKey) > 0;
    }

    public Page<Thought> queryList(int page, int pageSize) {
        if (page < 1) {
            page = 1;
        }
        List<Thought> list = thoughtMapper.queryList(pageSize, (page - 1) * pageSize);
        int count = thoughtMapper.count();
        Page<Thought> result = new Page<>();
        result.setContent(list);
        result.setPage(page);
        result.setSize(pageSize);
        result.setTotal(count);
        return result;
    }

    public Map<String, Integer> queryTags() {
        List<Thought> tags = thoughtMapper.queryTag();
        if (tags == null || tags.isEmpty()) {
            return null;
        }
        Map<String, Integer> map = new HashMap<>();
        tags.forEach(item -> {
            if (item.getTag() == null || item.getTag().isEmpty()) {
                return;
            }
            item.getTag().forEach(value -> {
                map.put(value, map.getOrDefault(value, 0) + 1);
            });
        });
        return map;
    }
}
