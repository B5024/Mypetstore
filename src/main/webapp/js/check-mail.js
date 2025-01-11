
var mailFrom = sessionStorage.getItem('mailFrom');

var xhr = '';

$(function (){
    $('#email').on('blur', function() {
        var email = $('#email').val().trim();
        checkMail(email);
    });
    $('#form').on('submit', function(event){
        event.preventDefault();
        var email = $('#email').val().trim();
        if(checkMail(email)){
            sendRequest(email);
        }
    })
})

 function checkMail(email) {
     const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
     if(!emailPattern.test(email)){
         $('#feedback').text('Please enter valid email');
         return false;
     }else{
         $('#feedback').text('');
         return true;
     }
 }

 function sendRequest(email){
    xhr= new XMLHttpRequest();
    xhr.onreadystatechange = process;
    xhr.open('GET', 'isMailExist?email=' + email);
    xhr.send(null);
 }

 function process(){
    if(xhr.readyState === 4){
        if(xhr.status === 200){
            var response = xhr.responseText;
            if(response !== 'exist'){
                if(mailFrom === 'findPassword')
                    $('#feedback').text('This email has not been linked to an account yet');
                else{
                    $('#feedback').text('');
                    document.getElementById('form').submit();
                }
            }else{
                if(mailFrom === 'register')
                    $('#feedback').text('This email has already been used');
                else{
                    $('#feedback').text('');
                    document.getElementById('form').submit();
                }
            }
        }
    }
 }


