package com.tao.service;

import com.tao.po.Blog;
import com.tao.po.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Ming
 * 创建时间：2021/4/25 22:15
 */

public interface NewsService {
    Page<News> findAll(Pageable pageable);

    List<News> findTop(int size);

    Blog getAndConvert(Long id);

    void deleteNews(Long id);

    News updateNews(Long id, News news);

    News saveNews(News news);

    News findById(Long id);
}
