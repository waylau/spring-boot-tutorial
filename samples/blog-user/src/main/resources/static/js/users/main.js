/**
 * Bolg main JS.
 * Created by waylau.com on 2017/3/9.
 */
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {
 
 
	 $(".page").click(function() {
		 $.ajax({ 
			 url: "/users", 
			 contentType : 'application/json',
			 data:{"async":true, "pageIndex":1, "name":$("#searchName").val()},
			 success: function(data){
				 $("#userMain").html(data);
		     },
		     error : function() {
		         alert("error");
		     }
		 });
     });
});