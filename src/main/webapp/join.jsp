<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <title>MBTI</title>
   <link rel="stylesheet" href="css.css">
   
<script src="./jquery.js"></script>
<script>
	//비밀번호 8 ~ 16자 영문 + 숫자
	function isPw(pw) {
		var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;

		return regExp.test(pw);
	}

	//비밀번호 6~20자 영문또는 숫자
	function isId(id) {
		var regExp = /^[a-z]+[a-z0-9]{5,19}$/g;

		return regExp.test(id);
	}

	function check() {
		var id = $('input[name=id]').val();
		var pw = $('input[name=pw]').val();
		var pwcheck = $('input[name=pwcheck]').val();
		//아이디 정규식 검사
		if (!isId(id)) {
			alert("아이디를 6~20자의 영문또는 숫자조합으로 만들어주세요.");
			return false;
		}

		//비밀번호 정규식 검사
		if (!isPw(pw)) {
			alert("비밀번호를 8~16자의 영문 + 숫자 조합으로 만들어주세요.");
			return false;
		}

		//비밀번호가 서로 같은지 비교
		if (pw != pwcheck) {
			alert("비밀번호가 다릅니다.");
			return false;
		}
		
		//중복검사 체크
		if($('input[name=hidden]').val() != 0){
			alert("중복검사를 해주세요");
			return false;
		}
		
		//미친놈 체크
		if($('input[name=idhidden]').val() != id){
			alert("씹새끼 잡았다");
			return false;
		}
		
		alert('가입성공');
		document.getElementById("frm").submit();
		return true;
	}

	function idcheck() {
		var userId = $('input[name=id]').val();
		if (!isId(userId)) {
			alert("아이디를 6~20자의 영문또는 숫자조합으로 만들어주세요.");
			return false;
		}
		$.post("idcheck", {
			userId : userId
		}, function(data) {
			if (data == 0) {
				//히든값 변경 해줘야함
				alert("아이디 사용 가능");
				$('input[name=hidden]').val(0);
				$('input[name=idhidden]').val(userId);
			} else {
				alert("아이디 사용 불가");
				$('input[name=hidden]').val(1);
			}
		});
	}
	
	
	
</script>

</head>
<body>
    <section class="index_login_wrap">
        <div class="index_login_1000">
            <div class="index_logo_wrap">
                <img src="./img/main_logo.png">
            </div>
            <div class="index_login_content_wrap">
                <form action="./joincon" method="post" id="frm">
                <input type="hidden" name="hidden" value="1">
                <input type="hidden" name="idhidden" value="">
                    <table>
                        <tr>
                            <td width="30%">ID</td>
                            <td width="60%"><input type="text" name="id" placeholder="6~20자의 영문또는 숫자조합" required></td>
                            <td width="10%"><input type="button" value="중복검사" onclick="idcheck()"></td>
                        </tr>
                        <tr>
                            <td>PW</td>
                            <td><input type="password" name="pw" placeholder="8~16자의 영문 + 숫자 조합" required></td>
                        </tr>
                        <tr>
                            <td>PW 확인</td>
                            <td><input type="password" name="pwcheck" required></td>
                        </tr>
                        <tr>
                            <td>MBTI</td>
                            <td>
                                <select name="mbti">
                                    <option value="intj">INTJ</option>
                                    <option value="intp">INTP</option>
                                    <option value="infj">INFJ</option>
                                    <option value="infp">INFP</option>
                                    <option value="istj">ISTJ</option>
                                    <option value="istp">ISTP</option>
                                    <option value="isfj">ISFJ</option>
                                    <option value="isfp">ISFP</option>
                                    <option value="entj">ENTJ</option>
                                    <option value="entp">ENTP</option>
                                    <option value="enfj">ENFJ</option>
                                    <option value="enfp">ENFP</option>
                                    <option value="estj">ESTJ</option>
                                    <option value="estp">ESTP</option>
                                    <option value="esfj">ESFJ</option>
                                    <option value="esfp">ESFP</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><input type="button" value="가입하기" onclick="check()"><input type="reset" value="다시쓰기"></td>
                        </tr>
                    </table>
                </form>
            </div>
        <div>
    </section>
</body>
</html>