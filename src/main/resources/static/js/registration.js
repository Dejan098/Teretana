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
    var myJSON = formToJSON(email, password,username,namee,surname,phoneNumber,birthDate);

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/users/registration",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        success: function (data) {
            // data = ulogovani korisnik koji je vratila metoda iz kontrolera
            // mozemo tu vrednost da ispisemo u konzoli
            console.log(data);

            alert(email + " je uspešno registrovan");
            window.location.href = "index.html";
        },
        error: function (data) {
            console.log(data);
            alert("Greska");
        }
    });
});

function formToJSON(email, password,username,namee,surname,phoneNumber,birthDate) {
    return JSON.stringify(
        {
            "email": email,
            "password": password,
            "username":username,
            "namee":namee,
            "surname":surname,
            "phoneNumber": phoneNumber,
            "birthDate" :birthDate
        }
    );
}