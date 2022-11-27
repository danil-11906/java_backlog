<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t" %>

<div class="back">
    <header>
        <div class="logo">Анализы Z</div>
            <nav class="menu" id="my-menu">
                <li><a class="nav-menu" href="${pageContext.request.contextPath}/profile">
                    <svg margin-top="1px" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
                        <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                        <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
                    </svg>
                    <span>МОЙ ПРОФИЛЬ</span>
                </a></li>
                <li><a class="nav-menu" href="${pageContext.request.contextPath}/calculator">КАЛЬКУЛЯТОР УСЛУГ</a></li>
                <li><a class="nav-menu" href="${pageContext.request.contextPath}/doctors">ВРАЧИ</a></li>
                <li><a class="nav-menu" href="${pageContext.request.contextPath}/services">СПИСОК УСЛУГ</a></li>
                <li><a class="nav-menu" href="${pageContext.request.contextPath}/bag">КОРЗИНА</a></li>
                <li><a class="nav-menu" href="${pageContext.request.contextPath}/reviews">ОТЗЫВЫ</a></li>
            </nav>
            <div class="bm">
            <a href="#" class="burger_menu" id="burger_menu">
                <svg width="30px" height="30px" viewBox="0 0 16 16" class="bi bi-list" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                </svg>
            </a>
        </div>
    </header>
</div>
<br>
<script src="${pageContext.request.contextPath}/js/burger_menu.js"></script>