$(document).on("submit", "form", function(event){

    // da se izbegne izvrsavanja pravog submita forme
    event.preventDefault();

    var email = $("#email").val();
    var password = $("#password").val();
    var username=$("#username").val();
    var surname =$("#surname").val();
    var phoneNumber =$("#phoneNumber").val();
    var birthDate =$("#birthDate").val();
    var namee =$("#namee").val();
    var fitnescentar =$("#fitnescentar").val();
    var myJSON = formToJSON(email, password,username,namee,surname,phoneNumber,birthDate,fitnescentar);

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/auth/signuptrener",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        success: function () {
            // data = ulogovani korisnik koji je vratila metoda iz kontrolera
            // mozemo tu vrednost da ispisemo u konzoli
            alert("Uspesna registracija");
            window.location.href = "index.html";
        },
        error: function (error) {

            alert("Greska");
        }
    });
});

function formToJSON(email, password,username,namee,surname,phoneNumber,birthDate,fitnescentar) {
    return JSON.stringify(
        {
            "email": email,
            "password": password,
            "username":username,
            "namee":namee,
            "surname":surname,
            "phoneNumber": phoneNumber,
            "birthDate" :birthDate,
            "fitnescentar":fitnescentar
        }
    );
}