<%-- 
    Document   : itemListJson
    Created on : 13-giu-2016, 19.37.30
    Author     : Riccardo Locci
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<json:array>
    <c:forEach var="item" items="${itemList}">
        <json:object>
            <json:property name="name" value="${item.getName()}"/>
            <json:property name="id" value="${item.getId()}"/>
            <json:object name="img">
                <json:property name="alt" value="${item.getImgAlt()}"/>
                <json:property name="src" value="${item.getImgName()}"/>
                <json:property name="width" value="${item.getImgWidth()}"/>
                <json:property name="height" value="${item.getImgHeight()}"/>
            </json:object>
            <json:property name="amount" value="${item.getAmount()}"/>
            <json:property name="price" value="${item.getPrice()}"/>
        </json:object>
    </c:forEach>
</json:array>
