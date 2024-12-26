console.log("link remove.js");

$(function(){
    let id = "";
    let $cur_tr;
    $('#removeBtn').on('click',function(e1){
        //防止自动跳转
        e1.preventDefault();

        //获得当前行的jquery对象
        $cur_tr = $(this).closest('tr');
        //获得当前行id
        id = $cur_tr.children("td").eq(0).text();
        console.log(id);
        //前端删除当前行
        $cur_tr.remove();

        //后端对数据库进行删除操作
        $.ajax({
            type: 'GET',
            url : 'removeCartItem',
            // url:'removeCartItem?workingItemId='+id,
            data:{ workingItemId : id },
            success:function(result){
                console.log(result);
                console.log("Delete success");
                alert("商品删除成功");
            },
            error:function(error){
                console.log(error);
            }
        });

    })
})
