/*!
 * Bolg main JS.
 * 
 * @since: 1.0.0 2017/3/9
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=main.js
 
// DOM 加载完再执行
$(function() {
	
	var _pageSize; // 存储用于搜索
	
	// 获取 CSRF Token 
	var csrfHeader = $("meta[name='_csrf']").attr("content");
	var csrfToken = $("meta[name='_csrf_header']").attr("content");
	
	// 根据用户名、页面索引、页面大小获取用户列表
	function getUersByName(pageIndex, pageSize) {
		 $.ajax({ 
			 url: "/users", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize,
				 "name":$("#searchName").val()
			 },
			 success: function(data){
				 $("#mainContainer").html(data);
		     },
		     error : function() {
		         alert("error");
		     }
		 });
	}
	
	// 分页
	$.tbpage("#mainContainer", function (pageIndex, pageSize) {
		getUersByName(pageIndex, pageSize);
		_pageSize = pageSize;
	});
   
	// 搜索
	$("#searchNameBtn").click(function() {
		getUersByName(0, _pageSize);
	});
	
	// 获取添加用户的界面
	$("#addUser").click(function() {
		$.ajax({ 
			 url: "/users/add", 
			 success: function(data){
				 $("#userFormContainer").html(data);
		     },
		     error : function() {
		         alert("error");
		     }
		 });
	});
	
	// 获取编辑用户的界面
	$(".blog-edit-user").click(function() {
		$.ajax({ 
			 url: "/users/edit/" + $(this).attr("userId"), 
			 success: function(data){
				 $("#userFormContainer").html(data);
		     },
		     error : function() {
		         alert("error");
		     }
		 });
	});
	
	
	// 提交变更后，清空表单
	$("#submitEdit").click(function() {
		$.ajax({ 
			 url: "/users", 
			 type: 'POST',
			 data:$('#userForm').serialize(),
			 success: function(data){
				 
				 // 从新刷新主界面
				 getUersByName(0, _pageSize);
		     },
		     error : function() {
		         alert("error");
		     }
		 });
	});
	
	// 删除用户
	$(".blog-delete-user").click(function() {
		$.ajax({ 
			 url: "/users/" + $(this).attr("userId") , 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
             },
			 success: function(data){
				 // 从新刷新主界面
				 getUersByName(0, _pageSize);
		     },
		     error : function() {
		         alert("error");
		     }
		 });
	});

 
	
});