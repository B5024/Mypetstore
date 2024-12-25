console.log("link cart.js");
//我们的页面没有发生改变 所以我们直接修改前端的值 然后将整个表单用ajax发送给后端存入数据库
//设置按钮触发事件

//进入jquery位置
$(function(){

    let quantityVal     = 0.0;
    let listPriceVal    = 0.0;
    let totalCostVal    = 0.0;
    let subTotalVal        = 0.0;

    //要使用id选择器啊。。。
    $('#updateForm').on('submit',function(e){
        totalCostVal = 0.0;
        subTotalVal = 0.0;
        e.preventDefault();
        //我们得拿到修改的那个数量 然后在前端修改对应的价格
        $("table tr").slice(1, -1).each(function(){
            quantityVal = parseInt($(this).children("td").eq(4).children("input").val());
            // console.log(quantityVal);
            //如果数量等于0
            if(quantityVal === 0){
                //删除这一行
                $(this).remove();
            }else {
                listPriceVal = parseFloat(($(this).children("td").eq(5).text().trim()).substring(1));
                console.log(listPriceVal);
                //设置totalCost应该要设置对应的td
                totalCostVal=quantityVal*listPriceVal;
                $(this).children("td").eq(6).text("$"+totalCostVal);
                // console.log(totalCostVal)
                subTotalVal += totalCostVal;
                // console.log(subTotalVal);
            }
        });
        //处理总价 找标签 用选择器 将subTotal使用span圈起来

        $("#subTotal").text("Sub Total: $"+subTotalVal);

        $.ajax({
            type:'POST',
            url:'updateCart',
            data:$('#updateForm').serialize(),
            success:function(){
                console.log('响应成功');
                alert("购物车更新成功");
                //alert本身就是用来弹出提示框
                //问题是在浏览器缓存了对应的js文件，然后js在浏览器中无法及时更新
                //我们使用js版本号的方式去更新缓存
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