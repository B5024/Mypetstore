$(function (){
    $('#mainImg').hover(function(event) {
        var $this = event.target;
        var categoryId= $this.id;
        if(categoryId === 'BIRDS2') categoryId = 'BIRDS';
        $("body").append("<div id='popup'></div>");
        $("#popup").css({
            "top": (event.pageY + 10) + "px",
            "position": "absolute",//添加绝对位置
            "background-color":"#90badd",
            "font-weight":"bold",
            "left": (event.pageX + 30) + "px"
        });
        $('#popup').load('productDesc',{categoryId : categoryId});
    }, function() {
        $('#popup').remove();
    });
});