console.log("link remove.js");

$(function(){


    let quantityVal = 0;
    let listPriceVal =0;
    let totalCostVal = 0;
    let subTotalVal = 0;

    let id = "";
    let $cur_tr;
    let $SubTotal = $("#subTotal");

    //删除购物车
    $(document).on('click','#removeBtn',function(e){
        e.preventDefault();
        //从jsp中得到data数据
        id = $(this).data('item-id')
        // console.log(id);

        //获得当前行的jquery对象
        $cur_tr = $(this).closest('tr');
        //前端删除当前行
        $cur_tr.remove();

        //计算当前总价
        totalCostVal = 0;
        subTotalVal = 0;
        $("table tr").slice(1, -1).each(function(){
            quantityVal = parseInt($(this).children("td").eq(4).children("input").val());
            listPriceVal = parseFloat(($(this).children("td").eq(5).text().trim()).substring(1));
            //设置totalCost应该要设置对应的td
            totalCostVal=quantityVal*listPriceVal;
            subTotalVal += totalCostVal;
        });
        $SubTotal.text("Sub Total: $"+subTotalVal);
        console.log($SubTotal.text());

        //后端对数据库进行删除操作
        $.ajax({
            type: 'GET',
            url : 'removeCartItem',
            data:{ workingItemId : id },
            success:function(result){
                // console.log(result);
                // console.log("Delete success");
                alert("商品删除成功");
            },
            error:function(error){
                alert("服务器连接出问题了QAQ");
                console.log(error);
            }
        });

    })
})
