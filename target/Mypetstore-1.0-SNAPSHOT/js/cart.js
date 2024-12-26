console.log("link cart.js");
$(function(){

    let quantityVal     = 0;
    let listPriceVal    = 0;
    let totalCostVal    = 0;
    let subTotalVal        = 0;

    $('#updateForm').on('submit',function(e){
        totalCostVal = 0;
        subTotalVal = 0;
        e.preventDefault();
        $("table tr").slice(1, -1).each(function(){
            quantityVal = parseInt($(this).children("td").eq(4).children("input").val());
            //如果数量等于0
            if(quantityVal === 0){
                //删除这一行
                $(this).remove();
            }else {
                listPriceVal = parseFloat(($(this).children("td").eq(5).text().trim()).substring(1));
                console.log(listPriceVal);
                totalCostVal=quantityVal*listPriceVal;
                $(this).children("td").eq(6).text("$"+totalCostVal);
                subTotalVal += totalCostVal;
            }
        });
        $("#subTotal").text("Sub Total: $"+subTotalVal);

        $.ajax({
            type:'POST',
            url:'updateCart',
            data:$('#updateForm').serialize(),
            success:function(){
                console.log('响应成功');
                alert("购物车更新成功");
            },
            error:function (){
                console.log('错误');
            }
        });
    })

});



//如果他是0的话 我们得把它删除


//
// let xhr = '';
//
// function send_cart_msg(){
//     xhr = new XMLHttpRequest();
//     xhr.onreadystatechange = process;
//     xhr.open('POST','http://localhost:8080/updateCart');
//     xhr.send(null);
// }
//
// function process() {
//     //获得整个类的信息？
//     if(xhr.readyState === 4){
//         Handle(xhr.responseXML);
//     }
// }
//
// function Handle(responseXML) {
//     //不解析直接得到 得到之后怎么放？
//
// }


//导入js