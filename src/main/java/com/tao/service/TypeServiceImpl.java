package com.tao.service;

import com.tao.NotFoundException;
import com.tao.dao.BlogRepository;
import com.tao.dao.TypeRepository;
import com.tao.po.Blog;
import com.tao.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangt
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private BlogRepository blogRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type getType(Long id) {
        return typeRepository.findOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }


    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return typeRepository.findTop(pageable);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteType(Long id) {
        Type type = typeRepository.findById(id);
        List<Blog> listBlog = blogRepository.findBlogsByType(type);
        listBlog.stream().map(blog -> {
            blog.setType(typeRepository.findByName("默认"));
            return blog;
        }).forEach(blog -> blogRepository.save(blog));
        typeRepository.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTypeAndBlogs(Long id) {
        Type type = typeRepository.findById(id);
        List<Blog> listBlog = blogRepository.findBlogsByType(type);
        blogRepository.delete(listBlog);
        typeRepository.delete(id);
    }
}
