package com.simple.basic.memo.service;

import com.simple.basic.command.MemoVO;

import java.util.ArrayList;

public interface MemoService {

    void writeMemo(MemoVO vo);
    ArrayList<MemoVO> getMemoList();
}
