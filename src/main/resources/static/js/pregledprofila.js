$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/users/user",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {

            $('#firstName').append(data['namee']);
            $('#lastName').append(data['surname']);
            $('#email').append(data['email']);
            $('#korisnickoime').append(data['korisnickoime']);
            $('#phone').append(data['phoneNumber']);
            $('#birthDate').append(data['birthDate']);
            $('#rola').append(data['rola']);

        },
        error: function (data) {
            window.location.href="error.html";
        }
    });
});