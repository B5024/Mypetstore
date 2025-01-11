$(function (){
    var validate = false;
    var $Msg = $('#feedback');
    $('table#register_info').on('blur', 'td', function() {
        var factor = $(this).closest('tr').find('td:first').text().replace(':', '').trim();
        if ($(this).find('input').val() === '') {
            $Msg.text(factor + ' is required');
            validate = false;
        } else {
            $Msg.text('');
            validate = true;
        }
    });
    $('#confirmPassword').on('blur',function(){
        if($('#confirmPassword').val() !== $('#password').val()){
            $Msg.text('Confirm password does not match');
            validate = false;
        }else{
            $Msg.text('');
            validate = true;
        }
    });
    $('#password').on('blur',function(){
        if($('#password').val() === ''){
            $Msg.text('Password is required');
            validate = false;
        }else if(!hasLetterAndNumber($('password').val().trim())){
            $Msg.text('Password should have at least one letter and one number');
            validate = false;
        }else {
            $Msg.text('');
            validate = true;
        }
    });
    $('#form').on('submit', function(event) {
        event.preventDefault();
        const emailcode = $('#emailcode').val().trim();
        const username = $('#username').val().trim();
        if(validate){
            $.get("register?emailcode=" + emailcode + "&username=" + username,function(data,status){
                if(status === 'success'){
                    const parts = data.split(":", 2);
                    if(parts[0] === 'exist'){
                        $Msg.text('Username already exists');
                    }else{
                        if(parts[1] === 'not match'){
                            $Msg.text('Mail code does not match');
                        }else{
                            $Msg.text('');
                            document.getElementById('form').submit();
                        }
                    }
                }
            })
        }
    })


})

function hasLetterAndNumber(str){
    const hasLetter = /[a-zA-Z]/.test(str);
    const hasNumber = /\d/.test(str);

    return hasLetter && hasNumber;
}