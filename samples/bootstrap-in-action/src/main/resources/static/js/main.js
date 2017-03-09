/**
 * Bolg main JS.
 * Created by waylau.com on 2017/3/9.
 */
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {

    $('#navbarsContainer a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })

});