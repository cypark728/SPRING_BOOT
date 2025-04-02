package com.simple.basic.memo.service;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.mapper.MemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service("memoService")
public class MemoServiceImpl implements MemoService{

    @Autowired
    private MemoMapper memoMapper;

    @Override
    public void writeMemo(MemoVO vo) {
        memoMapper.writeMemo(vo);
    }

    @Override
    public ArrayList<MemoVO> getMemoList() {
        return memoMapper.getMemoList();
    }
}
