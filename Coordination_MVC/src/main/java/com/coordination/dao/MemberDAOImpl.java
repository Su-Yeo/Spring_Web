package com.coordination.dao;

import org.apache.ibatis.session.SqlSession;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
>>>>>>> 73a35b5546b450e0985e8c97b4a8377eb71019a0

import com.coordination.dto.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.coordination.mapper.memberMapper";
	
	//회원목록 전체 조회
//	@Override
//	public List<MemberVO> memberList() throws Exception {
//		
//		return sqlSession.selectList(Namespace+".memberList");
//	}
//
//	//특정 회원 정보 조회
//	@Override
//	public List<MemberVO> selectMember(MemberVO vo) throws Exception {
//		
//		return sqlSession.selectList(Namespace+".selectMember", vo);
//	}

	//회원가입
	@Override
	public void insertMember(MemberVO vo) {
		
		sqlSession.insert(Namespace+".insertMember", vo);
		System.out.println("*****Member Data Insert Completion*****");
	}

	//회원정보 수정
	@Override
	public void updateMember(MemberVO vo) {
		
		sqlSession.insert(Namespace+".updateMember", vo);
		System.out.println("*****Member Data Update Completion*****");
	}

	//회원정보 삭제
	@Override
	public void deleteMember(MemberVO vo) {
		
		sqlSession.insert(Namespace+".deleteMember", vo);
		System.out.println("*****Member Data Delete Completion*****");
	}

	//로그인 처리
	@Override
	public MemberVO loginCheck(MemberVO vo) throws Exception {
		
		vo = sqlSession.selectOne(Namespace+".login", vo);
		
		return vo;
	}
<<<<<<< HEAD

=======
>>>>>>> 73a35b5546b450e0985e8c97b4a8377eb71019a0
}
