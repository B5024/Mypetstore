console.log("link chooseItem.js");
$(function(){
    let loadingCart = [];
    let $checkbox;
    //这里要禁止跳转
    $('#checkoutBtn').on('click',function(event){
        //清空数组
        loadingCart =[];
        $("table tr").slice(1, -1).each(function(){
            $checkbox = $(this).children().eq(0).find("input[type='checkbox']");
            if($checkbox.prop('checked')){
                loadingCart.push($checkbox.val());
            }
        });
        // 将JSON对象转换为字符串并进行URL编码
        const commitData = encodeURIComponent(JSON.stringify(loadingCart));

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
            alert('您即将购买商品：\n'+'        ' + loadingCart.join('|'));
        } else {
            //禁止跳转
            event.preventDefault();
            alert('请至少勾选一个选项才能买单');
            return false; // 阻止链接跳转或表单提交
        }
    })
})
