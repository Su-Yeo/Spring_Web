package com.coordination.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.coordination.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.coordination.mapper.memberMapper";
	
	//회원목록 전체 조회
	@Override
	public List<MemberVO> memberList() throws Exception {
		
		return sqlSession.selectList(Namespace+".memberList");
	}

	//특정 회원 정보 조회
	@Override
	public List<MemberVO> selectMember(MemberVO vo) throws Exception {
		
		return sqlSession.selectList(Namespace+".selectMember", vo);
	}

	//회원가입
	@Override
	public void insertMember(MemberVO vo) {
		
		sqlSession.insert(Namespace+".insertMember", vo);
		System.out.println("---Data Insert Completion---");
	}

	//회원정보 수정
	@Override
	public void updateMember(MemberVO vo) {
		
		sqlSession.insert(Namespace+".updateMember", vo);
		System.out.println("---Data Update Completion---");
	}

	//회원정보 삭제
	@Override
	public void deleteMember(MemberVO vo) {
		
		sqlSession.insert(Namespace+".deleteMember", vo);
		System.out.println("---Data Delete Completion---");
	}

	//로그인 처리
	@Override
	public MemberVO loginCheck(MemberVO vo) throws Exception {
		
		vo = sqlSession.selectOne(Namespace+".login", vo);
		
		return vo;
	}

}