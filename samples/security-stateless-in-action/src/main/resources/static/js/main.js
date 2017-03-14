/**
 * Bolg main JS.
 * Created by waylau.com on 2017/3/9.
 */
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {
 
	// 登录
	$("#submitLogin").click(function(){
	    var username = $('#username').val();
	    var password = $('#password').val();
	    var token = ''
	    $.ajax({
	      type: 'POST',
	      url: '/login',
	      data: { username: username , password: password },
	      success: function(resultData){
	        var token = resultData.token;
	        console.log(token);
	        $.ajax({
	          type: 'GET',
	          url: '/memberinfo',
	          headers: {"Authorization": token},
	          success: function(data){
	             $(location).attr('href', '/memberinfo')
	          }
	        });
	      }
	    });
	});
	
});