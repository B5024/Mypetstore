var xhr = '',
    sendCode = '',
    validate = false;

$(function (){
    $('#email').on('blur', function() {
        var email = $('#email').val().trim();
        checkMail(email);
    });
    $('#form').on('submit', function(event) {
        event.preventDefault();
        if(!validate){
            return;
        }else{
            xhr = new XMLHttpRequest();
            xhr.onreadystatechange = process_sendCode;
            var email = $('#email').val().trim();
            xhr.open('GET', 'mailcode?email=' + email);
            xhr.send(null);
        }
    });
    $('#confirm').on('click', function() {
        var code = $('#code').val().trim();
        if(code === sendCode){
            xhr = new XMLHttpRequest();
            var email = $('#email').val().trim();
            xhr.onreadystatechange = process_reDir;
            xhr.open('GET', 'changeMail?email=' + email);
            xhr.send(null);
        }else{
            $('#feedback').text('code is ont right');
        }
    })
});

function process_reDir() {
    if(xhr.readyState === 4){
        if(xhr.status === 200){
            var response = xhr.responseText;
            if(response === 'redirect'){
                window.location.href = "mainForm";
            }
        }
    }
}

function process_sendCode(){
    if(xhr.readyState === 4){
        if(xhr.status === 200){
            $('#feedback').text('Code has been sent');
            sendCode = xhr.responseText;
        }
    }
}

function checkMail(email) {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if(!emailPattern.test(email)){
        $('#feedback').text('Please enter valid email');
        validate = false;
    }else{
        $('#feedback').text('');
        isMailExist(email);
    }
}

function isMailExist(email){
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = process_isMailExist;
    xhr.open('GET', 'isMailExist?email=' + email);
    xhr.send(null);
}

function process_isMailExist(){
    if(xhr.readyState === 4){
        if(xhr.status === 200){
            var response = xhr.responseText;
            if(response === 'exist') {
                $('#feedback').text('This email has already been used');
                validate = false;
            } else{
                $('#feedback').text('');
                validate = true;
            }
        }
    }
}

