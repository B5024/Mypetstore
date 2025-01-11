var elUsername = document.getElementById("username"),
    elPassword = document.getElementById("password"),
    elMsg = document.getElementById('feedback'),
    elForm = document.getElementById("form");

var xhr = '';

function validate(){
    if(elUsername.value === ''){
        elMsg.textContent = 'Username is required';
        return false;
    }
    else if(elUsername.value !== '' && elPassword.value === ''){
        elMsg.textContent = 'Password is required';
        return false;
    }
    else{
        elMsg.textContent = '';
        return true;
    }
}

function sendRequest(){
    if(!validate()) return;

    var username = elUsername.value;
    var password = elPassword.value;

    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = process;
    xhr.open('GET', 'signOn?username=' + username + '&password=' + password);
    xhr.send(null);
}

function process(){
    if(xhr.readyState === 4){
        if(xhr.status === 200){
            var response = xhr.responseText;
            elMsg.textContent = response;
            if(response === 'logged in'){
                elForm.submit();
            }
        }
    }
}

elUsername.addEventListener('blur', validate);
elPassword.addEventListener('blur', validate);
elForm.addEventListener('submit', function(event) {
    event.preventDefault();
    sendRequest();
});


var elFindPassword = document.getElementById('findPassword'),
    elRegister = document.getElementById('register');

elFindPassword.addEventListener('click', function(event){
    event.preventDefault();
    sessionStorage.setItem('mailFrom','findPassword');
    window.location.href = elFindPassword.href;
})

elRegister.addEventListener('click', function(event){
    event.preventDefault();
    sessionStorage.setItem('mailFrom','register');
    window.location.href = elRegister.href;
})

