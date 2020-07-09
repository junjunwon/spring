package co.kr.vo;

public class MemberDTO {
	/*
	 * MemberDTO�� �뵵�� �α���ȭ�����κ��� ���޵Ǵ� ȸ���� ������(���̵�, ��й�ȣ)�� �����ϴ� �뵵�� ����Ѵ�.
	 * VO(Value Object)�� DTO(Data Transfer Object)�� ������
	 * �Ϲ������� ��Ʈ�ѷ��� ���޵Ǵ� �����͸� �����ϴ� �뵵�� VO�� ����ϴ� ��쵵 �ְ�, DTO�� ����ϴ� ��쵵 �ִµ� �� �� ���� �������� �������� ����.
	 * ������
	 * 	- DTO�� VO�� �뵵�� ������ ������ ���޿� ����� �� �ִ�.
	 *  - �Ķ���ͳ� ���� Ÿ������ ����ϴ� ���� �����ϴ�.
	 * ������
	 *  - VO�� ��� ���� �����ͺ��̽��� �Ÿ��� ������ ������ VO�� ���̺� ������ �̿��ؼ� �ۼ��ϴ� ��찡 ����.
	 *  - DTO�� ��� ȭ�鿡 �Ÿ��� ������ ������ ȭ�鿡�� ���޵Ǵ� �����͸� �����ϴ� �뵵�� ����ϴ� ��찡 ����.
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
