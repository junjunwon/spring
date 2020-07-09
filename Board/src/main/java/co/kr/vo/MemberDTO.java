package co.kr.vo;

public class MemberDTO {
	/*
	 * MemberDTO의 용도는 로그인화면으로부터 전달되는 회원의 데이터(아이디, 비밀번호)를 수집하는 용도로 사용한다.
	 * VO(Value Object)와 DTO(Data Transfer Object)의 차이점
	 * 일반적으로 컨트롤러에 전달되는 데이터를 수집하는 용도로 VO를 사용하는 경우도 있고, DTO를 사용하는 경우도 있는데 두 용어에 대한 공통점과 차이점을 보자.
	 * 공통점
	 * 	- DTO와 VO의 용도는 데이터 수집과 전달에 사용할 수 있다.
	 *  - 파라미터나 리턴 타입으로 사용하는 것이 가능하다.
	 * 차이점
	 *  - VO의 경우 보다 데이터베이스와 거리가 가깝기 때문에 VO는 테이블 구조를 이용해서 작성하는 경우가 많다.
	 *  - DTO의 경우 화면에 거리가 가깝기 때문에 화면에서 전달되는 데이터를 수집하는 용도로 사용하는 경우가 많다.
	 */
	
	private String userId;
	private String userPass;
	private boolean useCookie;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", userPass=" + userPass + ", useCookie=" + useCookie + "]";
	}
	
	
}
