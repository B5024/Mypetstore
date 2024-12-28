var validate = true;

$(function (){
    $('#confirmPassword').on('blur',function(){
        if($('#confirmPassword').val() !== $('#password').val()){
            $('#feedback_User').text('Confirm password does not match');
            validate = false;
        }else{
            $('#feedback_User').text('');
            validate = true;
        }
    });
    $('#password').on('blur',function(){
        if($('#password').val() === ''){
            $('#feedback_User').text('Password is required');
            validate = false;
        }else if(!hasLetterAndNumber($('password').val())){
            $('#feedback_User').text('Password should have at least one letter and one number');
            validate = false;
        }else {
            $('#feedback_User').text('');
            validate = true;
        }
    });
    $('table#account_information').on('blur', 'td', function() {
        var factor = $(this).closest('tr').find('td:first').text().replace(':', '').trim();
        console.log(factor);
        console.log($(this).find('input').val());
        if ($(this).find('input').val() === '') {
            $('#feedback_User').text(factor + ' is required');
            validate = false;
        } else {
            $('#feedback_User').text('');
            validate = true;
        }
    });
    $('#form').on('submit', function(event) {
        event.preventDefault();
        if(validate === true){
            $('#form').submit();
        }
    })
});


function hasLetterAndNumber(str){
    const hasLetter = /[a-zA-Z]/.test(str);
    const hasNumber = /\d/.test(str);

    return hasLetter && hasNumber;
}








