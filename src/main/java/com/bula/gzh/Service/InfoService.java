package com.bula.gzh.Service;

import com.bula.gzh.Entity.Info;
import com.bula.gzh.Entity.PageResult;
import com.bula.gzh.Repository.InfoRepository;
import com.bula.gzh.Util.Result;
import com.bula.gzh.Util.ResultGenerator;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
    @Resource
    private InfoRepository infoRepository;

    public InfoService() {
    }

    public Result findByTitleForTime(int limit, int page) {
        Sort sort = Sort.by(Direction.DESC, new String[]{"id"});
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Info> pageInfo = this.infoRepository.findByBeforeEndTime(pageable);
        if (null == pageInfo) {
            return ResultGenerator.genSuccessResult("数据不存在");
        } else {
            PageResult<Info> pageResult = new PageResult(pageInfo.getNumberOfElements(), limit, pageInfo.getTotalPages(), pageInfo.getNumber() + 1, pageInfo.getContent());
            return ResultGenerator.genSuccessResult(pageResult);
        }
    }

    public Result findByTitle(String title, int limit, int page) {
        Sort sort = Sort.by(Direction.ASC, new String[]{"id"});
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page pageInfo;
        if (null == title) {
            pageInfo = this.infoRepository.findAll(pageable);
        } else {
            pageInfo = this.infoRepository.findByTitleLike("%" + title + "%", pageable);
        }

        if (null == pageInfo) {
            return ResultGenerator.genSuccessResult("数据不存在");
        } else {
            PageResult<Info> pageResult = new PageResult(pageInfo.getNumberOfElements(), limit, pageInfo.getTotalPages(), pageInfo.getNumber() + 1, pageInfo.getContent());
            return ResultGenerator.genSuccessResult(pageResult);
        }
    }

    public Result findById(int id) {
        Info info = this.infoRepository.findById(id);
        return null == info ? ResultGenerator.genFailResult("数据不存在") : ResultGenerator.genSuccessResult(info);
    }

    public Result deleteById(Integer[] ids) {
        Integer[] var2 = ids;
        int var3 = ids.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Integer id = var2[var4];
            this.infoRepository.deleteById(id);
        }

        return ResultGenerator.genSuccessResult("删除成功");
    }
}
