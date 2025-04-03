package com.coding404.myweb.util;

import java.util.ArrayList;
import java.util.List;

public class PageVO {
    //페이지네이션을 계산하기 위한 클래스
    private int start; //시작페이지 번호
    private int end; //끝 페이지 번호
    private boolean prev; //"이전" 버튼 활성화 여부
    private boolean next; //"다음" 버튼 활성화 여부

    private int page; //현재 조회하고 있는 페이지 (cri객체와 연관있음)
    private int amount; //현재 조회하고 있는 데이터 개수
    private int total; //전체 게시글 수
    private int realEnd; //맨 마지막 페이지에서 보이는 실제 끝번호 값

    private Criteria cri; //페이지 기준 객체
    private List<Integer> pageList = new ArrayList<>(); //페이지 번호리스트(타임리프에서 향상된 포문 사용)

    private int pageCount = 5; //화면에 보여지는 페이지네이션 개수

    //생성될 때 cri객체와, 전체게시글 수 필요
    public PageVO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;
        this.page = cri.getPage();
        this.amount = cri.getAmount();

        //페이지 끝번호 계산
        //현재 조회하는 페이지가 11번 -> 끝페이지 20
        //현재 조회하는 페이지가 5번 -> 끝페이지 10
        //(int)(Math.ceil(현재 조회하는 번호 / 페이지네이션 개수 )) * 페이지네이션 개수
        this.end = (int)(Math.ceil(this.page / (double)this.pageCount)) * pageCount;
        //페이지 시작번호 계산
        //끝페이지 번호 - 페이지네이션 개수 + 1
        this.start = this.end - this.pageCount + 1;

        //실제 끝번호 계산
        //데이터가 53개라면 -> 실제 마지막 페이지 번호 6
        //데이터가 165개라면 -> 실제 마지막 페이지 번호 17
        //(int)Math.ceil(총 게시글 수 / 혀재 죄호하는 데이터 개수)
        this.realEnd = (int)Math.ceil(this.total / (double)this.amount);

        //실제 마지막번호로 다시 계산
        //데이터가 165개 일때
        //1~10페이지 조회시, end = 10, realEnd = 17
        //11~20페이지 조회시, end = 20, realEnd = 17
        if (this.end > this.realEnd) {
            this.end = this.realEnd;
        }

        //이전 버튼 활성화 여부
        //start = 1, 11, 21, 31 ....
        this.prev = start > 1;

        //다음버튼 활성화 여부
        this.next = this.realEnd > this.end;

        //페이지리스트 초기화
        for(int i = start; i <= end; i++) {
            pageList.add(i);
        }
    }
}
