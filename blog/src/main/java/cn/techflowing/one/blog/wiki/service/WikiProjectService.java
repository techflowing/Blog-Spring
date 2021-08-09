package cn.techflowing.one.blog.wiki.service;

import cn.techflowing.one.blog.wiki.mapper.WikiProjectMapper;
import cn.techflowing.one.blog.wiki.model.WikiProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Wiki 项目Service
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/8 10:09 下午
 */
@Service
public class WikiProjectService {

    @Autowired
    WikiProjectMapper wikiProjectMapper;

    public List<WikiProject> queryAllWikiProject() {
        return wikiProjectMapper.queryAllWikiProject();
    }
}