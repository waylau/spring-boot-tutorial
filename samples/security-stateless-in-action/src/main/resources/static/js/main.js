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
	
//	$('#loginform').submit(function(e) {
//		  e.preventDefault()
//
//		  $.ajax({
//		    url: `http://localhost:3000/users/login`,
//		    type: 'POST',
//		    data: $(this).serialize(),
//		    dataType: 'json',
//		    success: function(data) {
//		      if (data) {
//		        if (data.usernotfound) {
//		          sweetAlert('Oops...', 'user not found!', 'error')
//		        }
//		        if (data.passerror) {
//		          sweetAlert('Oops...', 'Password wrong!', 'error')
//		        }
//		        if (data.token) {
//		          let token = data.token
//		          localStorage.setItem('token', token)
//
//		          window.location.href = 'http://127.0.0.1:8080/home.html'
//		        }
//		      }
//		    },
//		    error: function(err) {
//		      console.log(err)
//		    }
//
//		  })
//		})
});