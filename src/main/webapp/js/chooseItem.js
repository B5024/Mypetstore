console.log("link chooseItem.js");
$(function(){
    let loadingCart = [];
    let $checkbox;
    let commit_cart = [];
    //这里要禁止跳转
    $('#checkoutBtn').on('click',function(event){
        //清空数组
        loadingCart =[];
        let i = 0;
        $("table tr").slice(1, -1).each(function(){
            $checkbox = $(this).children().eq(0).find("input[type='checkbox']");
            if($checkbox.prop('checked')){
                loadingCart.push($checkbox.val());
            }
        });

        //先这样子
        commit_cart = JSON.parse(JSON.stringify(loadingCart));
        // 将JSON对象转换为字符串并进行URL编码
        const commitData = encodeURIComponent(JSON.stringify(commit_cart));

        $.ajax({
            type: 'GET',
            url: 'commitCart?commitCartData=' + commitData, // 将commitData附加到URL
            success: function() {
                console.log('响应成功');
            },
            error: function() {
                console.log('失败');
            }
        });

        if (loadingCart.length > 0) {
            alert('您即将购买商品：' + loadingCart.join('|'));
        } else {
            //禁止跳转
            event.preventDefault();
            alert('请至少勾选一个选项才能买单');
            return false; // 阻止链接跳转或表单提交
        }
    })

    // $('#confirmBtn').on('click',function(){
    //     let data = sessionStorage.getItem('commit_cart');
    //     alert('已勾选的项: ' +data);
    //     // let allData = $.extend({}, $('#confirmBtn').serializeArray(), data);
    //     $.ajax({
    //         type:'POST',
    //         url:'confirmOrder',
    //         contentType: 'application/json', // 设置内容类型为JSON
    //         data: {
    //             commitData : data
    //         },
    //         success:function(){
    //             console.log('响应成功');
    //             alert("购物车更新成功");
    //         },
    //         error:function (jqXHR, textStatus, errorThrown){
    //             console.log('AJAX请求失败：' + textStatus + ', 错误信息：' + errorThrown);
    //             alert("更新失败，请稍后再试");
    //         }
    //     });
    // })
})

/*
* 我们应该是每一次都选择？ 还是说add的时候一次性选择
* 得到数组
* 给数据
* 成交渲染数据
* 删除购物车对应数据
*
* */