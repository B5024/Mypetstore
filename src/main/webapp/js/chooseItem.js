console.log("link chooseItem.js");
$(function(){

    const newOrder = document.getElementById('paymentNewOrder');
    const confirmOrder = document.getElementById('paymentConfirmOrder');
    const openNewOrderButton = document.getElementById('openNewOrderButton');
    const continueNewOrderButton = document.getElementById('continueNewOrderButton');
    const cancelNewOrderButton = document.getElementById('cancelNewOrderButton');
    const overlay = document.getElementById('overlay');
    const confirmOrderButton = document.getElementById('confirmOrderButton');

    const confirmFirstName =document.getElementById('confirmFirstName');
    const confirmLastName =document.getElementById('confirmLastName');
    const confirmAddress1 =document.getElementById('confirmAddress1');
    const confirmAddress2 =document.getElementById('confirmAddress2');
    const confirmCity =document.getElementById('confirmCity');
    const confirmState =document.getElementById('confirmState');
    const confirmZip =document.getElementById('confirmZip');
    const confirmCountry =document.getElementById('confirmCountry');
    const confirmShipFirstName =document.getElementById('confirmShipFirstName');
    const confirmShipLastName =document.getElementById('confirmShipLastName');
    const confirmShipAddress1 =document.getElementById('confirmShipAddress1');
    const confirmShipAddress2 =document.getElementById('confirmShipAddress2');
    const confirmShipCity =document.getElementById('confirmShipCity');
    const confirmShipState =document.getElementById('confirmShipState');
    const confirmShipZip =document.getElementById('confirmShipZip');
    const confirmShipCountry =document.getElementById('confirmShipCountry');

    let loadingCart = [];
    let $checkbox;
    //这里要禁止跳转
    $('#openNewOrderButton').on('click',function(event){
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
            newOrder.style.display = 'flex';
            overlay.style.display='block';
        } else {
            //禁止跳转
            event.preventDefault();
            alert('请至少勾选一个选项才能买单');
            return false; // 阻止链接跳转或表单提交
        }
    })

    continueNewOrderButton.addEventListener('click',()=>{

        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const Address1 = document.getElementById('Address1').value;
        const Address2 = document.getElementById('Address2').value;
        const City = document.getElementById('City').value;
        const State = document.getElementById('State').value;
        const Zip = document.getElementById('Zip').value;
        const Country = document.getElementById('Country').value;

        const shipFirstName = document.getElementById('aFirstName').value;
        const shipLastName = document.getElementById('aLastName').value;
        const shipAddress1 = document.getElementById('aAddress1').value;
        const shipAddress2 = document.getElementById('aAddress2').value;
        const shipCity = document.getElementById('aCity').value;
        const shipState = document.getElementById('aState').value;
        const shipZip = document.getElementById('aZip').value;
        const shipCountry = document.getElementById('aCountry').value;

        confirmFirstName.textContent=firstName;
        confirmLastName.textContent=lastName;
        confirmAddress1.textContent=Address1;
        confirmAddress2.textContent=Address2;
        confirmCity.textContent=City;
        confirmState.textContent=State;
        confirmZip.textContent=Zip;
        confirmCountry.textContent=Country;

        confirmShipFirstName.textContent=shipFirstName;
        confirmShipLastName.textContent=shipLastName;
        confirmShipAddress1.textContent=shipAddress1;
        confirmShipAddress2.textContent=shipAddress2;
        confirmShipCity.textContent=shipCity;
        confirmShipState.textContent=shipState;
        confirmShipZip.textContent=shipZip;
        confirmShipCountry.textContent=shipCountry;


        newOrder.style.display = 'none';
        confirmOrder.style.display = 'flex';

    });

    confirmOrderButton.addEventListener('click', () => {
        const form =document.getElementById('paymentForm');
        confirmOrder.style.display = 'none';
        overlay.style.display='none';

        form.submit();
    });

    cancelNewOrderButton.addEventListener('click', () => {
        newOrder.style.display = 'none';
        overlay.style.display='none';
    });

    window.addEventListener('click', (event) => {
        if (event.target === newOrder) {
            newOrder.style.display = 'none';
            overlay.style.display='none';
        }
    });
})
