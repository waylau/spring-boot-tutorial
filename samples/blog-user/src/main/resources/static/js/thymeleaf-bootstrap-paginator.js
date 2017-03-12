/*!
 * 分页处理.
 * 
 * @since: 1.0.0
 * @author Way Lau <https://waylau.com>
 */
(function($) {
	
	"use strict";
	
	/**
	 * handler:pageIndex 所选页面的索引，从0开始；pageSize 页面的大小，这里默认是10。
	 */
	$.tbpage = function(selector, target, handler) {
		$(selector).off("click", target).on("click", target, function() {
		 
			var pageIndex = $(this).attr("pageIndex");
			
			// 判断所选元素是否为当前页面
			// 若不是当前页面才需要处理
			if($(this).parent().attr("class").indexOf("active")>0){ 
				//console.log("为当前页面");
			}else{
				handler(pageIndex, 10);
			}
 
		});
	};

})(jQuery);