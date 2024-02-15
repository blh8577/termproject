<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="mbti" value="${sessionScope.member.mbti_idx}" scope="session"/>
<c:set var="color" value="${sessionScope.color}" scope="session"/>

<!DOCTYPE html>
<html>
<head>
   <title>MBTI</title>
   <link rel="stylesheet" href="css.css">
</head>
<body>
<!-- Header Section -->

<c:import url="./header.jsp"></c:import>


<!-- Content Section -->
<section class="home_content_wrap">
   <div class="home_content_1000">
      <table>
         <tr>
            <td width="70%" height="50%" class="<c:out value="${color}"/>_border">
               <div class="home_content_title  <c:out value="${color}"/>_bottom">인기글 게시판 뷰</div>
               <div class="home_content_list_wrap">
                  <ul>
                  
                     <c:import url="./latestBoard?homeboard=2"></c:import>
                  
                  </ul>
               </div>
            </td>
            <td width="30%" class="<c:out value="${color}"/>_border">
               <div class="home_my_info_wrap <c:out value="${color}"/>_color"><c:out value="${mbti}"/></div>
               <div class="home_my_logint_wrap"><button class="<c:out value="${color}"/>_background" onClick="location.href='./logout'">로그아웃</button></div>
            </td>
         </tr>
         <tr>
            <td height="50%" class="<c:out value="${color}"/>_border">
               <div class="home_content_title  <c:out value="${color}"/>_bottom">최신글 게시판 뷰</div>
               <div class="home_content_list_wrap">
                  <ul>
                     
                     <c:import url="./latestBoard?homeboard=1"></c:import>
                     
                     
                  </ul>
               </div>
            </td>
            <td class="<c:out value="${color}"/>_border">
<%--                <div class="home_content_title  <c:out value="${color}"/>_bottom">쪽지 리스트</div>
               <div class="home_content_list_wrap">
                  <ul>
                     <li><a href="#">&middot; 게시글 제목입니다.</a></li>
                     <li><a href="#">&middot; 게시글 제목입니다.</a></li>
                     <li><a href="#">&middot; 게시글 제목입니다.</a></li>
                     <li><a href="#">&middot; 게시글 제목입니다.</a></li>
                     <li><a href="#">&middot; 게시글 제목입니다.</a></li>
                  </ul>
               </div> --%>
            </td>
         </tr>
      </table>
   </div>
   
   				<div class="search_wrap">
					<div>
						<form action="./search?cat=${param.cat}" method="post">
							<input type="text" name="search"
								class="<c:out value="${color}"/>_border"><input
								type="submit" value="검색"
								class="<c:out value="${color}"/>_background" />
						</form>
					</div>
				</div>
				
</section>

<!-- Footer Section -->

<c:import url="./footer.jsp"></c:import>

</body>
</html>